package com.cbs.hzb.ui.contracts;

import android.content.Context;

import com.cbs.bean.ConsumerInfo;
import com.cbs.bill.data.BillList;
import com.cbs.bill.model.SimpleBill;
import com.cbs.hzb.ui.adapt.BillAdapter;

import java.util.List;

/**
 * Created by yangshaowei on 2017/3/22.
 *
 */

public interface ConsumerInfosContracts {
	interface View {
		/**
		 * 刷新界面
		 * @param
		 */
		public void refreshView(String title, String time, String all);

	}

	interface Presenter {

		/**
		 * 加载缓存
		 */
		public void calConsumer(List<ConsumerInfo> consumerInfoList);
	}
}
