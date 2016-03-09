package com.vonnie.mynewsapp.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
<<<<<<< HEAD
=======
import android.view.View;
>>>>>>> origin/master

/**
 * Created by Vonnie on 2016/2/26.
 */
public class NoScrollViewPager extends ViewPager{

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
<<<<<<< HEAD

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
=======
>>>>>>> origin/master
}
