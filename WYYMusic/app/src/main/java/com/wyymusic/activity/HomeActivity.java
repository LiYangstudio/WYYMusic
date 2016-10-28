package com.wyymusic.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.wyymusic.R;
import com.wyymusic.adapter.FragmentAdapter;
import com.wyymusic.fragment.Discover.DiscoverFragment;
import com.wyymusic.fragment.Friend.FriendFragment;
import com.wyymusic.fragment.Music.MusicFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by A555LF on 2016/10/9.
 */

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tb_drawerlayout_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawerlayout_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.drawerlayout_navigationview)
    NavigationView mNavigationView;
    @BindView(R.id.activity_drawerlayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar_menu)
    ImageView mDrawerIcon;
    @BindView(R.id.toolbar_discover)
    ImageView mDiscoverBar;
    @BindView(R.id.toolbar_music)
    ImageView mMusicBar;
    @BindView(R.id.toolbar_friend)
    ImageView mFriendBar;
    @BindView(R.id.toolbar_search)
    ImageView mSearchBar;


    private List<Fragment> mFragmentList;

    private FragmentAdapter mFragmentAdapter;

    private boolean mIsOpen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();
        mViewPager.setCurrentItem(1);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                mIsOpen= true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mIsOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


    }

    private void init() {

        mToolbar.setTitle("本地音乐");
        setSupportActionBar(mToolbar);
        mFragmentList = new ArrayList<>();

        mFragmentList.add(DiscoverFragment.getInstance());
        mFragmentList.add(MusicFragment.getInstance());
        mFragmentList.add(FriendFragment.getInstance());

        mFragmentAdapter = new FragmentAdapter(mFragmentList, getSupportFragmentManager());
        mViewPager.setAdapter(mFragmentAdapter);
        mMusicBar.setSelected(true);
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setItemTextColor(null);

        mFriendBar.setOnClickListener(this);
        mDiscoverBar.setOnClickListener(this);
        mDrawerIcon.setOnClickListener(this);
        mSearchBar.setOnClickListener(this);
        mMusicBar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_menu:
                if (!mIsOpen) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    mIsOpen = true;
                }
                break;
            case R.id.toolbar_discover:
                mMusicBar.setSelected(false);
                mDiscoverBar.setSelected(true);
                mFriendBar.setSelected(false);
                mViewPager.setCurrentItem(0);

                break;
            case R.id.toolbar_friend:
                mFriendBar.setSelected(true);
                mMusicBar.setSelected(false);
                mDiscoverBar.setSelected(false);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.toolbar_music:
                mDiscoverBar.setSelected(false);
                mMusicBar.setSelected(true);
                mFriendBar.setSelected(false);
                mViewPager.setCurrentItem(1);
                break;


        }
    }


}
