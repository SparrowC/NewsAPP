package com.vonnie.mynewsapp.activity.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vonnie.mynewsapp.R;
import com.vonnie.mynewsapp.activity.MainActivity;
import com.vonnie.mynewsapp.beans.NewsDetails;
import com.vonnie.mynewsapp.global.Config;
import com.vonnie.mynewsapp.utils.DateUtils;
import com.vonnie.mynewsapp.utils.NetUtils;

import java.util.List;

/**
 * PullRefreshListView + ViewPager +ListView
 * * Created by Vonnie on 2016/2/26.
 */
public class MyServerChannelNewsView {
    protected View root;
    private ListView lv_newList;
    protected MainActivity mActivity;
    private String channelName;
    private String timestamp;
    private List<NewsDetails.ShowapiResBodyEntity.PagebeanEntity.ContentlistEntity> contentList;

    final private int LOAD_NEWS_DETAIL_SUCCESS =1;
    final private int LOAD_NEWS_DETAIL_FAIL =0;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case LOAD_NEWS_DETAIL_FAIL:
                    Toast.makeText(mActivity,"服务器出问题了！",Toast.LENGTH_SHORT).show();
                    break;
                case LOAD_NEWS_DETAIL_SUCCESS:
                    initData();
                    break;
            }
        }
    };

    public MyServerChannelNewsView(MainActivity activity, String name) {
        mActivity = activity;
        this.channelName=name;
        initView();
    }

    private void initData() {
        lv_newList.setAdapter(new MyNewsListAdapter());
    }

    private void initView() {
        root=View.inflate(mActivity,R.layout.base_channel_news_layout,null);
        lv_newList= (ListView) root.findViewById(R.id.lv_newList);

        //加载网络数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                //timestamp="20160229215000";
                timestamp= DateUtils.getCurTimestamp();

                Message msg=new Message();
                String url= Config.myNewsURL+channelName+".txt";
                String result= NetUtils.LoadJsonDataFromServer(url);

                NewsDetails details=NewsDetails.objectFromData(result);
                NewsDetails.ShowapiResBodyEntity bodyEntity=details.getShowapi_res_body();
                if(bodyEntity!=null){
                    NewsDetails.ShowapiResBodyEntity.PagebeanEntity pagebeanEntity=bodyEntity.getPagebean();
                    if(pagebeanEntity!=null){
                        contentList=pagebeanEntity.getContentlist();
                        if(contentList!=null&&contentList.size()>0)
                        {
                            msg.what=LOAD_NEWS_DETAIL_SUCCESS;
                        }else {
                            msg.what=LOAD_NEWS_DETAIL_FAIL;
                        }
                        mHandler.sendMessage(msg);
                        return;
                    }
                }
                msg.what=LOAD_NEWS_DETAIL_FAIL;
                mHandler.sendMessage(msg);
            }
        }).start();
    }




    public View getView()
    {
        return root;
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
