package com.wyymusic.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wyymusic.R;
import com.wyymusic.bean.MusicBean;

import java.util.List;

/**
 * Created by A555LF on 2016/10/23.
 */

public class PlayImageAdapter extends PagerAdapter {
    private List<MusicBean> mImageViewList;

    public PlayImageAdapter(List<MusicBean> imageViewList) {
        mImageViewList = imageViewList;

    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_viewpager_playimage, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.fragment_play_album);
        imageView.setImageResource(R.drawable.fragment_play_album_defautl);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mImageViewList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
