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

        }else{

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final String result=NetUtils.PostToServer(Config.getAddCircleURL(name,et_circleInput.getText().toString()));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(result.equals("1"))
                                showMessage("发送成功！");
                            else
                                showMessage("请检查网络！");
                        }
                    });
                }
            }).start();

        }
    }
    public void back(View v)
    {
        this.finish();
    }
}
