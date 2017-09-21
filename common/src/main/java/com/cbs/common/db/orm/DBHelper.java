/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cbs.common.db.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// TODO: Auto-generated Javadoc

/**
 * 描述：手机data/data下面的数据库
 *
 * @version v1.0
 * @date：2013-7-23 上午9:47:10
 */
public class DBHelper extends SQLiteOpenHelper {

    /**
     * The model classes.
     */
    private Class<?>[] modelClasses;
    private static String TAG = "DBHelper";

    /**
     * 初始化一个AbSDDBHelper.
     *
     * @param context      应用context
     * @param name         数据库名
     * @param factory      数据库查询的游标工厂
     * @param version      数据库的新版本号
     * @param modelClasses 要初始化的表的对象
     */
    public DBHelper(Context context, String name, CursorFactory factory,
                    int version, Class<?>[] modelClasses) {
        super(context, name, factory, version);
        Log.d(TAG, "DBHelper.onCreate version = " + version + "; name = "
                + name);
        this.modelClasses = modelClasses;

    }

    /**
     * 描述：表的创建.
     *
     * @param db 数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w(TAG, "DBHelper.onCreate");
        TableHelper.createTablesByClasses(db, this.modelClasses);
        //创建索引
//        createIndex(db, "address", "Bill");
    }

    /**
     * 描述：表的重建.
     *
     * @param db         数据库对象
     * @param oldVersion 旧版本号
     * @param newVersion 新版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d(TAG, "onUpgrade = " + oldVersion + "; newVersion = "
                + newVersion + " ThreadId:" + Thread.currentThread().getId());
    }

    /**
     * 修改表接口，用于增加列
     *
     * @param db
     * @param tableName 表名
     * @param colum     列名
     * @param type      类型
     * @param other     其它,默认值或约束之类;若无其它,填该字段为空.
     */
    private void addColumn(SQLiteDatabase db, String tableName, String colum,
                           String type, String other) {
        StringBuffer sql = new StringBuffer("ALTER TABLE ");
        sql.append(tableName).append(" ADD COLUMN ").append(colum).append(" ")
                .append(type);
        if (null != other) {
            sql.append(" ").append(other);
        }
        sql.append(";");
        db.execSQL(sql.toString());
    }

    /**
     * 重命名表
     *
     * @param db
     * @param oldTableName
     * @param newTableName
     */
    private void renameTable(SQLiteDatabase db, String oldTableName,
                             String newTableName) {
        StringBuffer sql = new StringBuffer("ALTER TABLE ");
        sql.append(oldTableName).append(" RENAME TO  ").append(newTableName)
                .append(";");
        db.execSQL(sql.toString());
    }

    /**
     * 创建索引
     */
    private void createIndex(SQLiteDatabase db, String columnName,
                             String tableName) {
        StringBuffer sql = new StringBuffer("CREATE INDEX IF NOT EXISTS ");
        sql.append(tableName+"_index").append(" ON ").append(tableName).append("(")
                .append(columnName).append(")");
        sql.append(";");
        db.execSQL(sql.toString());
    }

}
