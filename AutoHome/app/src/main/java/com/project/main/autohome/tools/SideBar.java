package com.project.main.autohome.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/14 0014.
 * 自定义索引栏【找车-常用论坛-车系论坛】
 */
public class SideBar extends View {
    // 26字母
    public static String[] b = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    private int choose = -1;//选中
    private Paint paint = new Paint();
    private TextView mTextDialog;
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;

    public void setmTextDialog(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideBar(Context context) {
        super(context);
    }

    /**
     * 重写OnDraw方法
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();//获取对应长宽
        int width = getWidth();
        int singleHeight = height / b.length;//获取每一个字母高度
        for (int i = 0; i < b.length; i++) {
            paint.setColor(Color.rgb(33, 64, 98));
            paint.setTypeface(Typeface.DEFAULT_BOLD);//设置字体样式 默认的样式
            paint.setAntiAlias(true);//抗锯齿
            paint.setTextSize(20);
            //选中的状态
            if (i == choose) {
                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);//字体加粗
            }
            // x坐标等于 -> 中间字符串宽度的一半.
            float xPos = width / 2 - paint.measureText(b[i]) / 2;//取得字符串显示的宽度值
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(b[i], xPos, yPos, paint);
            paint.reset();//重置画笔
        }
    }

    /**
     * MotionEvent 传递机制（Android Touch事件）
     * public static final int ACTION_DOWN             = 0;单点触摸动作
     * public static final int ACTION_UP               = 1;单点触摸离开动作
     * public static final int ACTION_MOVE             = 2;触摸点移动动作
     * public static final int ACTION_CANCEL           = 3;触摸动作取消
     * public static final int ACTION_OUTSIDE          = 4;触摸动作超出边界
     * public static final int ACTION_POINTER_DOWN     = 5;多点触摸动作
     * public static final int ACTION_POINTER_UP       = 6;多点离开动作
     * 以下是一些非touch事件
     * public static final int ACTION_HOVER_MOVE       = 7;
     * public static final int ACTION_SCROLL           = 8;
     * public static final int ACTION_HOVER_ENTER      = 9;
     * public static final int ACTION_HOVER_EXIT       = 10;
     */
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();//点击y轴坐标
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        // 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
        final int count = (int) (y / getHeight() * b.length);
        switch (action) {
            case MotionEvent.ACTION_UP:
                //setBackgroundXXX的用处，设置这个View背景。setBackgroundDrawable 的参数为Drawable对象，
                setBackgroundDrawable(new ColorDrawable(0x00000000));
                choose = -1;
                //一个方法  Android中View绘制流程以及invalidate()等相关方法分析
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                setBackgroundResource(R.drawable.sidebar_background);
                if (oldChoose != count) {
                    if (count > 0 && count < b.length) {
                        if (listener != null) {
                            listener.OnTouchingLetterChangedListener(b[count]);
                        }
                        if (mTextDialog!=null) {
                            mTextDialog.setText(b[count]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }
                        choose=count;
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }

    /**
     * onTouchingChangedListener事件
     */
    public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    /**
     * 接口
     */
    public interface OnTouchingLetterChangedListener {
        void OnTouchingLetterChangedListener(String s);
    }
}
