package com.cbs.common.model;

import com.cbs.common.db.orm.annotation.Column;
import com.cbs.common.db.orm.annotation.Table;
import com.cbs.common.db.orm.annotation.Unique;

/**
 * 版权		：hzb
 * 项目名	：Android客户端
 * 文件名	：BillMessage.java
 * 描述		：DB 账单表(结构)
 * 创建日期	：2017年8月23日
 * 作者		：yangshaowei
 */
@Table(name = BillMessage.TABLE_NAME)
public class BillMessage extends BaseModel{
	public static final String TABLE_NAME = "BillMessage";

	@Unique
	@Column(name = COLUMN_ID)
	protected int id = DEFAULT_VALUE_INTEGER;

	@Column(name = COLUMN_HOLDERSID)
	protected String holdersId = DEFAULT_VALUE_STRING;

	@Column(name = COLUMN_ISBALANCE)
	protected boolean isBalance = DEFAULT_VALUE_BOOLEAN;

	@Column(name = COLUMN_DAYCONSUME)
	protected String dayConsume = DEFAULT_VALUE_STRING;

	@Column(name = COLUMN_MONCONSUME)
	protected String monConsume = DEFAULT_VALUE_STRING;

	@Column(name = COLUMN_ALLCONSUME)
	protected String allConsume = DEFAULT_VALUE_STRING;

	@Column(name = COLUMN_TITLE)
	protected String title = DEFAULT_VALUE_STRING;

	@Column(name = COLUMN_CREATETIME)
	protected String createTime = DEFAULT_VALUE_STRING;

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

	public boolean isBalance() {
		return isBalance;
	}

	public void setBalance(boolean balance) {
		isBalance = balance;
	}

	public String getDayConsume() {
		return dayConsume;
	}

	public void setDayConsume(String dayConsume) {
		this.dayConsume = dayConsume;
	}

	public String getMonConsume() {
		return monConsume;
	}

	public void setMonConsume(String monConsume) {
		this.monConsume = monConsume;
	}

	public String getAllConsume() {
		return allConsume;
	}

	public void setAllConsume(String allConsume) {
		this.allConsume = allConsume;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
