package com.example.customer_view;

import android.app.Notification;
import android.content.Context;
import android.content.res.TypedArray;
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
    private int MySquareColor;
    private int MySquareSize;
    private Rect MyRect;
    private Paint MyPaint;
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
        if (attrs == null){
            return;
        }
        TypedArray ta = getContext().obtainStyledAttributes(attrs,R.styleable.view);
        MySquareColor = ta.getColor(R.styleable.view_square_color,Color.BLUE);
        MySquareSize = ta.getDimensionPixelSize(R.styleable.view_square_size,100);

        ta.recycle();
    }

    public void SwapColor() {
        MyPaint.setColor(MyPaint.getColor() == MySquareColor ? Color.RED : MySquareColor);
        postInvalidate();
    }


    public void onDraw(Canvas canvas) {
        //draw a rectangle
        MyRect.left = 100;
        MyRect.top = 100;
        MyRect.right = MyRect.left + MySquareSize;
        MyRect.bottom = MyRect.top + MySquareSize;
        canvas.drawRect(MyRect, MyPaint);
        //draw a text and line
        canvas.drawText("Touch and move the ball", 100, 400, MyPaint);
        canvas.drawLine(100, 500, 600, 500, MyPaint);
         //draw a circle
        canvas.drawCircle(x, y, radius,MyPaint);
    }

    // some animation about circle
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                radius = radius + 50;
                break;
            case MotionEvent.ACTION_MOVE:
                x= event.getX();
                y= event.getY();
                break;
            case MotionEvent.ACTION_UP:
                radius = radius - 50;
                break;
        }
        postInvalidate();
        return true;
    }


}
