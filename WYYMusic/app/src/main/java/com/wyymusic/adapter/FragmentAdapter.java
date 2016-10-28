package com.wyymusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by A555LF on 2016/10/10.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    public FragmentAdapter(List<Fragment> FragmentList, FragmentManager fragmentManager) {

        super(fragmentManager);
        mFragmentList=FragmentList;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

}


