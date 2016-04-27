package com.example.tacademy.sampleimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Tacademy on 2016-04-27.
 */
public class CustomView extends View {
    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    Paint paint;
    Bitmap bitmap;
    int xbit,ybit;
    private void init()
    {
        paint=new Paint();
        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.sample_1);

    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        requestLayout();        //레이아웃을 다시그린다
        invalidate();       //사진을 다시그린다
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width=getPaddingLeft()+getPaddingRight();
        int height=getPaddingTop()+getPaddingBottom();
        if(bitmap!=null)
        {
            width+=bitmap.getWidth();
            height+=bitmap.getHeight();
        }
        width=resolveSize(width,widthMeasureSpec);
        height=resolveSize(height,heightMeasureSpec);

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int width, height;
        width = (right - left) - (getPaddingLeft() + getPaddingRight());
        height = (bottom - top) - (getPaddingTop() + getPaddingBottom());

        if (bitmap != null) {
            width -= bitmap.getWidth();
            height -= bitmap.getHeight();
        }

        xbit = getPaddingLeft() + width / 2;
        ybit = getPaddingTop() + height / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        if(bitmap!=null) {
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0);
            ColorMatrixColorFilter cf = new ColorMatrixColorFilter(cm);
            paint.setColorFilter(cf);
            canvas.drawBitmap(bitmap, xbit, ybit, paint);
        }
    }
}
