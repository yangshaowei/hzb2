package com.cbs.common.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cbs.bean.BillItem;
import com.cbs.bean.ConsumerInfo;
import com.cbs.bean.Detail;
import com.cbs.common.db.dao.BillDao;
import com.cbs.common.db.orm.DBHelper;
import com.cbs.common.model.BaseModel;
import com.cbs.common.model.BillMessage;
import com.cbs.common.model.ConsumerInfos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangshaowei on 2017/8/23
 */

public class ConsumerInfosDbUtil {

    private DBHelper dbHelper;

    public ConsumerInfosDbUtil(Context context){
        dbHelper = BillDao.getDbHelper(context);
    }

    /**
     * 不存在，则插入
     * @param
     */
    public void insert(String queryId, ConsumerInfo consumerInfo) {
        SQLiteDatabase dbw = dbHelper.getWritableDatabase();
        if (dbw.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(BaseModel.COLUMN_ID, queryId);
            values.put(BaseModel.COLUMN_HOLDERSID, consumerInfo.getHoldersId());
            values.put(BaseModel.COLUMN_DESCRIBE, consumerInfo.getDescribe());
            values.put(BaseModel.COLUMN_TYPE, consumerInfo.getType());
            values.put(BaseModel.COLUMN_SUM, consumerInfo.getSum());
            values.put(BaseModel.COLUMN_TIME, consumerInfo.getTime());
            dbw.insert(ConsumerInfos.TABLE_NAME, null, values);
        }
    }

    /**
     * 删除
     * @param id
     */
    public void delect(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + ConsumerInfos.TABLE_NAME, null);
            if(null != cursor && 0 != cursor.getCount()){
                while (cursor.moveToNext()) {
                    String idTp = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_ID));
                    if(id.equals(idTp)){
                        db.delete(ConsumerInfos.TABLE_NAME, BaseModel.COLUMN_ID + " = ?", new String[]{id});
                    }
                }
            }
            cursor.close();
        }
    }

    /**
     * 查询消费记录
     * @param queryId
     * @return
     */
    public List<ConsumerInfos> queryConsumerInfos(String queryId){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<ConsumerInfos> consumerInfosList = new ArrayList<>();

        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + ConsumerInfos.TABLE_NAME, null);
            if(null != cursor && 0 != cursor.getCount()){
                while (cursor.moveToNext()) {
                    String id = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_ID));
                    if(queryId.equals(id)){
                        String holderId = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_HOLDERSID));
                        String describe = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_DESCRIBE));
                        String type = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_TYPE));
                        String sum = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_SUM));
                        String time = cursor.getString(cursor.getColumnIndex(BaseModel.COLUMN_TIME));

                        ConsumerInfos consumerInfos = new ConsumerInfos();
                        consumerInfos.setHoldersId(holderId);
                        consumerInfos.setId(Integer.valueOf(id));
                        consumerInfos.setDescribe(describe);
                        consumerInfos.setType(type);
                        consumerInfos.setSum(sum);
                        consumerInfos.setTime(time);
                        consumerInfosList.add(consumerInfos);
                    }
                }
            }
            cursor.close();
        }
        return consumerInfosList;
    }
}
