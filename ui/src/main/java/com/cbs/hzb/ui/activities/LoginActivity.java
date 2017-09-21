package com.cbs.hzb.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cbs.common.utils.PreferenceHelper.PreferenceConstant;
import com.cbs.common.utils.SharePreferenceUtils;
import com.cbs.hzb.R;
import com.cbs.impl.LoginImpl;
import com.cbs.model.RequestModel;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by c on 2017/4/12.
 */

public class LoginActivity extends BaseActivity{

    private EditText et_account;
    private EditText et_password;
    private Button bt_login;
    private Button bt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void findViews() {
        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_register = (Button) findViewById(R.id.bt_register);
    }

    @Override
    protected void init() {
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String account = et_account.getText().toString();
                final String password = et_password.getText().toString();
                LoginImpl loginImpl = new LoginImpl(account, password);
                loginImpl.request(new RequestModel.RequestCallBack() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //登陆成功
                        SharePreferenceUtils.setDBParam(LoginActivity.this, PreferenceConstant.LOGIN_USERNAME, account);
                        SharePreferenceUtils.setDBParam(LoginActivity.this, PreferenceConstant.LOGIN_PASSWORD, password);
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        //登陆失败
                    }
                });
            }
        });
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
