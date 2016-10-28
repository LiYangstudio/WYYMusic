package com.wyymusic.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import static android.R.attr.textSize;

/**
 * Created by A555LF on 2016/10/26.字母导航栏
 */

public class IndexBar extends View {
    public static String[] mAlphabets = new String[]{ "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#" };
    private int mSelectedPosition;
    private Paint mPaint;
    private int mCellHeight;
    private int mAlphabetDefaultColor;
    private int mAlphabetSelectedColor;
    private float mTextSize;
    private OnAlphabetChangeListener onAlphabetChangeListener;

    public IndexBar(Context context) {
        super(context);
        init();
    }

    public IndexBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTextSize);
        mAlphabetDefaultColor = Color.GRAY;
        mAlphabetSelectedColor = Color.RED;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCellHeight = getHeight() / mAlphabets.length;
        for (int i=0; i< mAlphabets.length; i++){
            drawAlphabet(canvas,i);
        }

    }

    private void drawAlphabet(Canvas canvas,int positon) {
        String alphabet = mAlphabets[positon];

        mPaint.setColor(mAlphabetDefaultColor);
        if (isPressed()){
            if (positon == mSelectedPosition){
                mPaint.setColor(mAlphabetSelectedColor);
                if (onAlphabetChangeListener != null){
                    onAlphabetChangeListener.alphabetChangeListener(this,alphabet,positon);
                }
            }
        }

        int baseLine = (positon+1) * mCellHeight;
        canvas.drawText(alphabet, (getWidth() - mPaint.measureText(alphabet)) / 2, baseLine, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                setPressed(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                mSelectedPosition = (int)(Math.ceil((y / mCellHeight)) - 1);
                break;
            case MotionEvent.ACTION_UP:
                setPressed(false);
                break;
        }
        invalidate();
        return true;
    }

    public interface OnAlphabetChangeListener{
        void alphabetChangeListener(View v,String alphabet,int position);
    }

    public OnAlphabetChangeListener getOnAlphabetChangeListener() {
        return onAlphabetChangeListener;
    }

    public void setOnAlphabetChangeListener(OnAlphabetChangeListener onAlphabetChangeListener) {
        this.onAlphabetChangeListener = onAlphabetChangeListener;
    }

    public int getAlphabetSelectedColor() {
        return mAlphabetSelectedColor;
    }

    public void setAlphabetSelectedColor(int alphabetSelectedColor) {
        this.mAlphabetSelectedColor = alphabetSelectedColor;
    }

    public int getAlphabetDefaultColor() {
        return mAlphabetDefaultColor;
    }

    public void setAlphabetDefaultColor(int alphabetDefaultColor) {
        this.mAlphabetDefaultColor = alphabetDefaultColor;
    }

    public float getTextSize() {
        if (mPaint == null) return 0;
        return mPaint.getTextSize();
    }

    public void setTextSize(float textSize) {
        if (mPaint == null) return ;
        mPaint.setTextSize(textSize);
    }
}
