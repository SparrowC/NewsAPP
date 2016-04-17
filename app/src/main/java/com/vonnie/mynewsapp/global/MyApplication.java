package com.vonnie.mynewsapp.global;

import android.app.Application;

import com.vonnie.mynewsapp.utils.SharedPreferencesUtils;

/**
 * Created by Vonnie on 2016/4/17.
 */
public class MyApplication extends Application {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String name=SharedPreferencesUtils.getString(this.getApplicationContext(),"username","");
       if(!name.equals(""))
           setUsername(name);
        else
           setUsername(null);
    }
}
