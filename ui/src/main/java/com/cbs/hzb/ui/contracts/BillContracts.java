package com.cbs.hzb.ui.contracts;

import android.content.Context;

import com.cbs.bill.data.BillList;
import com.cbs.bill.model.SimpleBill;
import com.cbs.hzb.ui.adapt.BillAdapter;

/**
 * Created by yangshaowei on 2017/3/22.
 *
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

		/**
		 * 通知数据改变
		 */
		public void notifyDataChange(Context context, BillAdapter adapter);

		/**
		 * 加载缓存
		 */
		public void startCacheLoading(Context context);
	}
}
