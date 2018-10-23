package com.mmj.www.multitouchview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MultiTouchView extends View {
    final int MAX_POINTS = 10;//포인터갯수
    float[] x = new float[10];
    float[] y = new float[10];
    boolean[] touching = new boolean[MAX_POINTS];
    Paint mPaint;
//    Paint mPaint2;

    public MultiTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.CYAN);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint2.setColor(Color.RED);
//        mPaint2.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int id = event.getPointerId(index);
        int action = event.getActionMasked();
        switch (action){
            case MotionEvent.ACTION_DOWN:break;
            case MotionEvent.ACTION_POINTER_DOWN:
                x[id] = (int)event.getX(index);
                y[id] = (int)event.getY(index);
                touching[id] = true;
                break;
            case MotionEvent.ACTION_MOVE:break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                touching[id] = false;
                break;
        }

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0; i<MAX_POINTS; i++)
            if (touching[i]) {
            canvas.drawCircle(x[i],y[i], 60, mPaint);
            }
    }
}

