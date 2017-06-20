package com.cbs.hzb.ui.contracts;

import android.content.Context;

import com.cbs.bill.data.BillList;
import com.cbs.bill.model.SimpleBill;

/**
 * Created by yangshaowei on 2017/3/22.
 * 搜索联系人
 */

public interface BillContracts {
	interface View {
		/**
		 * 列表显示
		 * @param
		 */
		public void showBillItem(BillList bills);

	}

	interface Presenter {

		/**
		 * 条目被点击
		 * @param context
		 * @param simpleBill
		 */
		public void itemClick(Context context, SimpleBill simpleBill);

		/**
		 * 获取列表
		 */
		public BillList getBillList();
	}
}
