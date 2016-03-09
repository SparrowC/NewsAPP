package com.vonnie.mynewsapp.activity;

<<<<<<< HEAD
import android.annotation.TargetApi;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
=======
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
>>>>>>> origin/master
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
<<<<<<< HEAD
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
=======
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.TextView;
>>>>>>> origin/master

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.vonnie.mynewsapp.R;
import com.vonnie.mynewsapp.activity.base.BaseMainContent;
import com.vonnie.mynewsapp.activity.base.impl.MainGovView;
import com.vonnie.mynewsapp.activity.base.impl.MainHomeView;
import com.vonnie.mynewsapp.activity.base.impl.MainNewsView;
import com.vonnie.mynewsapp.activity.base.impl.MainSettingView;
import com.vonnie.mynewsapp.activity.base.impl.MainSmartServiceView;
import com.vonnie.mynewsapp.beans.NewsChannel;
<<<<<<< HEAD
import com.vonnie.mynewsapp.view.NoScrollViewPager;

=======
import com.vonnie.mynewsapp.utils.NetUtils;

import java.io.Serializable;
>>>>>>> origin/master
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vonnie on 2016/2/3.
 */
public class MainActivity extends FragmentActivity {
    private SlidingMenu slidingmenulayout;
<<<<<<< HEAD
    private NoScrollViewPager vp_main;
    private RadioButton rb_home,rb_newscenter,rb_smartservice,rb_gov,rb_setting;
    private Button btn_home, btn_newsCenter, btn_setting, btn_menuShare;
    private RadioGroup rg_bottom;
    private ImageView iv_userImage;
=======
    private ViewPager vp_main;
    private RadioButton rb_home,rb_newscenter,rb_smartservice,rb_gov,rb_setting;
>>>>>>> origin/master
    private String[] mTitle ={"首页","新闻中心","智慧服务","政务","设置"};
    private List<BaseMainContent> mainViewList;
    private List<NewsChannel.ShowapiResBodyEntity.ChannelListEntity> channelList;

    @Override
         protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_layout);
        initView();
        initData();
    }

<<<<<<< HEAD
    protected void initView() {
        slidingmenulayout= (SlidingMenu) findViewById(R.id.slidingmenulayout);

        vp_main = (NoScrollViewPager) findViewById(R.id.vp_main);
        rg_bottom = (RadioGroup) findViewById(R.id.rg_bottom);

        //menu button
        btn_home = (Button) findViewById(R.id.btn_home);
        btn_newsCenter = (Button) findViewById(R.id.btn_newsCenter);
        btn_setting = (Button) findViewById(R.id.btn_setting);
        btn_menuShare = (Button) findViewById(R.id.btn_menuShare);
        iv_userImage = (ImageView) findViewById(R.id.iv_userImage);
=======



//    @Override
//    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        setContentView(R.layout.main_layout);
//        initView();
//        initData();
//    }

    protected void initView() {
        slidingmenulayout= (SlidingMenu) findViewById(R.id.slidingmenulayout);

        vp_main= (ViewPager) findViewById(R.id.vp_main);

>>>>>>> origin/master

        rb_home= (RadioButton) findViewById(R.id.rb_home);
        rb_newscenter= (RadioButton) findViewById(R.id.rb_newscenter);
        rb_smartservice= (RadioButton) findViewById(R.id.rb_smartservice);
        rb_gov= (RadioButton) findViewById(R.id.rb_gov);
        rb_setting= (RadioButton) findViewById(R.id.rb_setting);

        MainHomeView homeView=new MainHomeView();
        MainNewsView newsView=new MainNewsView();
        MainSmartServiceView smartServiceView=new MainSmartServiceView();
        MainGovView govView=new MainGovView();
        MainSettingView settingView= new MainSettingView();

        mainViewList=new ArrayList<>();
       mainViewList.add(homeView);
       mainViewList.add(newsView);
       mainViewList.add(smartServiceView);
       mainViewList.add(govView);
       mainViewList.add(settingView);
<<<<<<< HEAD
    }

    public void setBottomVisibility(boolean b) {
        if (b) {//show
            rg_bottom.setVisibility(View.VISIBLE);
        } else {//hide
            rg_bottom.setVisibility(View.GONE);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void initData() {
        vp_main.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        vp_main.setCurrentItem(0);
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

=======

    }

    protected void initData() {
        vp_main.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        vp_main.setCurrentItem(0);
>>>>>>> origin/master
        rb_home.setChecked(true);

        rb_home.setOnClickListener(new MyOnRadioButtonClickedListener(0));
        rb_newscenter.setOnClickListener(new MyOnRadioButtonClickedListener(1));
        rb_smartservice.setOnClickListener(new MyOnRadioButtonClickedListener(2));
        rb_gov.setOnClickListener(new MyOnRadioButtonClickedListener(3));
        rb_setting.setOnClickListener(new MyOnRadioButtonClickedListener(4));
<<<<<<< HEAD

        //left menu
        btn_home.setOnClickListener(new MyOnButtonClickListener());
        btn_newsCenter.setOnClickListener(new MyOnButtonClickListener());
        btn_menuShare.setOnClickListener(new MyOnButtonClickListener());
        btn_setting.setOnClickListener(new MyOnButtonClickListener());
        iv_userImage.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        });
        iv_userImage.setClipToOutline(true);
=======
>>>>>>> origin/master
    }

    public SlidingMenu getSlidingMenu()
    {
        return slidingmenulayout;
    }

<<<<<<< HEAD

=======
>>>>>>> origin/master
    /******内部类*******/
    class MainPagerAdapter extends FragmentPagerAdapter{
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

<<<<<<< HEAD
=======
//        @Override
//        public int getCount() {
//            return mainViewList.size();
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view==object;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            container.addView(mainViewList.get(position).getView());
//            return mainViewList.get(position).getView();
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            container.removeView(mainViewList.get(position).getView());
//        }
>>>>>>> origin/master
    }

    private class MyOnRadioButtonClickedListener implements View.OnClickListener {
        private int id;
        public MyOnRadioButtonClickedListener(int i) {
            id=i;
        }

        @Override
        public void onClick(View v) {
            vp_main.setCurrentItem(id,false);
        }
    }
<<<<<<< HEAD

    private class MyOnButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == btn_home) {
                vp_main.setCurrentItem(0, false);
            }
            if (v == btn_menuShare) {

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
=======
>>>>>>> origin/master
}
