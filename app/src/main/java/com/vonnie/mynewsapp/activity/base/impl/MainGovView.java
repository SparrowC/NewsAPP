package com.vonnie.mynewsapp.activity.base.impl;

import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.vonnie.mynewsapp.activity.MainActivity;
import com.vonnie.mynewsapp.activity.base.BaseMainContent;

/**
 * Created by vonnie on 2016/2/26.
 */
public class MainGovView extends BaseMainContent {

    public MainGovView() {
        super();
    }

    @Override
    protected void initData() {
        setTitle("政务");
        TextView text = new TextView(mActivity);
        text.setText("政务");
        text.setTextColor(Color.RED);
        text.setTextSize(25);
        text.setGravity(Gravity.CENTER);

        // 向FrameLayout中动态添加布局
        fl_content.addView(text);

    }
}
