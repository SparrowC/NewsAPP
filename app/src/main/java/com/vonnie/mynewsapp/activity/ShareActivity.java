package com.vonnie.mynewsapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.vonnie.mynewsapp.R;
import com.vonnie.mynewsapp.global.Config;
import com.vonnie.mynewsapp.utils.NetUtils;
import com.vonnie.mynewsapp.utils.SharedPreferencesUtils;

/**
 * Created by Vonnie on 2016/4/17.
 */
public class ShareActivity extends BaseActivity {

    private EditText et_circleInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_circle_layout);
        et_circleInput= (EditText) findViewById(R.id.et_circleInput);

    }
    public void send(View v)
    {
        final String name=SharedPreferencesUtils.getString(this,"username","");
        if(name.equals("")){
            showMessage("请先登录！");
            startActivity(RegistActivity.class);
            this.finish();
        }else{
            String input=et_circleInput.getText().toString();
            if(!input.equals("")){
                NetUtils.SyncPost(this, Config.getAddCircleURL(name, input), new NetUtils.NetWork() {
                    @Override
                    public void onSuccess(String result) {
                        if (result.equals("1")) {
                            showMessage("发布成功！");
                            ShareActivity.this.finish();
                        } else
                            showMessage("服务器正在维护中，稍后再试！");
                    }

                    @Override
                    public void onFailed(String result) {
                        showMessage("请检查网络！");
                    }
                });
            }else
                showMessage("输入不能为空！");

        }
    }
    public void back(View v)
    {
        this.finish();
    }
}
