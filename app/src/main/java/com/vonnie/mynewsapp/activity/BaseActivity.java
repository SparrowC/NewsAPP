package com.vonnie.mynewsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Window;
import android.widget.Toast;

/**
 * Created by vonnie on 2016/2/3.
 */
public class BaseActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }


    protected void startActivity(Class<?> cls){
        Intent intent=new Intent(this,cls);
        startActivity(intent);
    }

    public void showMessage(String text)
    {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    protected void initView()
    {

    }

    protected void initData()
    {

    }
}
