package com.vonnie.mynewsapp.activity.base;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;
import com.vonnie.mynewsapp.R;
import com.vonnie.mynewsapp.activity.MainActivity;
import com.vonnie.mynewsapp.activity.NewsDetailActivity;
import com.vonnie.mynewsapp.beans.NewsDetails;
import com.vonnie.mynewsapp.global.Config;
import com.vonnie.mynewsapp.utils.DateUtils;
import com.vonnie.mynewsapp.utils.NetUtils;
import com.vonnie.mynewsapp.utils.SharedPreferencesUtils;
import com.vonnie.mynewsapp.view.PullRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * PullRefreshListView + ViewPager +ListView
 * * Created by Vonnie on 2016/2/26.
 */
public class ChannelNewsView {
    protected View root;
    private PullRefreshListView lv_newList;
    protected MainActivity mActivity;
    private String channelName,channelID;
    private String timestamp;
    private List<NewsDetails.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity> contentList;
    private RelativeLayout rl_refresh;
    private ImageView iv_refresh;
    private TextView tv_refreshText;
    private boolean isRefreshViewShow=false;
    //轮播View
    private CirclePageIndicator cpi_indicator;
    private ViewPager vp_carousel;
    private TextView tv_carouselTitle;
    private List<ImageView> imageViews;
    private String[] carouselTitles = {"科比", "8VS24", "star"};
    private int[] imageIDs = {R.drawable.kobe, R.drawable.kobe1, R.drawable.kobe2};
    private Handler handler = new Handler();
    private int curIndex = 0;
    private MyRunable mRunable = new MyRunable();
    private int postTime = 3500;
    private boolean isFirstTime = true;

    //在UI线程中执行的
    private class MyRunable implements Runnable {
        @Override
        public void run() {
            curIndex = (curIndex + 1) % 3;
            vp_carousel.setCurrentItem(curIndex);
            handler.postDelayed(mRunable, postTime);
        }
    }

    final private int UI_REFRESH =2;
    final private int LOAD_NEWS_DETAIL_SUCCESS =1;
    final private int LOAD_NEWS_DETAIL_FAIL =0;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case LOAD_NEWS_DETAIL_FAIL:
                    if(contentList==null)
                        showRefreshView();
                    break;
                case LOAD_NEWS_DETAIL_SUCCESS:
                    rl_refresh.setVisibility(View.GONE);
                    iv_refresh.clearAnimation();
                    initData();
                    break;
                case UI_REFRESH:
                    if(isRefreshViewShow)
                    {
                        rl_refresh.setVisibility(View.GONE);
                        iv_refresh.clearAnimation();
                        isRefreshViewShow=false;
                    }
                    refreshView();
                    lv_newList.setListNormal();
                    break;
            }
        }


    };
    private MyNewsListAdapter listAdapter;


    public ChannelNewsView(MainActivity activity, String channelId, String name) {
        mActivity = activity;
        this.channelID=channelId;
        this.channelName=name;
        initView();

    }

    private void initView() {
        root = LayoutInflater.from(mActivity).inflate(R.layout.base_channel_news_layout, null);
        lv_newList = (PullRefreshListView) root.findViewById(R.id.lv_newList);
        rl_refresh = (RelativeLayout) root.findViewById(R.id.rl_refresh);
        iv_refresh = (ImageView) root.findViewById(R.id.iv_refresh);
        tv_refreshText = (TextView) root.findViewById(R.id.tv_refreshText);

        iv_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                ra.setRepeatCount(3);
                ra.setDuration(900);

                iv_refresh.startAnimation(ra);
                tv_refreshText.setText("正在刷新...");
                getDataFromServer(0);
            }
        });
        View view = LayoutInflater.from(mActivity).inflate(R.layout.carousel_layout, null);
        cpi_indicator = (CirclePageIndicator) view.findViewById(R.id.cpi_indicator);
        vp_carousel = (ViewPager) view.findViewById(R.id.vp_carousel);
        tv_carouselTitle = (TextView) view.findViewById(R.id.tv_carouselTitle);

        lv_newList.addHeaderView(view);

        getDataFromLocal();
        getDataFromServer(0);

    }
    //加载数据到界面
    private void initData() {
        initCarousel();

        listAdapter=new MyNewsListAdapter();
        lv_newList.setAdapter(listAdapter);

        lv_newList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                String url = contentList.get(position - 2).getLink();

                intent.putExtra("url", url);
                mActivity.startActivity(intent);
            }
        });

        lv_newList.setOnRefreshListener(new PullRefreshListView.OnRefreshListener() {
            @Override
            public void OnPullRefresh() {
                //向下拉刷新列表时
            }

            @Override
            public void OnReleaseRefresh() {
                //向下拉后，释放手时
            }

            @Override
            public void OnRefreshing() {
                //刷新时
                getDataFromServer(1);
            }
        });

    }

    private void initCarousel() {
        imageViews = new ArrayList<>();
        for (int i = 0; i < imageIDs.length; i++) {
            ImageView iv = new ImageView(mActivity);
            iv.setImageResource(imageIDs[i]);
            imageViews.add(iv);
        }

        vp_carousel.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViews.size();
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
                ImageView iv = imageViews.get(position);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                container.addView(iv);
                return iv;
            }
        });
        vp_carousel.setCurrentItem(0);
        cpi_indicator.setVisibility(View.VISIBLE);
        cpi_indicator.setViewPager(vp_carousel);
        cpi_indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_carouselTitle.setText(carouselTitles[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (isFirstTime) {
            handler.postDelayed(mRunable, postTime);
            isFirstTime = false;
        }

    }



    private void refreshView() {
        listAdapter.notifyDataSetChanged();
        Toast.makeText(mActivity,"刷新成功",Toast.LENGTH_SHORT).show();
    }


    /**
     * 从本地获取数据
     */
    private void getDataFromLocal() {
        String result= SharedPreferencesUtils.getString(mActivity,"url","");
        if(result.equals(""))
            return;
        else {//本地有缓存
            contentList=getContentList(result);
            Message msg=new Message();
            msg.what=LOAD_NEWS_DETAIL_SUCCESS;
            mHandler.sendMessage(msg);
            Log.d("ChannelNews","本地加载。。。");
        }
    }

    /**
     * 通过json数据返回ContentlistEntity的链表
     * @param result
     * @return 如果解析失败返回 null,成功则返回ContentlistEntity的链表
     */
    private List<NewsDetails.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity> getContentList(String result)
    {
        if(!result.equals("")) {
            NewsDetails details = NewsDetails.objectFromData(result);
            if(details!=null)
            {
                NewsDetails.ShowapiResBodyEntity bodyEntity = details.getShowapi_res_body();
                if (bodyEntity != null) {
                    NewsDetails.ShowapiResBodyEntity.PagebeanEntity pagebeanEntity = bodyEntity.getPagebean();
                    if (pagebeanEntity != null) {
                        contentList = pagebeanEntity.getContentlist();
                        if (contentList != null && contentList.size() > 0) {
                            return contentList;
                        }
                    }
                }
            }
        }
        return null;
    }
    /**
     * 从服务器获取数据
     * @param type 0表示加载；1表示刷新
     */
    private void getDataFromServer(final int type) {
        //加载网络数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                timestamp= DateUtils.getCurTimestamp();

                Message msg=new Message();

                String url = Config.getChannelNewsUrl(channelID, channelName, timestamp);

                String result= NetUtils.LoadJsonDataFromServer(url);

                List<NewsDetails.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity> temp = getContentList(result);

                if(temp!=null)
                {
                    contentList=temp;
                    SharedPreferencesUtils.setString(mActivity,"url",result);
                    if(type==0)
                        msg.what=LOAD_NEWS_DETAIL_SUCCESS;
                    if(type==1)
                        msg.what=UI_REFRESH;
                    Log.d("ChannelNews","网络加载。。。");
                }else
                    msg.what=LOAD_NEWS_DETAIL_FAIL;

                mHandler.sendMessage(msg);
            }
        }).start();
    }


    public View getView()
    {
        return root;
    }

    private void showRefreshView() {
        isRefreshViewShow=true;
        tv_refreshText.setText("加载失败，点击刷新");
        iv_refresh.clearAnimation();
        rl_refresh.setVisibility(View.VISIBLE);
    }




    private class MyNewsListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return contentList.size();
        }

        @Override
        public Object getItem(int position) {
            return contentList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if(convertView==null){
                convertView=LayoutInflater.from(mActivity).inflate(R.layout.news_list_item_layout,null);
                holder=new ViewHolder();
                holder.tv_newsDesc= (TextView) convertView.findViewById(R.id.tv_newsDesc);
                holder.tv_newTitle= (TextView) convertView.findViewById(R.id.tv_newTitle);
                convertView.setTag(holder);
            }else {
                holder= (ViewHolder) convertView.getTag();
            }

            holder.tv_newsDesc.setText(contentList.get(position).getDesc());
            holder.tv_newTitle.setText(contentList.get(position).getTitle());

            return convertView;
        }
    }

    private class ViewHolder{
        TextView tv_newTitle,tv_newsDesc;
    }
}
