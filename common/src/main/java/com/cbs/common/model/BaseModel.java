package com.cbs.common.model;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.text.TextUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.LinkedList;

/**
 * 版权		：hzb
 * 项目名	：Android客户端
 * 文件名	：BaseModel.java
 * 描述		：
 * 作者		：yangshaowei
 */
@SuppressLint("DefaultLocale")
public class BaseModel implements Serializable {
    /**
     * 默认string 值
     */
    public static final String DEFAULT_VALUE_STRING = "";
    /**
     * 默认int 值
     */
    public static final int DEFAULT_VALUE_INTEGER = -1;

    /**
     * 默认boolean 值
     */
    public static final boolean DEFAULT_VALUE_BOOLEAN = false;

    /**
     * DB字段名
     */
    public static final String COLUMN_UNIQUE_ID = " uniqueId";

    /**
     * DB字段名
     */
    public static final String COLUMN_ID = "id";

    /**
     * DB字段名
     */
    public static final String COLUMN_HOLDERSID = "holdersId";

    /**
     * DB字段名
     */
    public static final String COLUMN_ISBALANCE = "isBalance";

    /**
     * DB字段名
     */
    public static final String COLUMN_DAYCONSUME = "dayConsume";

    /**
     * DB字段名
     */
    public static final String COLUMN_MONCONSUME = "monConsume";

    /**
     * DB字段名
     */
    public static final String COLUMN_ALLCONSUME = "allConsume";

    /**
     * DB字段名
     */
    public static final String COLUMN_TITLE = "title";

    /**
     * DB字段名
     */
    public static final String COLUMN_CREATETIME = "createTime";

    /**
     * DB字段名
     */
    public static final String COLUMN_DESCRIBE = "describe";

    /**
     * DB字段名
     */
    public static final String COLUMN_TYPE = "type";

    /**
     * DB字段名
     */
    public static final String COLUMN_SUM = "sum";

    /**
     * DB字段名
     */
    public static final String COLUMN_TIME = "time";

    /**
     * DB字段名
     */
    public static final String COLUMN_CID = "cid";

}
