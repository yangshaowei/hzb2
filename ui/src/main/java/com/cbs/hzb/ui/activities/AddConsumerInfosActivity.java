package com.cbs.hzb.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cbs.bill.model.SimpleBill;
import com.cbs.hzb.R;

import java.util.ArrayList;

/**
 * Created by yangshaowei on 2017/8/24.
 */

public class AddConsumerInfosActivity extends BaseActivity{

    private String billId;
    private EditText et_describe;
    private EditText et_time;
    private EditText et_sum;
    private Button bt_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comsumerinfos);
    }

    @Override
    protected void findViews() {
        et_describe = (EditText) findViewById(R.id.et_describe);
        et_time = (EditText) findViewById(R.id.et_time);
        et_sum = (EditText) findViewById(R.id.et_sum);
        bt_ok = (Button) findViewById(R.id.bt_ok);
    }

    @Override
    protected void init() {
        billId = getIntent().getStringExtra("billId");
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String des = et_describe.getText().toString();
                String time = et_time.getText().toString();
                String sum = et_sum.getText().toString();

            }
        });
    }

    public static void show(Context context, String billId){
        Bundle bundle = new Bundle();
        bundle.putString("billId", billId);
        Intent intent = new Intent(context, AddConsumerInfosActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
