package com.cbs.hzb.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cbs.bean.BillItem;
import com.cbs.bill.data.BillList;
import com.cbs.bill.model.SimpleBill;
import com.cbs.hzb.R;
import com.cbs.hzb.ui.adapt.ConsumerInfosAdapter;
import com.cbs.hzb.ui.contracts.ConsumerInfosContracts;
import com.cbs.hzb.ui.presenters.ConsumerInfosPresenterImpl;
import com.cbs.hzb.ui.utils.WrapContentLinearLayoutManager;

import java.util.ArrayList;

/**
 * Created by yangshaowei on 2017/8/24.
 */

public class ConsumerInfosDetailActivity extends BaseActivity implements ConsumerInfosContracts.View{

    private TextView tv_all;
    private TextView tv_title;
    private TextView tv_time;
    private Button bt_add;
    private String billItemId = null;
    private ConsumerInfosAdapter consumerInfosAdapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comsumerinfos_detail);
    }

    @SuppressLint("NewApi")
    @Override
    protected void findViews() {
        tv_all = (TextView) findViewById(R.id.tv_all);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_time = (TextView) findViewById(R.id.tv_time);
        bt_add = (Button)findViewById(R.id.bt_add);
        recyclerView = (RecyclerView) findViewById(R.id.rv_consumerinfoses);
        WrapContentLinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void init() {
        final SimpleBill simpleBill = (SimpleBill) getIntent().getParcelableExtra("SimpleBill");
        billItemId = simpleBill.getId();
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddConsumerInfosActivity.show(ConsumerInfosDetailActivity.this, simpleBill.getId());
            }
        });

    }

    public static void show(Context context, SimpleBill simpleBill){
        Bundle bundle = new Bundle();
        bundle.putParcelable("SimpleBill", simpleBill);
        Intent intent = new Intent(context, ConsumerInfosDetailActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BillList.getBillList().loadBillListFromNet(this, new BillList.SyncLoadBillLisetner() {
            @SuppressLint("NewApi")
            @Override
            public void loaderFinished() {
                ConsumerInfosPresenterImpl consumerInfosPresenter = new ConsumerInfosPresenterImpl(ConsumerInfosDetailActivity.this,ConsumerInfosDetailActivity.this);
                SimpleBill simpleBill = BillList.getBillList().getSimpleBillById(billItemId);
                consumerInfosPresenter.calConsumer(simpleBill.getConsumerInfos());
                consumerInfosAdapter = new ConsumerInfosAdapter(ConsumerInfosDetailActivity.this, simpleBill.getConsumerInfos());
                recyclerView.setAdapter(consumerInfosAdapter);
            }
        });

    }

    @Override
    public void refreshView(String title, String time, String all) {
        tv_title.setText(title);
        tv_time.setText(time);
        tv_all.setText(all);
    }
}
