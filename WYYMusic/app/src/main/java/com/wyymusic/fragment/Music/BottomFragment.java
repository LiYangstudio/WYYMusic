package com.wyymusic.fragment.Music;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wyymusic.R;
import com.wyymusic.activity.PlayActivity;
import com.wyymusic.adapter.PopupWindowAdapter;
import com.wyymusic.bean.MusicBean;
import com.wyymusic.ui.DividerDecoration;
import com.wyymusic.util.MusicCache;
import com.wyymusic.util.MusicInfoUtil;
import com.wyymusic.util.StartMusicService;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by A555LF on 2016/10/21.
 */

public class BottomFragment extends Fragment implements View.OnClickListener, PopupWindow.OnDismissListener {
    @BindView(R.id.main_bottom_cover)
    ImageView mCoverImageView;
    @BindView(R.id.main_bottom_song)
    TextView mSongTextView;
    @BindView(R.id.main_bottom_artist)
    TextView mArtistTextView;
    @BindView(R.id.main_bottom_menu)
    ImageView mMenuImageView;
    @BindView(R.id.main_bottom_play)
    ImageView mPlayImageView;
    @BindView(R.id.main_bottom_next)
    ImageView mNextImageView;
    @BindView(R.id.main_bottom_root)
    LinearLayout mainBottomRoot;
    @BindView(R.id.main_bottom_progress)
    ProgressBar mProgress;

    private PopupWindowAdapter mPopupWindowAdapter;
    private PopupWindow mPopupWindow;
    private RecyclerView mRecyclerView;


    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                mProgress.setProgress(StartMusicService.position());
                handler.sendEmptyMessageDelayed(1, 50);
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottom_control, container, false);
        EventBus.getDefault().register(this);

        ButterKnife.bind(this, view);

        updateBottomControl();
        setListener();
        return view;
    }

    private void updateBottomControl() {
        mPopupWindowAdapter = new PopupWindowAdapter(MusicInfoUtil.querryMusic(getContext()));

        if (StartMusicService.getMusicName(MusicCache.getCacheMusic(MusicCache.getPosition())) != null) {
            mSongTextView.setText(StartMusicService.getMusicName(MusicCache.getCacheMusic(MusicCache.getPosition())));
            mArtistTextView.setText(StartMusicService.getArtistName(MusicCache.getCacheMusic(MusicCache.getPosition())));
            if (StartMusicService.isPlaying()) {

                mProgress.setMax(StartMusicService.getDuration(MusicCache.getCacheMusic(MusicCache.getPosition())));
                handler.sendEmptyMessageDelayed(1, 100);
            }
        }
    }

    public static BottomFragment newInstance() {
        return new BottomFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_bottom_play:
                if (StartMusicService.isPlaying()) {
                    StartMusicService.pause();
                } else {
                    StartMusicService.start();
                }
                upDateIcon();
                break;
            case R.id.main_bottom_root:
                PlayActivity.startActivity(getActivity());
                break;
            case R.id.main_bottom_next:
                StartMusicService.next();
                updateBottomControl();
                break;
            case R.id.main_bottom_menu:
                initPopupWindow();
                mPopupWindow.showAtLocation(mMenuImageView, Gravity.BOTTOM, 0, 0);
                break;


        }
    }

    private void upDateIcon() {
        if (StartMusicService.isPlaying()) {
            mPlayImageView.setImageResource(R.drawable.bottom_pause);
        } else {
            mPlayImageView.setImageResource(R.drawable.bottom_play);
        }

    }

    @Subscribe
    public void onEvent(MusicBean musicBean) {

        updateBottomControl();
    }

    private void setListener() {
        mPlayImageView.setOnClickListener(this);
        mainBottomRoot.setOnClickListener(this);
        mNextImageView.setOnClickListener(this);
        mMenuImageView.setOnClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initPopupWindow() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_bottom_menu_rv, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_rv_control);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mPopupWindowAdapter);
        mRecyclerView.addItemDecoration(new DividerDecoration(getActivity(), DividerDecoration.VERTICAL_LIST));
        mRecyclerView.setOnClickListener(this);
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels - 900;
        mPopupWindow = new PopupWindow(view,
                width, height, true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        mPopupWindow.setOnDismissListener(this);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
    }

    @Override
    public void onDismiss() {

    }
}
