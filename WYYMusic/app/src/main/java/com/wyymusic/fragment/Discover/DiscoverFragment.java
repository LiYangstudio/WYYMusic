package com.wyymusic.fragment.Discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wyymusic.R;
import com.wyymusic.adapter.InViewPagerAdapter;
import com.wyymusic.fragment.Music.MusicListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by A555LF on 2016/10/10.
 */

public class DiscoverFragment extends Fragment {
    private static DiscoverFragment mDiscoverFragment;
    @BindView(R.id.viewpager_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.discover_viewpager)
    ViewPager mViewPager;
    private View v;

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    private InViewPagerAdapter mInViewPagerAdapter;
    private RecommendFragment recommendFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_viewpager_discover, container, false);
        ButterKnife.bind(this, v);
        init();

        return v;
    }

    public static DiscoverFragment getInstance() {

        mDiscoverFragment = new DiscoverFragment();

        return mDiscoverFragment;

    }

    private void init() {

        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();
        mFragmentList.add(RecommendFragment.getInstance());
        mFragmentList.add(MusicListFragment.getInstance());
        mFragmentList.add(RadioFragment.getInstance());
        mFragmentList.add(RankingFragment.getInstance());


        mTitleList.add("个性推荐");
        mTitleList.add("歌单");
        mTitleList.add("主播电台");
        mTitleList.add("排行榜");
        mInViewPagerAdapter = new InViewPagerAdapter(mFragmentList, mTitleList, getChildFragmentManager());
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager.setAdapter(mInViewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);


    }

}
