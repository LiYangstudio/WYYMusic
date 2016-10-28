package com.wyymusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by A555LF on 2016/10/10.
 */

public class InViewPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> mFragmentList;
    private List<String> mTitleList;


    public InViewPagerAdapter(List<Fragment> FragmentList, List<String> TitleList, FragmentManager fragmentManager){
        super(fragmentManager);
        mFragmentList=FragmentList;
        mTitleList=TitleList;



    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    @Override
    public int getCount() {
        return mTitleList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);

    }
}


