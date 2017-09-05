package com.cbs.hzb.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cbs.bill.model.SimpleBill;
import com.cbs.hzb.R;

import java.util.ArrayList;

/**
 * Created by yangshaowei on 2017/8/24.
 */

public class ConsumerInfosDetailActivity extends BaseActivity{

    private TextView tv_title;
    private TextView tv_time;
    private Button bt_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comsumerinfos_detail);
    }

    @Override
    protected void findViews() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_time = (TextView) findViewById(R.id.tv_time);
        bt_add = (Button)findViewById(R.id.bt_add);
    }

    @Override
    protected void init() {
        final SimpleBill simpleBill = (SimpleBill) getIntent().getParcelableExtra("SimpleBill");
        tv_title.setText(simpleBill.getTitle());
        tv_time.setText(simpleBill.getCreatTime());
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
        bundle.putParcelableArrayList("ConsumerInfo", (ArrayList<? extends Parcelable>) simpleBill.getConsumerInfos());
        Intent intent = new Intent(context, ConsumerInfosDetailActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
