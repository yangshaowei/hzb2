package com.cbs.hzb.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cbs.bean.BillItem;
import com.cbs.bean.ConsumerInfo;
import com.cbs.bill.data.BillList;
import com.cbs.bill.model.SimpleBill;
import com.cbs.common.utils.PreferenceHelper.PreferenceConstant;
import com.cbs.common.utils.SharePreferenceUtils;
import com.cbs.domain.ResponeData;
import com.cbs.hzb.R;
import com.cbs.hzb.ui.presenters.AddConsumerInfosPresenterImpl;
import com.cbs.impl.UpdateBillImpl;
import com.cbs.model.RequestModel;
import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by yangshaowei on 2017/8/24.
 */

public class AddConsumerInfosActivity extends BaseActivity{

    private String billId;
    private EditText et_describe;
    private EditText et_time;
    private EditText et_sum;
    private Button bt_ok;
    private AddConsumerInfosPresenterImpl addConsumerInfosPresenter;
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
        addConsumerInfosPresenter = new AddConsumerInfosPresenterImpl();
        addConsumerInfosPresenter.initCurrentTime(et_time);
        billId = getIntent().getStringExtra("billId");
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BillItem billItem = addConsumerInfosPresenter.getModifyBillItem((String) SharePreferenceUtils.getDBParam(AddConsumerInfosActivity.this, PreferenceConstant.LOGIN_USERNAME, null),
                        et_describe.getText().toString(),
                        et_sum.getText().toString(),
                        et_time.getText().toString(),
                        "0");
                UpdateBillImpl updateBill = new UpdateBillImpl((String) SharePreferenceUtils.getDBParam(AddConsumerInfosActivity.this, PreferenceConstant.LOGIN_USERNAME, null),
                        (String) SharePreferenceUtils.getDBParam(AddConsumerInfosActivity.this, PreferenceConstant.LOGIN_PASSWORD, null),
                        billId,
                        billItem);
                updateBill.request(new RequestModel.RequestCallBack() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        ResponeData responeData = gson.fromJson(s, ResponeData.class);
                        if(responeData.isFlag()){
                            Toast.makeText(AddConsumerInfosActivity.this,"添加成功",Toast.LENGTH_LONG).show();
                            finish();
                        }else {
                            Toast.makeText(AddConsumerInfosActivity.this,"添加失败",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {

                    }
                });
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
