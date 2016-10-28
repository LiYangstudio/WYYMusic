package com.wyymusic.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.wyymusic.bean.ArtistBean;
import com.wyymusic.bean.MusicBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by A555LF on 2016/10/20.
 */

public class MusicInfoUtil {
    private static String[] mMusicInfo=new String[] {
            MediaStore.Audio.Media._ID ,
            MediaStore.Audio.Media.ALBUM_ID ,
            MediaStore.Audio.Media.ALBUM ,
            MediaStore.Audio.Media.DURATION ,
            MediaStore.Audio.Media.TITLE ,
            MediaStore.Audio.Media.ARTIST ,
            MediaStore.Audio.Media.ARTIST_ID ,
            MediaStore.Audio.Media.DATA ,
            MediaStore.Audio.Media.SIZE

    };
    private static String[] mArtistInfo=new String[]{
            MediaStore.Audio.Artists.ARTIST ,
            MediaStore.Audio.Artists.NUMBER_OF_TRACKS ,
            MediaStore.Audio.Artists._ID

    };
    public static List<MusicBean> querryMusic(Context context){
        Uri uri=MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver=context.getContentResolver();
        List<MusicBean> musicBeanList=getMusicList(contentResolver.query(uri,mMusicInfo,null,null,MediaStore.Audio.Media.DEFAULT_SORT_ORDER));
        return musicBeanList;
    }
   public static List<MusicBean> getMusicList(Cursor cursor){
     List<MusicBean> musicBeanList=new ArrayList<>();
       while(cursor.moveToNext()){
           MusicBean musicBean=new MusicBean();
           musicBean.setSongId(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
           musicBean.setArtistId(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
           musicBean.setDuration(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
           musicBean.setMusicName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
           musicBean.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
           musicBean.setArtistId(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID)));
           musicBean.setAlbumName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
           musicBean.setSize(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)));
           String path=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
           musicBean.setPath(path);
           musicBean.setFolder(path.substring(0,path.lastIndexOf(File.separator)));
           musicBean.setLocal(true);
           musicBeanList.add(musicBean);


       }
       cursor.close();
       return musicBeanList;

   }
public static List<ArtistBean> getArtistList(Cursor cursor){
    List<ArtistBean> artistBeanList=new ArrayList<>();
    while(cursor.moveToNext()){
        ArtistBean artistBean=new ArtistBean();
        artistBean.setArtistId(cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Artists._ID)));
        artistBean.setArtistName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST)));
        artistBean.setNumberTtacks(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS)));
        artistBeanList.add(artistBean);

    }
    cursor.close();
    return artistBeanList;



}
    public static List<ArtistBean> querryArtist(Context context){
        Uri uri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver=context.getContentResolver();
        List<ArtistBean> artistBeanList=getArtistList(contentResolver.query(uri,mArtistInfo,null,null,MediaStore.Audio.Artists.DEFAULT_SORT_ORDER));
        return artistBeanList;

    }
}
