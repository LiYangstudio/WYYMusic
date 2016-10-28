package com.wyymusic.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.wyymusic.PlayMusicAidlInterface;
import com.wyymusic.bean.MusicBean;
import com.wyymusic.service.PlayMusicService;

/**
 * Created by A555LF on 2016/10/22.
 */

public class StartMusicService {
    public static PlayMusicAidlInterface mPlayMusicAidlInterface;
    public static ServiceConnection mServiceConnection;

    public static void bindService(Context context) {
        Intent intent = new Intent(context, PlayMusicService.class);
        context.startService(intent);
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mPlayMusicAidlInterface = PlayMusicAidlInterface.Stub.asInterface(service);

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }

        };
        context.bindService(intent, mServiceConnection, context.BIND_AUTO_CREATE);
    }

    public static int position() {
        try {
            return mPlayMusicAidlInterface.position();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean isPlaying() {
        try {
            if (mPlayMusicAidlInterface != null) {
                return mPlayMusicAidlInterface.isPlaying();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void seekMusic(int currentTime) {
        try {
            mPlayMusicAidlInterface.seekMusic(currentTime);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void start() {
        try {
            mPlayMusicAidlInterface.play();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void pause() {
        try {
            mPlayMusicAidlInterface.pause();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void next() {
        if (MusicCache.getPosition() == MusicCache.getMusicSize() - 1) {
            MusicCache.rememberPosition(0);
        } else {
            MusicCache.rememberPosition(MusicCache.getPosition() + 1);
        }
        try {
            mPlayMusicAidlInterface.next(MusicCache.getCacheMusic(MusicCache.getPosition()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void pre() {
        if (MusicCache.getPosition() == 0) {
            MusicCache.rememberPosition(MusicCache.getMusicSize() - 1);
        } else {
            MusicCache.rememberPosition(MusicCache.getPosition() - 1);
        }
        try {
            mPlayMusicAidlInterface.pre(MusicCache.getCacheMusic(MusicCache.getPosition()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void playMusic(MusicBean musicBean) {
        try {
            mPlayMusicAidlInterface.playMusic(musicBean);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static String getArtistName(MusicBean musicBean) {
        try {
            if (mPlayMusicAidlInterface != null) {
                return mPlayMusicAidlInterface.getArtistName(musicBean);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateNotification(MusicBean musicBean) {
        try {
            mPlayMusicAidlInterface.updatNotification(musicBean);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static int getDuration(MusicBean musicInfo) {
        try {
            if (mPlayMusicAidlInterface != null) {
                return mPlayMusicAidlInterface.duration(musicInfo);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getMusicName(MusicBean musicInfo) {
        try {
            if (mPlayMusicAidlInterface != null) {
                return mPlayMusicAidlInterface.getMusicName(musicInfo);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void unBind(Context context) {
        context.unbindService(mServiceConnection);


    }


}
