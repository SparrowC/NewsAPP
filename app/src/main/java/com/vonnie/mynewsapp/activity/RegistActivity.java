package com.vonnie.mynewsapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.vonnie.mynewsapp.R;
import com.vonnie.mynewsapp.activity.BaseActivity;
import com.vonnie.mynewsapp.global.Config;
import com.vonnie.mynewsapp.global.MyApplication;
import com.vonnie.mynewsapp.utils.NetUtils;
import com.vonnie.mynewsapp.utils.SharedPreferencesUtils;

/**
 * Created by Vonnie on 2016/4/17.
 */
public class RegistActivity extends BaseActivity {
    private Button btn_regist,btn_login;
    private EditText et_password,et_username;
    private ProgressBar pb_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_layout);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        btn_regist= (Button) findViewById(R.id.btn_regist);
        btn_login= (Button) findViewById(R.id.btn_login);
        et_username= (EditText) findViewById(R.id.et_username);
        et_password= (EditText) findViewById(R.id.et_password);
        pb_login= (ProgressBar) findViewById(R.id.pb_login);
    }

    @Override
    protected void initData() {
        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb_login.setVisibility(View.VISIBLE);
                NetUtils.SyncPost(RegistActivity.this, Config.getRegistURL(et_username.getText().toString(), et_password.getText().toString()), new NetUtils.NetWork() {
                    @Override
                    public void onSuccess(String result) {
                        if (result.equals("1"))
                        {
                            showMessage("注册成功！");
                            MyApplication application= (MyApplication) getApplication();
                            application.setUsername(et_username.getText().toString());
                            SharedPreferencesUtils.setString(RegistActivity.this,"username",et_username.getText().toString());
                            RegistActivity.this.finish();
                        }
                        else
                            showMessage("用户已存在！");
                        pb_login.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onFailed(String result) {
                        showMessage("请检查网络");
                        pb_login.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb_login.setVisibility(View.VISIBLE);
                NetUtils.SyncPost(RegistActivity.this, Config.getLoginURL(et_username.getText().toString(), et_password.getText().toString()), new NetUtils.NetWork() {
                    @Override
                    public void onSuccess(String result) {
                        if (result.equals("1"))
                        {
                            showMessage("登陆成功！");
                            MyApplication application= (MyApplication) getApplication();
                            application.setUsername(et_username.getText().toString());
                            SharedPreferencesUtils.setString(RegistActivity.this,"username",et_username.getText().toString());
                            RegistActivity.this.finish();
                        }
                        else
                            showMessage("用户不存在或密码错误！");
                        pb_login.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onFailed(String result) {
                        showMessage("请检查网络");
                        pb_login.setVisibility(View.INVISIBLE);

                    }
                });
            }
        });
    }

    public void back(View v)
    {
        this.finish();
    }
}
