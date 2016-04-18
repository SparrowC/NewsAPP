package com.vonnie.mynewsapp.activity;

import android.annotation.TargetApi;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vonnie.mynewsapp.R;
import com.vonnie.mynewsapp.beans.CircleInfo;
import com.vonnie.mynewsapp.global.Config;
import com.vonnie.mynewsapp.utils.NetUtils;
import com.vonnie.mynewsapp.utils.SharedPreferencesUtils;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Vonnie on 2016/4/17.
 */
public class CircleActivity extends BaseActivity {

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 10:
                    break;
                case 11:
                    pb_bar.setVisibility(View.INVISIBLE);
                    lv_circles.setAdapter(new MyCircleAdapter());
                    break;
            }
        }
    };
    private ImageView iv_userImageCircle;
    private ListView lv_circles;
    private List<CircleInfo.CircleEntity> mCircleEntitise;
    private ProgressBar pb_bar;
    private ImageView iv_back,iv_add;
    private RelativeLayout circle_bkg;
    private TextView tv_circleUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_layout);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initView() {
        lv_circles= (ListView) findViewById(R.id.lv_circles);
        pb_bar= (ProgressBar) findViewById(R.id.pb_bar);

        iv_userImageCircle= (ImageView) findViewById(R.id.iv_userImageCircle);
        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_add= (ImageView) findViewById(R.id.iv_add);
        tv_circleUsername= (TextView) findViewById(R.id.tv_circleUsername);
//        circle_bkg= (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.circle_backgroud_layout, null);
//        iv_userImageCircle= (ImageView) circle_bkg.findViewById(R.id.iv_userImageCircle);
//        iv_back= (ImageView) circle_bkg.findViewById(R.id.iv_back);
//        iv_add= (ImageView) circle_bkg.findViewById(R.id.iv_add);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initData() {

        tv_circleUsername.setText(SharedPreferencesUtils.getString(this,"username",""));
        pb_bar.setVisibility(View.VISIBLE);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircleActivity.this.finish();
            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ShareActivity.class);
            }
        });
        //设置图像为圆形
        iv_userImageCircle.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setOval(0, 0, view.getWidth(), view.getHeight());
            }
        });
        iv_userImageCircle.setClipToOutline(true);
        getDataFromServer();


    }

    private void getDataFromServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result= NetUtils.LoadJsonDataFromServer(Config.showCircleURL);
                Log.d("CircleInfo", result);
                if(!result.equals(""))
                {
                    CircleInfo mCircleInfo=CircleInfo.objectFromData(result);
                    mCircleEntitise =mCircleInfo.getCircle();
                    Message msg=new Message();
                    msg.what=11;
                    mHandler.sendMessage(msg);
                }
            }
        }).start();
    }

    class MyCircleAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mCircleEntitise.size();
        }

        @Override
        public Object getItem(int position) {
            return mCircleEntitise.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=new ViewHolder();
            if(convertView==null)
            {
                convertView= LayoutInflater.from(CircleActivity.this).inflate(R.layout.circle_list_item_layout,null);
                holder.item_userImage= (ImageView) convertView.findViewById(R.id.item_userImage);
                holder.item_circles= (TextView) convertView.findViewById(R.id.item_circles);
                holder.item_userName= (TextView) convertView.findViewById(R.id.item_userName);
                holder.item_time= (TextView) convertView.findViewById(R.id.item_time);

                convertView.setTag(holder);
            }else
                holder= (ViewHolder) convertView.getTag();

            String name=mCircleEntitise.get(position).getUser();
            if(name.equals(SharedPreferencesUtils.getString(CircleActivity.this,"username","")))
                holder.item_userImage.setImageResource(R.drawable.image);
            else
                holder.item_userImage.setImageResource(R.drawable.ic_launcher);


            holder.item_userName.setText(name);
            holder.item_circles.setText(mCircleEntitise.get(position).getNews());
            holder.item_time.setText(mCircleEntitise.get(position).getDate());

            holder.item_userImage.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setOval(0, 0, view.getWidth(), view.getHeight());
                }
            });
            holder.item_userImage.setClipToOutline(true);
            return convertView;
        }
    }
    class ViewHolder{
        TextView item_circles,item_userName,item_time;
        ImageView item_userImage;
    }
}
