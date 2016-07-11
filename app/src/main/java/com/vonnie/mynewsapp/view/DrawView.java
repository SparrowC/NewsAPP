package com.vonnie.mynewsapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.vonnie.mynewsapp.R;

/**
 * Created by Kun、 on 2016/2/9.
 */
public class DrawView extends HorizontalScrollView {
    private LinearLayout mWrap;
    private ViewGroup  left_menu,right_content;
    private Context mContext;
    private int left_menuWidth,screenWidth;
    private boolean firstMeasure;
    private boolean isDrawing;
    private int startX,startY;

    public DrawView(Context context) {
        super(context);
        mContext=context;
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        init(context, attrs, 0);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);

    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.DrawView);
        left_menuWidth=typedArray.getInteger(R.styleable.DrawView_menuWidth,200);
        this.setHorizontalScrollBarEnabled(false);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(!firstMeasure)
        {
            WindowManager windowManager= (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display=windowManager.getDefaultDisplay();

            screenWidth=display.getWidth();
            firstMeasure=true;
        }
        mWrap= (LinearLayout) this.getChildAt(0);

        left_menu= (ViewGroup) mWrap.getChildAt(0);
        left_menu.getLayoutParams().width=left_menuWidth;
        right_content= (ViewGroup) mWrap.getChildAt(1);
        right_content.getLayoutParams().width=screenWidth;

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollTo(left_menuWidth, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if(ev.getX()<10)
                {//在屏幕左边滑动
                    startY= (int) ev.getY();
                    isDrawing=true;
                }else {
                    isDrawing=false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(isDrawing)
                {
                    scrollTo(-(int) ev.getX(),0);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return false;
    }


}
