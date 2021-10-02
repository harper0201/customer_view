package com.example.customer_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

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
    private Bitmap MyImage;

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
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int padding = 50;
                MyImage = getResizedBitxmap(MyImage, getWidth() - padding, getHeight() - padding);
            }
        });

        //time task to resize image
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int newWidth = MyImage.getWidth() - 50;
                int newHeight = MyImage.getHeight() - 50;
                if (newWidth <= 0 || newHeight <= 0) {
                    cancel();
                    return;
                }
                MyImage = getResizedBitxmap(MyImage, newWidth, newHeight);
                postInvalidate();
            }
        }, 2000, 500);

        if (attrs == null){
            return;
        }
        // get the attribution in view
        TypedArray ta = getContext().obtainStyledAttributes(attrs,R.styleable.view);
        MySquareColor = ta.getColor(R.styleable.view_square_color,Color.BLUE);
        MySquareSize = ta.getDimensionPixelSize(R.styleable.view_square_size,SQUARE_SIZE);
        //deal with image
        MyImage = BitmapFactory.decodeResource(getResources(),R.drawable.cute_cat);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
        // draw a image
        float ImageX = (getWidth() - MyImage.getWidth())/2;
        float ImageY = getHeight() - MyImage.getHeight();
        canvas.drawBitmap(MyImage,ImageX,ImageY,null);
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

    //resize the picture
    private Bitmap getResizedBitxmap(Bitmap bitmap,int reqWidth,int reqheight){
        Matrix matrix = new Matrix();
        RectF src = new RectF(0,0,bitmap.getWidth(),bitmap.getHeight());
        RectF dst = new RectF(0,0,reqWidth,reqheight);
        matrix.setRectToRect(src,dst,Matrix.ScaleToFit.CENTER);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }


}
