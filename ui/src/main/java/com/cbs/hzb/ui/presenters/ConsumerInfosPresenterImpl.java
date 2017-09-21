package com.cbs.hzb.ui.presenters;

import android.content.Context;

import com.cbs.bean.ConsumerInfo;
import com.cbs.bill.data.BillList;
import com.cbs.bill.model.SimpleBill;
import com.cbs.hzb.ui.adapt.BillAdapter;
import com.cbs.hzb.ui.contracts.BillContracts;
import com.cbs.hzb.ui.contracts.ConsumerInfosContracts;

import java.util.List;

/**
 * Created by yangshaowei on 2017/5/10.
 */

public class ConsumerInfosPresenterImpl implements ConsumerInfosContracts.Presenter{

    private ConsumerInfosContracts.View mView;
    private Context mContext;
    public ConsumerInfosPresenterImpl(Context context, ConsumerInfosContracts.View mView) {
        this.mView = mView;
        this.mContext = context;
    }

    @Override
    public void calConsumer(List<ConsumerInfo> consumerInfoList) {

    }
}
