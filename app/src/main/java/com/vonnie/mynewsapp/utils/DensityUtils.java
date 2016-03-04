package com.vonnie.mynewsapp.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * Created by Vonnie on 2016/3/4.
 */
public class DensityUtils {
    public static int dp2px(Context context,float dp)
    {
        float density=context.getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5f);//+0.5 四舍五入
    }

    public static float px2dp(Context context,int px){
        float density=context.getResources().getDisplayMetrics().density;
        return px/density;
    }
}
