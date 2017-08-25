package com.cbs.common.model;

import com.cbs.common.db.orm.annotation.Column;
import com.cbs.common.db.orm.annotation.Table;
import com.cbs.common.db.orm.annotation.Unique;

/**
 * 版权		：hzb
 * 项目名	：Android客户端
 * 文件名	：BillMessage.java
 * 描述		：DB 消费信息表(结构)
 * 创建日期	：2017年8月23日
 * 作者		：yangshaowei
 */
@Table(name = ConsumerInfos.TABLE_NAME)
public class ConsumerInfos extends BaseModel{
	public static final String TABLE_NAME = "ConsumerInfos";

	@Unique
	@Column(name = COLUMN_ID)
	protected int id = DEFAULT_VALUE_INTEGER;

	@Column(name = COLUMN_HOLDERSID)
	protected String holdersId = DEFAULT_VALUE_STRING;

	@Column(name = COLUMN_DESCRIBE)
	protected String describe = DEFAULT_VALUE_STRING;

	@Column(name = COLUMN_TYPE)
	protected String type = DEFAULT_VALUE_STRING;

	@Column(name = COLUMN_SUM)
	protected String sum = DEFAULT_VALUE_STRING;

	@Column(name = COLUMN_TIME)
	protected String time = DEFAULT_VALUE_STRING;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHoldersId() {
		return holdersId;
	}

	public void setHoldersId(String holdersId) {
		this.holdersId = holdersId;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
