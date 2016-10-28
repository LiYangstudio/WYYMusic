package com.wyymusic.fragment.Music;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wyymusic.R;
import com.wyymusic.adapter.SingleMusicAdapter;
import com.wyymusic.bean.MusicBean;
import com.wyymusic.ui.DividerDecoration;
import com.wyymusic.ui.IndexBar;
import com.wyymusic.util.MusicCache;
import com.wyymusic.util.MusicInfoUtil;
import com.wyymusic.util.StartMusicService;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by A555LF on 2016/10/15.
 */

public class SingleMusicFrament extends Fragment {


    @BindView(R.id.singlemusic_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.singlemusic_indexbar)
    IndexBar mIndexBar;
    private SingleMusicAdapter mSingleMusicAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rv_singlemusic_fragment, container, false);

        ButterKnife.bind(this, view);
        init();
        onClick();
        return view;
    }

    private void init() {
        mSingleMusicAdapter = new SingleMusicAdapter(MusicInfoUtil.querryMusic(getContext()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mSingleMusicAdapter);
        mRecyclerView.addItemDecoration(new DividerDecoration(getActivity(), DividerDecoration.VERTICAL_LIST));

    }

    private void onClick() {
        mSingleMusicAdapter.onClick(new SingleMusicAdapter.OnClickListener() {
            @Override
            public void onClick(SingleMusicAdapter.SongHolder itemHolder, final MusicBean musicInfo, final int position) {
                itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        MusicCache.rememberPosition(position);
                        MusicCache.addCacheMusic(MusicInfoUtil.querryMusic(getActivity()));
                        StartMusicService.playMusic(musicInfo);
                        EventBus.getDefault().post(musicInfo);
                    }
                });
            }
        });

        mIndexBar.setOnAlphabetChangeListener(new IndexBar.OnAlphabetChangeListener() {
            @Override
            public void alphabetChangeListener(View v, String alphabet, int position) {
                mRecyclerView.scrollToPosition(position);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
