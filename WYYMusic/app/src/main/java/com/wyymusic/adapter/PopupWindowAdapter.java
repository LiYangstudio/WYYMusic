package com.wyymusic.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wyymusic.R;
import com.wyymusic.bean.MusicBean;
import com.wyymusic.util.MusicCache;
import com.wyymusic.util.StartMusicService;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by A555LF on 2016/10/25.
 */

public class PopupWindowAdapter extends RecyclerView.Adapter<PopupWindowAdapter.MusicHolder>{
    private static final int MENU_HEADER=0;
    private static  final int MEUN_CONTENT=1;
    private List<MusicBean> mPlayList;

    public PopupWindowAdapter(List<MusicBean> playList) {
        mPlayList = playList;

    }

    @Override
    public MusicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == MENU_HEADER){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_bottom_menu_header, parent , false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_bottom_menu_content, parent , false);
        }
        return new MusicHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicHolder holder, int position) {
       if(position!=MENU_HEADER){
            holder.musicName.setText(mPlayList.get(position-1).getMusicName());
            holder.artistName.setText(mPlayList.get(position-1).getArtist());
        }
    }

    @Override
    public int getItemCount() {
        return mPlayList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return MENU_HEADER;
        }else {
            return MEUN_CONTENT;
        }
    }

    public class MusicHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView musicName , artistName;
        public ImageView playState ;

        public MusicHolder(View itemView) {
            super(itemView);
            musicName = (TextView) itemView.findViewById(R.id.item_tv_music_name);
            artistName = (TextView) itemView.findViewById(R.id.item_tv_artist_name);
            playState = (ImageView) itemView.findViewById(R.id.item_iv_play_state);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            StartMusicService.playMusic(mPlayList.get(getAdapterPosition()-1));
            playState.setImageResource(R.drawable.common_play_state);
            playState.setVisibility(View.VISIBLE);
            musicName.setTextColor(Color.parseColor("#d20000"));
            artistName.setTextColor(Color.parseColor("#d20000"));
            MusicCache.addCacheMusic(mPlayList);
            MusicCache.rememberPosition(getAdapterPosition() -1);
            EventBus.getDefault().post(mPlayList.get(getAdapterPosition()-1));
        }
    }


}

