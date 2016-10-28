package com.wyymusic.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.wyymusic.util.PinYinUtil;

/**
 * Created by A555LF on 2016/10/21.
 */

public class MusicBean implements Parcelable, Comparable {

    private String musicName;
    private String artistName;
    private long songId;
    private String artist;
    private String path;
    private long artistId;
    private int duration;
    private String data;
    private int albumId;
    private String albumName;
    private String albumData;
    private boolean isLocal;
    private int size;
    private String folder;
    private String firstAlphabet;

    protected MusicBean(Parcel in) {
        musicName = in.readString();
        artistName = in.readString();
        songId = in.readLong();
        artist = in.readString();
        path = in.readString();
        artistId = in.readLong();
        duration = in.readInt();
        data = in.readString();
        albumId = in.readInt();
        albumName = in.readString();
        albumData = in.readString();
        isLocal = in.readByte() != 0;
        size = in.readInt();
        folder = in.readString();
    }

    public static final Creator<MusicBean> CREATOR = new Creator<MusicBean>() {
        @Override
        public MusicBean createFromParcel(Parcel in) {
            return new MusicBean(in);
        }

        @Override
        public MusicBean[] newArray(int size) {
            return new MusicBean[size];
        }
    };

    public MusicBean() {

    }


    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
        firstAlphabet = PinYinUtil.getFirstAlphabet(musicName);
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPath() {
        return path;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getArtistId() {
        return artistId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumData() {
        return albumData;
    }

    public void setAlbumData(String albumData) {
        this.albumData = albumData;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFirstAlphabet() {
        return firstAlphabet;
    }

    public void setFirstAlphabet(String firstAlphabet) {
        this.firstAlphabet = firstAlphabet;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(musicName);
        dest.writeString(artistName);
        dest.writeLong(songId);
        dest.writeString(artist);
        dest.writeString(path);
        dest.writeLong(artistId);
        dest.writeInt(duration);
        dest.writeString(data);
        dest.writeInt(albumId);
        dest.writeString(albumName);
        dest.writeString(albumData);
        dest.writeByte((byte) (isLocal ? 1 : 0));
        dest.writeInt(size);
        dest.writeString(folder);
    }

    @Override
    public int compareTo(Object another) {
        MusicBean musicBean = (MusicBean) another;
        if (musicBean.getFirstAlphabet().equals("#")) {
            return 1;

        } else if (getFirstAlphabet().equals("#")) {
            return -1;

        } else
            return getFirstAlphabet().compareTo(((MusicBean) another).getFirstAlphabet());
    }
}

