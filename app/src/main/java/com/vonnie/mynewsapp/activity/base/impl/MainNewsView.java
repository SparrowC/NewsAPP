package com.vonnie.mynewsapp.activity.base.impl;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.viewpagerindicator.TabPageIndicator;
import com.vonnie.mynewsapp.R;
import com.vonnie.mynewsapp.activity.base.BaseMainContent;
import com.vonnie.mynewsapp.activity.base.ChannelNewsView;
import com.vonnie.mynewsapp.beans.NewsChannel;
import com.vonnie.mynewsapp.utils.DateUtils;
import com.vonnie.mynewsapp.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vonnie on 2016/2/26.
 */
public class MainNewsView extends BaseMainContent {
    private ViewPager vp_newsMain;
    private TabPageIndicator tpi_title;
    private List<NewsChannel.ShowapiResBodyEntity.ChannelListEntity> channelList;
    private List<ChannelNewsView> channelNewsViewList;
    private String timestamp="20160227172600";
    //String test="https://route.showapi.com/109-35?channelId=5572a108b3cdc86cf39001cd&channelName=%E5%9B%BD%E5%86%85%E7%84%A6%E7%82%B9&needContent=0&needHtml=0&page=1&showapi_appid=16363&showapi_timestamp=20160302210534&title=%E6%96%B0%E9%97%BB&showapi_sign=56f27f8086be4e989a25b7d231a1287b"
    private String[] newsChannelNames={"国内焦点","国内最新","国际最新","科技最新"};
    private String[] newsChannelIDs={"5572a108b3cdc86cf39001cd","5572a109b3cdc86cf39001db","5572a109b3cdc86cf39001de","5572a10ab3cdc86cf39001f4"};
    //private String[] newsChannelIDs={"china_focus","china_news","international","IT_news"};

    final private int LOAD_FAILED=0;
    final private int LOAD_CHANNEL_FINISH=1;
    final private int LOAD_NEWS_FINISH=2;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case LOAD_FAILED:
                    Toast.makeText(mActivity,"服务器正在维护中",Toast.LENGTH_SHORT).show();
                case LOAD_CHANNEL_FINISH:
                    showView();
                    break;
                case  LOAD_NEWS_FINISH:
                    break;
            }
        }
    };


    public MainNewsView() {
        super();
    }

    @Override
    protected void initView() {
        super.initView();
        View view=View.inflate(mActivity,R.layout.main_news_layout,null);
        vp_newsMain= (ViewPager) view.findViewById(R.id.vp_newsMain);
        tpi_title= (TabPageIndicator) view.findViewById(R.id.tpi_title);

        channelNewsViewList=new ArrayList<>();

        fl_content.addView(view);
    }

    @Override
    protected void initData() {
        setTitle("新闻中心");
        loadData2();

    }

    private void showView() {
        //初始化channelNewsViewList
        for (int i=0;i<channelList.size();i++)
        {
            ChannelNewsView newsView=new ChannelNewsView(mActivity,channelList.get(i).getChannelId(),channelList.get(i).getName());
            channelNewsViewList.add(newsView);
        }

        vp_newsMain.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return channelList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(channelNewsViewList.get(position).getView());
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(channelNewsViewList.get(position).getView());
                return channelNewsViewList.get(position).getView();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return channelList.get(position).getName();
            }
        });

        tpi_title.setViewPager(vp_newsMain);
        tpi_title.setVisibility(View.VISIBLE);
        tpi_title.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    //通过网络获取新闻频道
    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();

                timestamp=DateUtils.getCurTimestamp();
                //timestamp="20160229212100";
                String url="https://route.showapi.com/109-34?showapi_appid=2992&showapi_timestamp="+timestamp+"&showapi_sign=ccd3b9a741a94c8d9cf1e72332d311ca";
                String result= NetUtils.LoadJsonDataFromServer(url);
                if(!result.equals(""))
                {
                    NewsChannel channel=NewsChannel.objectFromData(result);
                    channelList=channel.getShowapi_res_body().getChannelList();
                    Message msg=new Message();
                    if(channelList==null)
                    {
                        msg.what=LOAD_FAILED;
                    }else {

                        msg.what=LOAD_CHANNEL_FINISH;
                    }
                    mHandler.sendMessage(msg);
                }
            }
        }).start();
    }

    //将新闻频道写死
    private void loadData2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                channelList=new ArrayList<NewsChannel.ShowapiResBodyEntity.ChannelListEntity>();
                for(int i=0;i<4;i++)
                {
                    NewsChannel.ShowapiResBodyEntity.ChannelListEntity entity=new NewsChannel.ShowapiResBodyEntity.ChannelListEntity();
                    entity.setChannelId(newsChannelIDs[i]);
                    entity.setName(newsChannelNames[i]);
                    channelList.add(entity);
                }
                Message msg=new Message();
                if(channelList==null)
                {
                    msg.what=LOAD_FAILED;
                }else {

                    msg.what=LOAD_CHANNEL_FINISH;
                }
                mHandler.sendMessage(msg);

            }
        }).start();
    }

    private int curItem = 0;
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if ((positionOffset + 0.5f) > 1) {
                return;
            }
            vp_newsMain.setCurrentItem(curItem);
        }

        @Override
        public void onPageSelected(int position) {
            curItem = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
