package com.example.customer_view;

import android.app.Notification;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PostProcessor;
import android.graphics.Rect;
import android.speech.RecognizerResultsIntent;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class view extends View {
    //create view subclass for customer view
    private static final int SQUARE_SIZE = 100;
    private Rect MyRect;
    private Paint MyPaint;
    private Paint MyCircle;
    private float x = 400;
    private float y = 400;
    private float radius = 100f;
    public view(Context context) {
        super(context);
        init(null);
    }

    public view(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public view(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public view(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        MyRect = new Rect();
        MyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        MyPaint.setColor(Color.BLUE);
        MyPaint.setTextSize(50);
        MyCircle = new Paint();
        MyCircle.setAntiAlias(true);
        MyCircle.setColor(Color.RED);
    }


    public void onDraw(Canvas canvas) {
        //draw a rectangle
        MyRect.left = 100;
        MyRect.top = 100;
        MyRect.right = MyRect.left + SQUARE_SIZE;
        MyRect.bottom = MyRect.top + SQUARE_SIZE;
        super.onDraw(canvas);
        canvas.drawRect(MyRect, MyPaint);

        canvas.drawText("draw a line", 100, 400, MyPaint);
        canvas.drawLine(100, 500, 600, 500, MyPaint);
         //draw a circle
        canvas.drawCircle(x, y, radius, MyCircle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x= event.getX();
        y= event.getY();
        postInvalidate();
        return true;
    }

}
