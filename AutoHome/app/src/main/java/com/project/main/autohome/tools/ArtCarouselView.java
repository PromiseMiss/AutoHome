package com.project.main.autohome.tools;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/13 0013.
 * 自定义轮播图控件
 */
public class ArtCarouselView extends FrameLayout implements ViewPager.OnPageChangeListener {
    private Context context;
    private int totalCount = 100;// 总数，为实现无限滑动设置的
    private int showCount; // 要显示的轮播图数量
    private int currenPosition = 0;// 当前ViewPager的位置
    private ViewPager viewPager;
    private LinearLayout carouselLayout;
    private Adapter adapter;
    private int pageItemWidth;// 每个指示器的宽度
    private boolean isUserTouched = false;

    public ArtCarouselView(Context context) {
        super(context);
        this.context = context;
    }

    public ArtCarouselView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public ArtCarouselView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(context).inflate(R.layout.art_varousel_view, null);
        this.viewPager = (ViewPager) view.findViewById(R.id.art_carousel_gallery);
        this.carouselLayout = (LinearLayout) view.findViewById(R.id.art_carouselLayoutPage);
        this.viewPager.addOnPageChangeListener(this);
        addView(view);
    }
}
