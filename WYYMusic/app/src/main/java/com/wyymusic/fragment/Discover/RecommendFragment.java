package com.wyymusic.fragment.Discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wyymusic.R;
import com.wyymusic.ui.BannerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by A555LF on 2016/10/10.轮播图
 */

public class RecommendFragment extends Fragment {
    private static RecommendFragment mRecommendFragment;
    @BindView(R.id.bannerview_recommend)
    BannerView mBannerView;
    @BindView(R.id.recommend_ll_ib)
    ImageButton mImageButton;
    @BindView(R.id.recommend_ll_tv)
    TextView mTextView;


    private View v;

    private int[] mImageIdList = new int[]{R.drawable.first, R.drawable.second, R.drawable.third, R.drawable.fourth, R.drawable.fifth};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_recommend_bannerview, container, false);

        ButterKnife.bind(this, v);
        mBannerView.setData(mImageIdList);


        return v;
    }

    public static RecommendFragment getInstance() {

        mRecommendFragment = new RecommendFragment();


        return mRecommendFragment;
    }
}
