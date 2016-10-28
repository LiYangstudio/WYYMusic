package com.wyymusic.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.wyymusic.R;
import com.wyymusic.adapter.InViewPagerAdapter;
import com.wyymusic.fragment.Friend.EmptyFragment;
import com.wyymusic.fragment.Music.SingleMusicFrament;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by A555LF on 2016/10/15.
 */

public class LocalMusicActivity extends BottomActivity {
    @BindView(R.id.localmusic_tb)
    Toolbar mToolbar;
    @BindView(R.id.localmusic_tab)
    TabLayout mTabLayout;
    @BindView(R.id.localmusic_viewpager)
    ViewPager mViewPager;
    private List<Fragment> mFragmentList;
    private List<String> mTitleList;
    private SingleMusicFrament mSingleMusicFragment;
    private InViewPagerAdapter mInViewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_localmusic);

        ButterKnife.bind(this);
        init();


    }

    private void init() {
        mToolbar.setTitle("本地音乐");
        setSupportActionBar(mToolbar);
        mToolbar.inflateMenu(R.menu.localmusic_menu);
        mToolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.music_detaillist));
        mToolbar.setNavigationIcon(R.drawable.localmusic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.localmusic_insearch:

                        break;


                }
                return false;

            }

        });

        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();
        mTitleList.add("单曲");
        mTitleList.add("歌手");
        mTitleList.add("专辑");
        mTitleList.add("文件夹");
        mSingleMusicFragment = new SingleMusicFrament();
        mFragmentList.add(mSingleMusicFragment);
        for (int i = 0; i < 3; i++) {
            EmptyFragment emptyFragment = new EmptyFragment();
            mFragmentList.add(emptyFragment);
        }
        mInViewPagerAdapter = new InViewPagerAdapter(mFragmentList, mTitleList, getSupportFragmentManager());
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager.setAdapter(mInViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.localmusic_menu, menu);
        return true;
    }

    //设置一个反射方法，解决嵌入menu菜单的图标不显示
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {

                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
