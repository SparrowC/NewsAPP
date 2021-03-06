package com.vonnie.mynewsapp.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.vonnie.mynewsapp.R;
import com.vonnie.mynewsapp.activity.base.BaseMainContent;
import com.vonnie.mynewsapp.activity.base.impl.MainHomeView;
import com.vonnie.mynewsapp.activity.base.impl.MainNewsView;
import com.vonnie.mynewsapp.activity.base.impl.MainSmartServiceView;
import com.vonnie.mynewsapp.global.MyApplication;
import com.vonnie.mynewsapp.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vonnie on 2016/2/3.
 */
public class MainActivity extends FragmentActivity {
    private SlidingMenu slidingmenulayout;
    private NoScrollViewPager vp_main;
    private RadioButton rb_home, rb_newscenter, rb_smartservice;// rb_gov, rb_setting;
    private Button btn_home, btn_newsCenter, btn_setting, btn_circle;
    private RadioGroup rg_bottom;
    private ImageView iv_userImage;
    private TextView tv_username;

    private String[] mTitle = {"首页", "新闻中心", "智慧服务"};// "圈子", "设置"};
    private List<BaseMainContent> mainViewList;
//    private List<NewsChannel.ShowapiResBodyEntity.ChannelListEntity> channelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_layout);
        initView();
        initData();
    }

    protected void initView() {
        slidingmenulayout = (SlidingMenu) findViewById(R.id.slidingmenulayout);

        vp_main = (NoScrollViewPager) findViewById(R.id.vp_main);
        rg_bottom = (RadioGroup) findViewById(R.id.rg_bottom);

        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_newscenter = (RadioButton) findViewById(R.id.rb_newscenter);
        rb_smartservice = (RadioButton) findViewById(R.id.rb_smartservice);


        MainHomeView homeView = new MainHomeView();
        MainNewsView newsView = new MainNewsView();
        MainSmartServiceView smartServiceView = new MainSmartServiceView();


        mainViewList = new ArrayList<>();
        mainViewList.add(homeView);
        mainViewList.add(newsView);
        mainViewList.add(smartServiceView);

        btn_home = (Button) findViewById(R.id.btn_home);
        btn_newsCenter = (Button) findViewById(R.id.btn_newsCenter);
        btn_setting = (Button) findViewById(R.id.btn_setting);
        btn_circle = (Button) findViewById(R.id.btn_circle);
        iv_userImage = (ImageView) findViewById(R.id.iv_userImage);
        tv_username= (TextView) findViewById(R.id.tv_username);

    }

    public void setBottomVisibility(boolean b) {
        if (b) {//show
            rg_bottom.setVisibility(View.VISIBLE);
        } else {//hide
            rg_bottom.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication application= (MyApplication) getApplication();
        if(application.getUsername()!=null)
            tv_username.setText(application.getUsername());
        else
            tv_username.setText("未登陆");
        application=null;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void initData() {
        vp_main.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        vp_main.setCurrentItem(0);
        rb_home.setChecked(true);

        rb_home.setOnClickListener(new MyOnRadioButtonClickedListener(0));
        rb_newscenter.setOnClickListener(new MyOnRadioButtonClickedListener(1));
        rb_smartservice.setOnClickListener(new MyOnRadioButtonClickedListener(2));

        vp_main.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                RadioButton rb = (RadioButton) rg_bottom.getChildAt(position);
                rb.setChecked(true);
                if (position == 2)
                    setBottomVisibility(false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        //left menu
        btn_home.setOnClickListener(new MyOnButtonClickListener());
        btn_newsCenter.setOnClickListener(new MyOnButtonClickListener());
        btn_circle.setOnClickListener(new MyOnButtonClickListener());
        btn_setting.setOnClickListener(new MyOnButtonClickListener());
        iv_userImage.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        });
        iv_userImage.setClipToOutline(true);


    }

    public SlidingMenu getSlidingMenu() {
        return slidingmenulayout;
    }

    /******
     * 内部类
     *******/
    class MainPagerAdapter extends FragmentPagerAdapter {
        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mainViewList.get(position);
        }

        @Override
        public int getCount() {
            return mainViewList.size();
        }

    }

    private class MyOnRadioButtonClickedListener implements View.OnClickListener {
        private int id;

        public MyOnRadioButtonClickedListener(int i) {
            id = i;
        }

        @Override
        public void onClick(View v) {
            vp_main.setCurrentItem(id, false);
        }
    }

    private class MyOnButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == btn_home) {//登陆
                Intent intent=new Intent(MainActivity.this, RegistActivity.class);
                startActivity(intent);
            }
            if (v == btn_circle) {
                Intent intent=new Intent(MainActivity.this, CircleActivity.class);
                startActivity(intent);
            }
            if (v == btn_newsCenter) {
                vp_main.setCurrentItem(1, false);
            }
            if (v == btn_setting) {
                vp_main.setCurrentItem(4, false);
            }
            slidingmenulayout.toggle();
            setBottomVisibility(true);
        }
    }

}
