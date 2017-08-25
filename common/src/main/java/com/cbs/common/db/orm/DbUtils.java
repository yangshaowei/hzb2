/*
* 文件名：DbUtils.java
* 描    述：数据库静态变量工具类
* 作    者：wangsongchao
* 时    间：2014-09-29
* 版    权：中软国际有限公司
*/
package com.cbs.common.db.orm;

import com.cbs.common.model.BillMessage;
import com.cbs.common.model.ConsumerInfos;

public class DbUtils {
    /**
     * 新消息数据库名称
     */
    public static final String DB_NAME_MESSAGE = "hzb-%s.db";

    /**
     * 数据库版本号
     */

    public static final int DB_VERSION = 1;

    /**
     * 新消息数据库用到的类(反射需要的类)
     */
    public static final Class<?>[] DB_CLS_MESSAGE = new Class[]{
            BillMessage.class, ConsumerInfos.class
    };

}
