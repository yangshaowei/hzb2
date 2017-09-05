package com.cbs.common.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cbs.bean.BillItem;
import com.cbs.bean.Data;
import com.cbs.bean.Detail;
import com.cbs.common.db.dao.BillDao;
import com.cbs.common.db.orm.DBHelper;
import com.cbs.common.model.BaseModel;
import com.cbs.common.model.BillMessage;
import com.cbs.common.utils.PreferenceHelper.PreferenceConstant;
import com.cbs.domain.ResponeData;
import com.cbs.impl.SearchBillAllImpl;
import com.cbs.model.RequestModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by yangshaowei on 2017/8/23
 */

public class BillMessageDbUtil {

    private DBHelper dbHelper;
    private Context context;

    public BillMessageDbUtil(Context context){
        dbHelper = BillDao.getDbHelper(context);
        this.context = context;
    }

    /**
     * 不存在，则插入
     * @param
     */
    public void insert(BillItem billItem) {
        SQLiteDatabase dbw = dbHelper.getWritableDatabase();
        if (dbw.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(BaseModel.COLUMN_ID, billItem.getId());
            values.put(BaseModel.COLUMN_HOLDERSID, billItem.getHoldersId());
            values.put(BaseModel.COLUMN_ISBALANCE, billItem.isBalance());
            values.put(BaseModel.COLUMN_DAYCONSUME, billItem.getDetail().getDayConsume());
            values.put(BaseModel.COLUMN_MONCONSUME, billItem.getDetail().getMonConsume());
            values.put(BaseModel.COLUMN_ALLCONSUME, billItem.getDetail().getAllConsume());
            values.put(BaseModel.COLUMN_TITLE, billItem.getDetail().getTitle());
            values.put(BaseModel.COLUMN_CREATETIME, billItem.getDetail().getCreateTime());
            dbw.insert(BillMessage.TABLE_NAME, null, values);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delect(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + BillMessage.TABLE_NAME, null);
            if(null != cursor && 0 != cursor.getCount()){
                while (cursor.moveToNext()) {
                    String idTp = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_ID));
                    if(id.equals(idTp)){
                        db.delete(BillMessage.TABLE_NAME, BaseModel.COLUMN_ID + " = ?", new String[]{id});
                    }
                }
            }
            cursor.close();
        }
    }

    /**
     * 查询账单
     * @param queryId
     * @return
     */
    public BillItem queryBillItemById(String queryId){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        BillItem billItem = new BillItem();

        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + BillMessage.TABLE_NAME, null);
            if(null != cursor && 0 != cursor.getCount()){
                while (cursor.moveToNext()) {
                    String id = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_ID));
                    if(queryId.equals(id)){
                        String holderId = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_HOLDERSID));
                        String isBalance = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_ISBALANCE));
                        String dayConsume = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_DAYCONSUME));
                        String monConsume = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_MONCONSUME));
                        String allConsume = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_ALLCONSUME));
                        String title = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_TITLE));
                        String creatTime = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_CREATETIME));

                        billItem.setId(id);
                        billItem.setHoldersId(holderId);
                        billItem.setBalance(Boolean.valueOf(isBalance));
                        Detail detail = new Detail();
                        detail.setDayConsume(dayConsume);
                        detail.setMonConsume(monConsume);
                        detail.setAllConsume(allConsume);
                        detail.setTitle(title);
                        detail.setCreateTime(creatTime);
                        billItem.setDetail(detail);
                        return billItem;
                    }
                }
            }
            cursor.close();
        }
        return null;
    }

    /**
     * 查询全部账单
     * @param
     * @return
     */
    public List<BillItem> queryBillItemAll(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<BillItem> billItemList = new ArrayList<>();

        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + BillMessage.TABLE_NAME, null);
            if(null != cursor && 0 != cursor.getCount()){
                while (cursor.moveToNext()) {
                    BillItem billItem = new BillItem();
                    String id = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_ID));
                    String holderId = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_HOLDERSID));
                    String isBalance = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_ISBALANCE));
                    String dayConsume = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_DAYCONSUME));
                    String monConsume = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_MONCONSUME));
                    String allConsume = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_ALLCONSUME));
                    String title = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_TITLE));
                    String creatTime = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_CREATETIME));

                    billItem.setId(id);
                    billItem.setHoldersId(holderId);
                    billItem.setBalance(Boolean.valueOf(isBalance));
                    Detail detail = new Detail();
                    detail.setDayConsume(dayConsume);
                    detail.setMonConsume(monConsume);
                    detail.setAllConsume(allConsume);
                    detail.setTitle(title);
                    detail.setCreateTime(creatTime);
                    billItem.setDetail(detail);
                    billItemList.add(billItem);
                }
            }
            cursor.close();
        }
        return billItemList;
    }

    public void delectAllBillItem(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(BillMessage.TABLE_NAME,null,null);
    }

    /**
     * 数据库同步网络数据
     */
    public void syncDb(){
        SearchBillAllImpl searchBillAll = new SearchBillAllImpl((String) SharePreferenceUtils.getDBParam(context, PreferenceConstant.LOGIN_USERNAME, null),
                (String) SharePreferenceUtils.getDBParam(context, PreferenceConstant.LOGIN_PASSWORD, null));
        searchBillAll.request(new RequestModel.RequestCallBack() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                delectAllBillItem();

                Gson gson = new Gson();
                ResponeData responeData = gson.fromJson(s, ResponeData.class);
                Data data = responeData.getData();
                for(BillItem billItem : data.getBillItems()){
                    insert(billItem);
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {

            }
        });
    }
}
