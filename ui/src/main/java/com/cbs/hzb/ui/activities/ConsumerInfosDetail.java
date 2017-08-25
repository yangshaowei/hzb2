package com.cbs.hzb.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.cbs.bean.ConsumerInfo;
import com.cbs.bill.model.SimpleBill;
import com.cbs.hzb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangshaowei on 2017/8/24.
 */

public class ConsumerInfosDetail extends BaseActivity{

    private TextView tv_title;
    private TextView tv_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comsumerinfos_detail);
    }

    @Override
    protected void findViews() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_time = (TextView) findViewById(R.id.tv_time);
    }

    @Override
    protected void init() {
        SimpleBill simpleBill = (SimpleBill) getIntent().getParcelableExtra("SimpleBill");
        tv_title.setText(simpleBill.getTitle());
        tv_time.setText(simpleBill.getCreatTime());
    }

    public static void show(Context context, SimpleBill simpleBill){
        Bundle bundle = new Bundle();
        bundle.putParcelable("SimpleBill", simpleBill);
        bundle.putParcelableArrayList("ConsumerInfo", (ArrayList<? extends Parcelable>) simpleBill.getConsumerInfos());
        Intent intent = new Intent(context, ConsumerInfosDetail.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
