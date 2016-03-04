package com.vonnie.mynewsapp.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.vonnie.mynewsapp.R;
import com.vonnie.mynewsapp.activity.MainActivity;

/**
 * 基本的主View基类，有首页、新闻中心、智慧服务、政务、设置 五个子类
 * Created by Vonnie on 2016/2/26.
 */
public class BaseMainContent extends Fragment{
    protected View root;
    protected FrameLayout fl_content;//内容
    protected MainActivity mActivity;
    protected TextView tv_mainTitle;//标题
    protected ImageView iv_menu;//菜单按钮
    public BaseMainContent() {
        mActivity = (MainActivity) getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mActivity = (MainActivity) getContext();
        root=inflater.inflate(R.layout.base_main_layout,null);
        tv_mainTitle= (TextView) root.findViewById(R.id.tv_mainTitle);
        fl_content = (FrameLayout) root.findViewById(R.id.fl_content);
        iv_menu= (ImageView) root.findViewById(R.id.iv_menu);

        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.getSlidingMenu().showMenu();
            }
        });
        initView();
        initData();
        return root;
    }

    protected void initView() {

    }


     protected void initData(){

     }


    public void setTitle(String title)
    {
        tv_mainTitle.setText(title);
    }

//    public View getView()
//    {
//        return root;
//    }
}
