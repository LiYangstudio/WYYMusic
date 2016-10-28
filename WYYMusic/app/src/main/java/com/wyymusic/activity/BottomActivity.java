package com.wyymusic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.wyymusic.R;
import com.wyymusic.fragment.Music.BottomFragment;
import com.wyymusic.util.StartMusicService;

/**
 * Created by A555LF on 2016/10/23.
 */

public class BottomActivity extends AppCompatActivity {
    private BottomFragment mBottomFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StartMusicService.bindService(this);
        showBottomControl();

    }

    protected void showBottomControl() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();


        if (mBottomFragment == null) {

            mBottomFragment = BottomFragment.newInstance();
            fragmentTransaction.add(R.id.bottom_fl_control, mBottomFragment).commitAllowingStateLoss();
        } else {
            fragmentTransaction.show(mBottomFragment).commitAllowingStateLoss();
        }

    }

    @Override
    protected void onDestroy() {
        StartMusicService.unBind(this);
        super.onDestroy();
    }

}
