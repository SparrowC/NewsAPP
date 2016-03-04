package com.vonnie.mynewsapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.vonnie.mynewsapp.R;
import com.vonnie.mynewsapp.activity.fragment.GuideFragment1;
import com.vonnie.mynewsapp.activity.fragment.GuideFragment2;
import com.vonnie.mynewsapp.activity.fragment.GuideFragment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vonnie on 2016/2/3.
 */
public class GuideActivity extends FragmentActivity {
    private ImageView iv_grayBall1,iv_grayBall2,iv_redBall;
    private ViewPager vp_guide;
    private List<Fragment> fragmentList;
    private int ball_distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.guide_layout);
        initView();
        initListener();
    }

    private void initView() {
        fragmentList=new ArrayList<>();

        vp_guide= (ViewPager) findViewById(R.id.vp_guide);

        GuideFragment1 fragment1=new GuideFragment1();
        GuideFragment2 fragment2=new GuideFragment2();
        GuideFragment3 fragment3=new GuideFragment3();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);

        iv_grayBall1= (ImageView) findViewById(R.id.iv_grayBall1);
        iv_grayBall2= (ImageView) findViewById(R.id.iv_grayBall2);
        iv_redBall= (ImageView) findViewById(R.id.iv_redBall);

        iv_grayBall1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ball_distance=iv_grayBall2.getLeft()-iv_grayBall1.getLeft();
            }
        });


    }


    private void initListener() {
        vp_guide.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        vp_guide.setCurrentItem(0);

        vp_guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) iv_redBall.getLayoutParams();
                params.leftMargin= (int) ((v+i)*ball_distance);
                iv_redBall.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }



}
