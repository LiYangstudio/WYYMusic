// PlayMusicAidlInterface.aidl
package com.wyymusic;

// Declare any non-default types here with import statements
import com.wyymusic.bean.MusicBean;

interface PlayMusicAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void play();
    void playMusic(in MusicBean musicbean);
    void seekMusic(int time);
    void pause();
    void updatNotification(in MusicBean musicbean);
    void next(in MusicBean musicbean);
    void pre(in MusicBean musicbean);
    int duration(in MusicBean musicbean);
    int position();
    boolean isPlaying();
    String getMusicName(in MusicBean musicbean);
    String getArtistName(in MusicBean musicbean);
}
