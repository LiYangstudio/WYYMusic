package com.wyymusic.fragment.Music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by A555LF on 2016/10/10.
 */

public class MusicListFragment extends Fragment {
    private static MusicListFragment mMusicListFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static MusicListFragment getInstance() {
        if (mMusicListFragment == null) {
            mMusicListFragment = new MusicListFragment();

        }
        return mMusicListFragment;
    }
}
