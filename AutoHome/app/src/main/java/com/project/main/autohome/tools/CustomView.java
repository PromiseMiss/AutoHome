package com.project.main.autohome.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/18 0018.
 * 自定义View用来实现刷新加载
 */
public class CustomView extends View {
    private Bitmap backGroundBitmap;
    public Bitmap pointerBitmap;
    private int x;
    private int y;
    private Bitmap finalBackGroundBitmap;
    private Bitmap finalPointerBitmap;
    private float mCurrentProgress;

    public CustomView(Context context) {
        super(context);
        init(context);
    }


    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        backGroundBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.load_icon_dial2x));
        pointerBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.load_icon_pointer2x));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        if (mode == View.MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = backGroundBitmap.getWidth();
            if (mode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int size = View.MeasureSpec.getSize(heightMeasureSpec);
        int mode = View.MeasureSpec.getMode(heightMeasureSpec);
        if (mode == View.MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = backGroundBitmap.getHeight();
            if (mode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        x = w;
        y = h;
        finalBackGroundBitmap = Bitmap.createScaledBitmap(backGroundBitmap, x, y, true);
        finalPointerBitmap = Bitmap.createScaledBitmap(pointerBitmap, x, y, true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(finalBackGroundBitmap, 0, 0, null);
        canvas.rotate(mCurrentProgress * 2.7f, x / 2, y / 2);
        canvas.drawBitmap(finalPointerBitmap, 0, 0, null);
    }


    public void setCurrentProgress(float progress) {
        mCurrentProgress = progress * 100;
    }

}
