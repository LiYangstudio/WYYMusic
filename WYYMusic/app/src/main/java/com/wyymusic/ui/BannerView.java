package com.wyymusic.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.wyymusic.R;
import com.wyymusic.adapter.BannerAdapter;
import com.wyymusic.listener.BannerListenre;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by A555LF on 2016/10/10.
 */

public class BannerView extends FrameLayout {
    public boolean mIsAutoPlay = true;

    private ViewPager mViewPager;


    private int[] mImageIdList;
    private List<ImageView> mImageViewList;
    private List<View> mDotViewList;

    private int mCurrentItem = 0;
    private ScheduledExecutorService mScheduleExecutorService;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mViewPager.setCurrentItem(mCurrentItem);
        }
    };

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BannerView(Context context, AttributeSet attributeSet, int style) {
        super(context, attributeSet, style);


    }

    private void init() {

        mImageViewList = new ArrayList<>();
        mDotViewList = new ArrayList<>();
        LayoutInflater.from(getContext()).inflate(R.layout.fragment_recommend_banner, this, true);
        for (int imageId : mImageIdList) {


            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(imageId);
            mImageViewList.add(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        mDotViewList.add(findViewById(R.id.v_dot1));
        mDotViewList.add(findViewById(R.id.v_dot2));
        mDotViewList.add(findViewById(R.id.v_dot3));
        mDotViewList.add(findViewById(R.id.v_dot4));
        mDotViewList.add(findViewById(R.id.v_dot5));
        mViewPager = (ViewPager) findViewById(R.id.banner_recommend);
        mViewPager.setFocusable(true);
        mViewPager.setAdapter(new BannerAdapter(mImageViewList));
        mViewPager.addOnPageChangeListener(new BannerListenre(mViewPager, mDotViewList, mCurrentItem, new BannerListenre.OnListener() {


            @Override
            public void OnSuccess(int data, boolean isAutoPlay) {
                mCurrentItem = data;
                mIsAutoPlay = isAutoPlay;
            }
        }, mIsAutoPlay));

    }

    private void startPlay() {
        mScheduleExecutorService = Executors.newSingleThreadScheduledExecutor();
        mScheduleExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                synchronized (mViewPager) {
                    mCurrentItem = (mCurrentItem + 1) % mImageViewList.size();
                    handler.obtainMessage().sendToTarget();
                }
            }
        }, 1, 4, TimeUnit.SECONDS);
    }

    public void setData(int[] imageId) {

        mImageIdList = imageId;
        init();
        if (mIsAutoPlay) {
            startPlay();
        }
    }

}
