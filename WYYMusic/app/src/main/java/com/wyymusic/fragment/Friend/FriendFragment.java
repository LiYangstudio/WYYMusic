package com.wyymusic.fragment.Friend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wyymusic.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by A555LF on 2016/10/10.
 */

public class FriendFragment extends Fragment {
    private static FriendFragment mFriendFragment;
    @BindView(R.id.friend_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.friend_viewpager)
    ViewPager mViewPager;
    private View v;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_viewpager_friend, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    public static FriendFragment getInstance() {
        if (mFriendFragment == null) {
            mFriendFragment = new FriendFragment();
        }
        return mFriendFragment;
    }


}
