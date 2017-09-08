package com.cbs.common.db.dao;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.cbs.common.BuildConfig;
import com.cbs.common.db.dao.common.BasicDBDao;
import com.cbs.common.db.orm.DBHelper;
import com.cbs.common.db.orm.DbUtils;
import com.cbs.common.utils.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class BillDao extends BasicDBDao {
    private static DBHelper mDb;

    public static DBHelper getDbHelper(Context c) {

        synchronized (BillDao.class) {
            if (mDb == null) {
                String dbName = String.format(DbUtils.DB_NAME_MESSAGE, "");

                //tigger test 将数据库放到sd卡，方便查看
//                if (BuildConfig.DEBUG) {
                    dbName = Environment.getExternalStorageDirectory() + "/hzb/db/" + dbName;
//                }
                mDb = new DBHelper(c, dbName, null, DbUtils.DB_VERSION,
                        DbUtils.DB_CLS_MESSAGE);
            }
            return mDb;
        }
    }

    public static void release() {
        if (mDb != null) {
            Log.d("DAO", "release");
            mDb.close();
            mDb = null;
        }
    }

//    public static int getUnReadMessages() {
//        int unReadCount = 0;
//        if (mDb != null) {
//            SQLiteDatabase db = mDb.getReadableDatabase();
//            Cursor sum = db.rawQuery("select sum(unread_count) from Conversation LEFT OUTER JOIN ConvFlag ON (Conversation.address=ConvFlag.address) where ((slient_date is null or slient_date<=?) and (box_type=? or box_type=? or box_type=?))", new String[]{"0", "1", "8", "16"});
////            Cursor sum = db.rawQuery("select sum(unread_count) from " + Conversation.TABLE_NAME, null);
//            if (sum.moveToNext()) {
//                String sth = sum.getString(0);
//                try {
//                    unReadCount = Integer.parseInt(sth);
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(sum != null &&!sum.isClosed()){
//                sum.close();
//            }
//            Cursor sum2 = db.rawQuery("select sum(unread_count) from " + Conversations.PlatFormConversation.NAME + " where address like ?", new String[]{"%" + "125200024021003451@nfas01azx.pa.rcs1.chinamobile.com" + "%"});
//            if (sum2.moveToNext()) {
//                String sth = sum2.getString(0);
//                try {
//                    unReadCount = unReadCount + Integer.parseInt(sth);
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(sum2 != null &&!sum2.isClosed()){
//                sum2.close();
//            }
//        }
//        return unReadCount;
//    }

    /**
     * 读取InputStream的线程
     */
    private static void printMessage(final InputStream input) {
        new Thread(new Runnable() {
            public void run() {
                Reader reader = new InputStreamReader(input);
                BufferedReader bf = new BufferedReader(reader);
                String line = null;
                try {
                    while ((line = bf.readLine()) != null) {
//                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
