package com.wyymusic.util;

/**
 * Created by A555LF on 2016/10/27.
 */
/*
* 将时间转化为00：00格式
* */
public class CalculateTime {

    public static String getFormatTime(long time) {
        String min = time / (1000 * 60) + "";
        String sec = time % (1000 * 60) + "";
        if (min.length() < 2) {
            min = "0" + min;
        }
        if (sec.length() == 4) {
            sec = "0" + sec;
        } else if (sec.length() == 3) {
            sec = "00" + sec;
        } else if (sec.length() == 2) {
            sec = "000" + sec;
        } else if (sec.length() == 1) {
            sec = "0000" + sec;
        }
        return min + ":" + sec.trim().substring(0, 2);
    }
}
