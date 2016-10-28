package com.wyymusic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wyymusic.R;

import java.util.List;

/**
 * Created by A555LF on 2016/10/11.
 */

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.MusicListHolder> {


    private List<String> mTitleList;
    private int[] mImageList;
    private OnRecyclerViewListener mOnRecyclerViewListener;

    public FeatureAdapter(List<String> titlelist, int[] imagelist) {
        mImageList = imagelist;
        mTitleList = titlelist;

    }

    @Override
    public MusicListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_music_list, parent, false);
        MusicListHolder musicListHolder = new MusicListHolder(view);

        return musicListHolder;
    }

    @Override
    public int getItemCount() {
        return mTitleList.size();
    }

    @Override
    public void onBindViewHolder(final MusicListHolder holder, int position) {
        holder.imageView.setImageResource(mImageList[position]);
        holder.textView.setText(mTitleList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = holder.getLayoutPosition();
                mOnRecyclerViewListener.OnItemClick(v, a);
            }
        });
    }

    public interface OnRecyclerViewListener {
        void OnItemClick(View view, int data);
    }


    public void setOnListener(OnRecyclerViewListener onRecyclerViewListener) {
        mOnRecyclerViewListener = onRecyclerViewListener;

    }

    class MusicListHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        TextView textView;

        public MusicListHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.music_iv);
            textView = (TextView) view.findViewById(R.id.music_tv);


        }
    }
}
