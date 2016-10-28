package com.wyymusic.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by A555LF on 2016/10/28.
 */

public class WYYMusicApplication extends Application {
    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
    }

    public Context getmContext() {
        return mContext;
    }
}
