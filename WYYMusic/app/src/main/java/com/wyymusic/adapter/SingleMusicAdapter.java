package com.wyymusic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wyymusic.R;
import com.wyymusic.bean.MusicBean;

import java.util.Collections;
import java.util.List;

/**
 * Created by A555LF on 2016/10/22.
 */

public class SingleMusicAdapter extends RecyclerView.Adapter<SingleMusicAdapter.SongHolder> {
    private static final int LIST_HEAD = 0;
    private static final int LIST_CONTENT = 1;

    private List<MusicBean> mMusicList;
    private OnClickListener onClickListener;


    public SingleMusicAdapter(List<MusicBean> musicList) {
        mMusicList = musicList;
        Collections.sort(mMusicList);

    }

    @Override
    public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == LIST_HEAD) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_musiclist_header, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_singlemusiclist, parent, false);

        }
        return new SongHolder(view);
    }

    @Override
    public void onBindViewHolder(SongHolder holder, int position) {

        if (position == LIST_HEAD) {

        } else {
            if (position == 1) {
                holder.testView.setVisibility(View.VISIBLE);
            } else if (mMusicList.get(position - 1).getFirstAlphabet().charAt(0) != mMusicList.get(position - 2).getFirstAlphabet().charAt(0)) {
                holder.testView.setVisibility(View.VISIBLE);
            } else {
                holder.testView.setVisibility(View.GONE);
            }

            holder.testView.setText(mMusicList.get(position - 1).getFirstAlphabet());
            holder.name.setText(mMusicList.get(position - 1).getMusicName());
            holder.artist.setText(mMusicList.get(position - 1).getArtist());
            onClickListener.onClick(holder, mMusicList.get(position - 1), position - 1);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return LIST_HEAD;
        } else return LIST_CONTENT;


    }

    @Override
    public int getItemCount() {
        return mMusicList.size() + 1;
    }


    public class SongHolder extends RecyclerView.ViewHolder {
        ImageView imageView, music, more;
        TextView name, artist, text;

        TextView testView;

        public SongHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_tv_name);
            artist = (TextView) itemView.findViewById(R.id.item_tv_artist);
            music = (ImageView) itemView.findViewById(R.id.item_iv_music);

            text = (TextView) itemView.findViewById(R.id.item_head_text);
            imageView = (ImageView) itemView.findViewById(R.id.item_head_image);
            more = (ImageView) itemView.findViewById(R.id.item_head_more);

            testView = (TextView) itemView.findViewById(R.id.tv_header);

        }


    }

    public void onClick(OnClickListener listener) {
        onClickListener = listener;
    }

    public interface OnClickListener {
        void onClick(SongHolder itemHolder, MusicBean musicInfo, int position);
    }

}
