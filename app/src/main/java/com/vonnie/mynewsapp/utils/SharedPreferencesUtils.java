package com.vonnie.mynewsapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kung、 on 2016/2/3.
 */
public class SharedPreferencesUtils {

    private static final String NAME="config";

    public static void setBoolean(Context context, String key, boolean value)
    {
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    public static boolean getBoolean(Context context,String key,boolean defaultValue)
    {
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getBoolean(key,defaultValue);
    }

    public static void setString(Context context, String key, String value){
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
    public static String getString(Context context,String key,String defaultValue)
    {
        SharedPreferences sp=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sp.getString(key,defaultValue);
    }

}
