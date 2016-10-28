package com.wyymusic.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.wyymusic.PlayMusicAidlInterface;
import com.wyymusic.R;
import com.wyymusic.bean.MusicBean;

import java.io.IOException;

/**
 * Created by A555LF on 2016/10/20.
 */
/*
* 开启前台的跨进程服务，防止从最近应用城需删除
* */

public class PlayMusicService extends Service {
    private MediaPlayer mMediaPlayer;
    private IBinder mBinder;

    private RemoteViews mRemoteView;
    private Notification.Builder mBuilder;
    private Notification mNotification;
    private NotificationManager mNotificationManager;


    @Override
    public void onCreate() {
        super.onCreate();
        initNotification();
        init();


    }

    private void init() {
        mMediaPlayer = new MediaPlayer();
        mBinder = new PlayMusicAidlInterface.Stub() {
            @Override
            public void play() throws RemoteException {
                mMediaPlayer.start();
            }

            @Override
            public void updatNotification(MusicBean musicbean) throws RemoteException {
                updateNotification(musicbean);
            }

            @Override
            public void playMusic(MusicBean musicBean) throws RemoteException {

                beginPlayMusic(musicBean);
                updatNotification(musicBean);
            }

            @Override
            public String getArtistName(MusicBean musicbean) throws RemoteException {
                return musicbean.getArtistName();
            }

            @Override
            public String getMusicName(MusicBean musicbean) throws RemoteException {
                return musicbean.getMusicName();
            }

            @Override
            public boolean isPlaying() throws RemoteException {
                return mMediaPlayer.isPlaying();
            }

            @Override
            public int position() throws RemoteException {
                return mMediaPlayer.getCurrentPosition();
            }

            @Override
            public int duration(MusicBean musicbean) throws RemoteException {
                return musicbean.getDuration();
            }

            @Override
            public void pre(MusicBean musicbean) throws RemoteException {
                beginPlayMusic(musicbean);
            }

            @Override
            public void next(MusicBean musicbean) throws RemoteException {
                beginPlayMusic(musicbean);
            }

            @Override
            public void pause() throws RemoteException {
                mMediaPlayer.pause();

            }

            @Override
            public void seekMusic(int time) throws RemoteException {
                mMediaPlayer.seekTo(time);

            }
        };
    }

    private void initNotification() {
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mRemoteView = new RemoteViews(this.getPackageName(), R.layout.service_notification);

        mBuilder = new Notification.Builder(this.getApplicationContext()).setContent(mRemoteView);
        mBuilder.setWhen(System.currentTimeMillis());
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mNotification = mBuilder.getNotification();
        mNotification.defaults = Notification.DEFAULT_SOUND;
        startForeground(110, mNotification);
    }

    private void updateNotification(MusicBean musicBean) {

        mNotification.contentView.setTextViewText(R.id.tv_title, musicBean.getMusicName());
        mNotification.contentView.setTextViewText(R.id.tv_text, musicBean.getAlbumName());
        mNotificationManager.notify(110, mNotification);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void beginPlayMusic(MusicBean musicBean) {
        try {

            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(musicBean.getPath());
            mMediaPlayer.prepare();

            mMediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
