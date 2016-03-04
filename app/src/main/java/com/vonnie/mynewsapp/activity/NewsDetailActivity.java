package com.vonnie.mynewsapp.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vonnie.mynewsapp.R;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;

/**
 * Created by Vonnie on 2016/3/1.
 */
public class NewsDetailActivity extends BaseActivity {
    private ImageView iv_back,iv_newsTextSize,iv_news_share;
    private TextView tv_newsTitle;
    private WebView wv_newsWeb;
    private String url;
    private String mTitle;
    private ProgressBar pb_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail_layout);
//        url=getIntent().getStringExtra("url");
//        initView();
//        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        url=getIntent().getStringExtra("url");
        initView();
        initData();
    }

    @Override
    protected void initView() {
        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_newsTextSize= (ImageView) findViewById(R.id.iv_newsTextSize);
        iv_news_share= (ImageView) findViewById(R.id.iv_news_share);
        tv_newsTitle= (TextView) findViewById(R.id.tv_newsTitle);
        wv_newsWeb= (WebView) findViewById(R.id.wv_newsWeb);
        pb_progress= (ProgressBar) findViewById(R.id.pb_progress);

    }

    @Override
    protected void initData() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailActivity.this.finish();
            }
        });
        iv_newsTextSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        iv_news_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        wv_newsWeb.loadUrl(url);
        wv_newsWeb.getSettings().setJavaScriptEnabled(true);
        wv_newsWeb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pb_progress.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pb_progress.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                wv_newsWeb.loadUrl(url);
                return true;
            }
        });
        wv_newsWeb.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTitle=title;
                tv_newsTitle.setText(title);
            }
        });
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        oks.setTheme(OnekeyShareTheme.SKYBLUE);
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享："+mTitle);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("分享链接："+url);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}
