package com.wyymusic.activity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wyymusic.R;
import com.wyymusic.adapter.PlayImageAdapter;
import com.wyymusic.bean.MusicBean;
import com.wyymusic.util.CalculateTime;
import com.wyymusic.util.MusicCache;
import com.wyymusic.util.MusicInfoUtil;
import com.wyymusic.util.StartMusicService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

import static com.wyymusic.util.StartMusicService.isPlaying;

/**
 * Created by A555LF on 2016/10/23.
 */

public class PlayActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, ViewPager.OnPageChangeListener {
    private static final int ANIMATOR_SPEED = 500;
    private static final int ANIMATOR_ROTATION = -30;
    private static final int ANIMATOR_REPEAT_COUNT = 0;
    @BindView(R.id.play_iv_bg)
    ImageView mBgImageView;
    @BindView(R.id.play_tb_bar)
    Toolbar mToolBar;
    @BindView(R.id.play_vp_slide)
    ViewPager mViewpager;
    @BindView(R.id.play_iv_needle)
    ImageView mNeedleImageView;
    @BindView(R.id.play_iv_love)
    ImageView mLoveImageView;
    @BindView(R.id.play_iv_add)
    ImageView mAddImageView;
    @BindView(R.id.play_iv_number)
    ImageView mNumberImageView;
    @BindView(R.id.play_iv_more)
    ImageView mMoreImageView;
    @BindView(R.id.play_tv_start)
    TextView mStartTextVIiew;
    @BindView(R.id.play_sb_progress)
    SeekBar mSeekBar;
    @BindView(R.id.play_tv_end)
    TextView mEndTextView;
    @BindView(R.id.play_iv_mode)
    ImageView mModeImageView;
    @BindView(R.id.play_iv_pre)
    ImageView mPreImageview;
    @BindView(R.id.play_iv_play)
    ImageView mPlayImageView;
    @BindView(R.id.play_iv_next)
    ImageView mNextImageView;
    @BindView(R.id.play_iv_menu)
    ImageView mMenuImageView;
    private List<MusicBean> mMusicList;
    private ObjectAnimator mNeedleAnimator;
    private PlayImageAdapter mPlayImageAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_playmusic);
        ButterKnife.bind(this);
        setToolBar();

        setOnListener();
        initAnimator();
        initInfo();
        initViewPager();

    }

    private void initInfo() {
        mEndTextView.setText(CalculateTime.getFormatTime(StartMusicService.getDuration(MusicCache.getCacheMusic(MusicCache.getPosition()))));
        mStartTextVIiew.setText(CalculateTime.getFormatTime(StartMusicService.position()));

        mSeekBar.setMax(StartMusicService.getDuration(MusicCache.getCacheMusic(MusicCache.getPosition())));
        mSeekBar.setProgress(StartMusicService.position());
        mToolBar.setTitle(StartMusicService.getMusicName(MusicCache.getCacheMusic(MusicCache.getPosition())));
        mToolBar.setSubtitle(StartMusicService.getArtistName(MusicCache.getCacheMusic(MusicCache.getPosition())));
        if (isPlaying()) {
            mNeedleAnimator.start();
            mPlayImageView.setImageResource(R.drawable.play_bottom_pause);
            mSeekBar.postDelayed(mUpdateSeekBar, 1000);
        }
        mMusicList = MusicInfoUtil.querryMusic(this);
    }

    private Runnable mUpdateSeekBar = new Runnable() {
        @Override
        public void run() {
            mStartTextVIiew.setText(CalculateTime.getFormatTime(StartMusicService.position()));
            mSeekBar.setProgress(StartMusicService.position());
            if (StartMusicService.isPlaying()) {
                mSeekBar.postDelayed(mUpdateSeekBar, 1000);
            }
        }
    };

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PlayActivity.class);
        context.startActivity(intent);

    }

    private void initAnimator() {
        mNeedleAnimator = ObjectAnimator.ofFloat(mNeedleImageView, "rotation", ANIMATOR_ROTATION, 0);
        mNeedleAnimator.setDuration(ANIMATOR_SPEED);
        mNeedleAnimator.setRepeatCount(ANIMATOR_REPEAT_COUNT);
        mNeedleAnimator.setInterpolator(new LinearInterpolator());
    }

    private void setToolBar() {
        mToolBar = (Toolbar) findViewById(R.id.play_tb_bar);
        if (mToolBar != null) {
            mToolBar.setNavigationIcon(R.drawable.action_bar_back);
            mToolBar.setTitle(StartMusicService.getMusicName(MusicCache.getCacheMusic(MusicCache.getPosition())));
            mToolBar.setSubtitle(StartMusicService.getArtistName(MusicCache.getCacheMusic(MusicCache.getPosition())));
            setSupportActionBar(mToolBar);
            mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    private void initViewPager() {
        mPlayImageAdapter = new PlayImageAdapter(mMusicList);
        mViewpager.setAdapter(mPlayImageAdapter);
        mViewpager.setCurrentItem(MusicCache.getPosition());

    }

    private void setOnListener() {
        mPlayImageView.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
        mNextImageView.setOnClickListener(this);
        mPreImageview.setOnClickListener(this);
        mViewpager.addOnPageChangeListener(this);
    }

    private void changeToNext() {
        StartMusicService.next();
        initInfo();

        mViewpager.setCurrentItem(MusicCache.getPosition());
        EventBus.getDefault().post("null");
    }

    private void changeToPre() {
        StartMusicService.pre();
        initInfo();

        mViewpager.setCurrentItem(MusicCache.getPosition());
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position > MusicCache.getPosition()) {
            changeToNext();
        } else if (position < MusicCache.getPosition()) {
            changeToPre();
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            StartMusicService.seekMusic(progress);
            mStartTextVIiew.setText(CalculateTime.getFormatTime(StartMusicService.position()));
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_iv_play:
                if (StartMusicService.isPlaying()) {
                    mPlayImageView.setImageResource(R.drawable.play_bottom_play);
                    StartMusicService.pause();
                    mSeekBar.removeCallbacks(mUpdateSeekBar);
                    mNeedleAnimator.reverse();
                } else {
                    mPlayImageView.setImageResource(R.drawable.play_bottom_pause);
                    StartMusicService.start();
                    mNeedleAnimator.start();
                    mSeekBar.postDelayed(mUpdateSeekBar, 1000);
                }
                //  EventBus.getDefault().post("null");
                break;
            case R.id.play_iv_next:
//
                changeToNext();
                break;
            case R.id.play_iv_pre:
//
                changeToPre();
                break;
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }
}
