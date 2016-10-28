package com.wyymusic.fragment.Music;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wyymusic.R;
import com.wyymusic.activity.LocalMusicActivity;
import com.wyymusic.adapter.FeatureAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by A555LF on 2016/10/10.
 */

public class MusicFragment extends Fragment {
    private boolean mFristChange = true;
    private boolean mSecondChange = true;
    private static MusicFragment mMusicFragment;
    @BindView(R.id.music_rc)
    RecyclerView mRecylerView;
    @BindView(R.id.music_fristdetail)
    ImageView mFristImageView;
    @BindView(R.id.music_seconddetail)
    ImageView mSecondImageView;
    private View view;
    private FeatureAdapter mFeatureAdapter;
    private List<String> mTitleList;
    private int[] mImageList;
    private RecyclerView.LayoutManager mLayoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_viewpager_music, container, false);
        ButterKnife.bind(this, view);

        init(view);


        return view;
    }

    public static MusicFragment getInstance() {
        if (mMusicFragment == null) {
            mMusicFragment = new MusicFragment();

        }
        return mMusicFragment;
    }

    private void init(View view) {
        mImageList = new int[]{R.drawable.music_icn_local, R.drawable.music_icn_recent, R.drawable.music_icn_dj, R.drawable.music_icn_artist, R.drawable.music_icn_dld, R.drawable.music_icn_mv};
        mTitleList = new ArrayList<>();
        mTitleList.add("本地音乐");
        mTitleList.add("最近播放");
        mTitleList.add("下载管理");
        mTitleList.add("我的歌手");
        mTitleList.add("我的电台");
        mTitleList.add("我的MV");

        mFeatureAdapter = new FeatureAdapter(mTitleList, mImageList);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecylerView.setAdapter(mFeatureAdapter);
        mRecylerView.setLayoutManager(mLayoutManager);

        mFeatureAdapter.setOnListener(new FeatureAdapter.OnRecyclerViewListener() {
            @Override
            public void OnItemClick(View view, int data) {

                switch (data) {
                    case 0:
                        Intent intent = new Intent(getContext(), LocalMusicActivity.class);
                        getContext().startActivity(intent);
                        break;
                    case 1:

                        break;

                }


            }
        });


    }

    @OnClick({R.id.music_fristdetail, R.id.music_seconddetail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.music_fristdetail:
                if (mFristChange) {
                    arrmChange(mFristImageView).start();
                    mFristChange = false;
                } else {
                    arrmChange(mFristImageView).reverse();
                    mFristChange = true;
                }

                break;
            case R.id.music_seconddetail:
                if (mSecondChange) {

                    arrmChange(mSecondImageView).start();
                    mSecondChange = false;

                } else {
                    arrmChange(mSecondImageView).reverse();
                    mSecondChange = true;
                }
                break;
        }
    }

    private ObjectAnimator arrmChange(ImageView imageView) {
        ObjectAnimator change = ObjectAnimator.ofFloat(imageView, "rotation", 90, 0, 0);
        change.setDuration(300);
        return change;


    }


}