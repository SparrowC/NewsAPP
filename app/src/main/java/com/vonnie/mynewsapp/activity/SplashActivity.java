package com.vonnie.mynewsapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.vonnie.mynewsapp.R;
import com.vonnie.mynewsapp.utils.SharedPreferencesUtils;

public class SplashActivity extends BaseActivity {
    private ImageView iv_splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_layout);
        initAnimation();
    }

    private void initAnimation() {
        iv_splash= (ImageView) findViewById(R.id.iv_splash);

        AnimationSet set=new AnimationSet(true);

        RotateAnimation rotateAnimation=new RotateAnimation(-180,360, Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(1500);
        rotateAnimation.setFillAfter(true);

        ScaleAnimation scaleAnimation=new ScaleAnimation(0.2f,1.0f,0.2f,1.0f,Animation.RELATIVE_TO_SELF,
                0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(1500);
        scaleAnimation.setFillAfter(true);

        AlphaAnimation alphaAnimation=new AlphaAnimation(0.1f,1f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);

        set.addAnimation(rotateAnimation);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //startActivity(GuideActivity.class);

                //TODO 修改
                if (!SharedPreferencesUtils.getBoolean(SplashActivity.this, "is_guided", false)) {//第一次运行
                    startActivity(GuideActivity.class);
                    SharedPreferencesUtils.setBoolean(SplashActivity.this, "is_guided", true);
                } else {
                    startActivity(MainActivity.class);
                }
                SplashActivity.this.finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        iv_splash.setAnimation(set);
        iv_splash.startAnimation(set);

    }


}
