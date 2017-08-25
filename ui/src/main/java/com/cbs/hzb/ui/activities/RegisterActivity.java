package com.cbs.hzb.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cbs.hzb.R;
import com.cbs.impl.LoginImpl;
import com.cbs.impl.RegisterImpl;
import com.cbs.model.RequestModel;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by c on 2017/4/12.
 */

public class RegisterActivity extends BaseActivity{

    private EditText et_account;
    private EditText et_password;
    private Button bt_ok;
    private Button bt_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void findViews() {
        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_ok = (Button) findViewById(R.id.bt_ok);
        bt_cancel = (Button) findViewById(R.id.bt_cancel);
    }

    @Override
    protected void init() {
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = et_account.getText().toString();
                String password = et_password.getText().toString();
                RegisterImpl loginImpl = new RegisterImpl(RegisterActivity.this, account, password);
                loginImpl.request(new RequestModel.RequestCallBack() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Intent intent = new Intent();
                        intent.setClass(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(RegisterActivity.this,"注册失败", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
