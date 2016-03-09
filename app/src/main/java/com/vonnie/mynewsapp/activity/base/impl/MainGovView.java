package com.vonnie.mynewsapp.activity.base.impl;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.viewpagerindicator.CirclePageIndicator;
import com.vonnie.mynewsapp.R;
import com.vonnie.mynewsapp.activity.base.BaseMainContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vonnie on 2016/2/26.
 */
public class MainGovView extends BaseMainContent {
    private CirclePageIndicator cpi_indicator;
    private ViewPager vp_carousel;
    private List<ImageView> imageViews = new ArrayList<>();

    public MainGovView() {
        super();
    }

    @Override
    protected void initView() {
        View view = View.inflate(mActivity, R.layout.carousel_layout, null);
        cpi_indicator = (CirclePageIndicator) view.findViewById(R.id.cpi_indicator);
        vp_carousel = (ViewPager) view.findViewById(R.id.vp_carousel);

        fl_content.addView(view);

    }

    @Override
    protected void initData() {
        setTitle("政务");

        ImageView iv1 = new ImageView(mActivity);
        iv1.setImageResource(R.drawable.kobe);
        ImageView iv2 = new ImageView(mActivity);
        iv2.setImageResource(R.drawable.kobe1);
        imageViews.add(iv1);
        imageViews.add(iv2);
        vp_carousel.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(imageViews.get(position));
                return imageViews.get(position);
            }
        });
        cpi_indicator.setVisibility(View.VISIBLE);
        cpi_indicator.setViewPager(vp_carousel);
//        TextView text = new TextView(mActivity);
//        text.setText("政务");
//        text.setTextColor(Color.RED);
//        text.setTextSize(25);
//        text.setGravity(Gravity.CENTER);
//
//        // 向FrameLayout中动态添加布局
//        fl_content.addView(text);

    }
}
