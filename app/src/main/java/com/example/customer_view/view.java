package com.example.customer_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PostProcessor;
import android.graphics.Rect;
import android.speech.RecognizerResultsIntent;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class view extends View {
    //create view subclass for customer view
    private static final int SQUARE_SIZE = 100;
    private Rect MyRect;
    private Paint MyPaint;
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

    public void init(AttributeSet attrs){
        MyRect = new Rect();
        MyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        MyPaint.setColor(Color.BLUE);
    }

    public void SwapColor(){
        //swap color
        MyPaint.setColor(MyPaint.getColor() == Color.BLUE?Color.RED:Color.BLUE);
        invalidate();
    }

    protected void onDraw(Canvas canvas){
        //draw a rectangle
        MyRect.left = 100;
        MyRect.top = 100;
        MyRect.right = MyRect.left + SQUARE_SIZE;
        MyRect.bottom = MyRect.top + SQUARE_SIZE;
        canvas.drawRect(MyRect,MyPaint);

        // draw a circle
        int radis = 50;
        float x = MyRect.bottom + 100;
        float y = MyRect.right;
        canvas.drawCircle(x,y,radis,MyPaint);
    }
}
