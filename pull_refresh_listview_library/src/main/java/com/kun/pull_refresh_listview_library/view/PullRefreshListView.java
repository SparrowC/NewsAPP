package com.kun.pull_refresh_listview_library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import com.kun.pull_refresh_listview_library.*;

/**
 * Created by Vonnie on 2016/3/1.
 */
public class PullRefreshListView extends ListView {
    private Context mContext;
    public PullRefreshListView(Context context) {
        super(context);
        initHeaderView(context);
    }



    public PullRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView(context);
    }

    public PullRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeaderView(context);
    }

    /**
     * 初始化头View
     * @param context
     */
    private void initHeaderView(Context context) {
        mContext=context;
    }

}
