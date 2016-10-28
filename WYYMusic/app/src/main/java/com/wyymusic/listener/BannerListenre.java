package com.wyymusic.listener;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.wyymusic.R;

import java.util.List;

/**
 * Created by A555LF on 2016/10/10.
 */

public class BannerListenre implements ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private List<View> mDotList;
    private int mCurrentItem;
    private boolean mIsAutoPlay;
    private OnListener mOnLitener;

    public BannerListenre(ViewPager viewPager, List<View> dotView, int currentItem, OnListener onListener, Boolean isAutoPlay) {
        mViewPager = viewPager;
        mDotList = dotView;
        mCurrentItem = currentItem;
        mOnLitener = onListener;
        mIsAutoPlay = isAutoPlay;

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case 1:
                mIsAutoPlay = false;
                break;
            case 2:
                mIsAutoPlay = true;
                break;
            case 0:
                if (mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1 && !mIsAutoPlay) {
                    mViewPager.setCurrentItem(0);
                } else if (mViewPager.getCurrentItem() == 0 && !mIsAutoPlay) {
                    mViewPager.setCurrentItem(mViewPager.getAdapter().getCount() - 1);
                }
                break;


        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mOnLitener.OnSuccess(position, mIsAutoPlay);
        for (int i = 0; i < mDotList.size(); i++) {
            if (i == position) {
                ((View) mDotList.get(position)).setBackgroundResource(R.drawable.dot_red);
            } else {
                ((View) mDotList.get(i)).setBackgroundResource(R.drawable.dot_black);

            }
        }

    }

    public interface OnListener {
        public void OnSuccess(int data, boolean isAutoPlay);
    }
}
