package com.vonnie.mynewsapp.activity.base.impl;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.vonnie.mynewsapp.activity.MainActivity;
import com.vonnie.mynewsapp.activity.base.BaseMainContent;

/**
 * Created by vonnie on 2016/2/26.
 */
public class MainHomeView extends BaseMainContent {

    public MainHomeView() {
        super();
    }

    @Override
    protected void initData() {
        setTitle("首页");
        TextView text = new TextView(mActivity);
        text.setText("首页");
        text.setTextColor(Color.RED);
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);

        // 向FrameLayout中动态添加布局
        fl_content.addView(text);

    }
}
