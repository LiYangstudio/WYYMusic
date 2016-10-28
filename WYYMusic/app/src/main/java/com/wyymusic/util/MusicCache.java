package com.wyymusic.util;

import android.util.SparseArray;

import com.wyymusic.bean.MusicBean;

import java.util.List;

/**
 * Created by A555LF on 2016/10/22.
 */
/*
* 定义一个缓存类
* */
public class MusicCache {
    public static SparseArray<MusicBean> mCacheMusic = new SparseArray<>();
    public static int mCurrentPosition ;

    public static void rememberPosition(int position){
        mCurrentPosition = position ;
    }

    public static int getPosition(){
        return mCurrentPosition;
    }



    public static int getMusicSize(){
        return mCacheMusic.size();
    }

    public static void addCacheMusic(List<MusicBean> list){
        for (int i = 0 ; i < list.size() ; i++){
            mCacheMusic.put(i , list.get(i));
        }
    }

    public static MusicBean getCacheMusic(int position){
        return mCacheMusic.get(position);
    }


}
