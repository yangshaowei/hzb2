package com.cbs.hzb.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    }

    @Override
    protected void init() {
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = et_account.getText().toString();
                String password = et_password.getText().toString();
                LoginImpl loginImpl = new LoginImpl(account, password);
                loginImpl.request(new RequestModel.RequestCallBack() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //登陆成功
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        //登陆失败
                    }
                });
            }
        });

    }
}
