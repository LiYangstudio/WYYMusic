package com.wyymusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

import com.wyymusic.R;

import butterknife.BindView;
/*
* 欢迎界面加广告界面，延迟5秒
* */
public class SplashActivity extends AppCompatActivity {


    @BindView(R.id.iv_splash)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        butterknife.ButterKnife.bind(this);


        mImageView.setImageResource(R.drawable.iv_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();


            }
        }, 5000);


    }


}
