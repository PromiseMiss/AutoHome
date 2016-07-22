package com.project.main.autohome.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.project.main.autohome.R;

/**
 * Created by youyo on 2016/7/18 0018.
 * 下拉刷新   自定义listView
 */
public class CustomListView extends ListView implements AbsListView.OnScrollListener {
    private static final int DONE = 0; // 完成
    private static final int PULL_TO_REFRESH = 1; // 下拉刷新
    private static final int RELEASE_TO_REFRESH = 2; // 松开刷新
    private static final int REFRESHING = 3; // 正在刷新
    private static final int RATIO = 3;
    private LinearLayout headerView; // 头布局
    private CustomView mAutoHome;  // 自定义视图
    private int headerViewHeight; // 头布局高度
    private float startY; // Y轴开始坐标
    private float offsetY;
    private TextView tv_pull_to_refresh;
    private OnAutoHomeRefreshListener mOnRefreshListener;
    private int state;
    private int mFirstVisibleItem;
    private boolean isRecord;  // 记录
    private boolean isEnd;
    private boolean isRefreable;

    private FrameLayout mAnimContainer;
    private PointerView mAutoHomeAnim; // 自定义视图
    private Animation animation; // 动画


    public CustomListView(Context context) {
        super(context);
        init(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //	实现内部接口并对外提供此方法
    public interface OnAutoHomeRefreshListener {
        void onRefresh();
    }

    // 对外提供的方法，，刷新监听的方法
    public void setOnAutoHomeRefreshListener(OnAutoHomeRefreshListener onRefreshListener) {
        mOnRefreshListener = onRefreshListener;
        isRefreable = true;
    }

    public void setOnRefreshComplete() {
        isEnd = true;
        state = DONE;
        changeHeaderByState(state);
    }

    private void init(Context context) {
        // 下拉时去掉蓝色的阴影
        setOverScrollMode(View.OVER_SCROLL_NEVER);
        //        下面的onScroll是他的方法（触屏滑动）里面要复写两个方法onScrollStateChanged和onScroll
        setOnScrollListener(this);
        // 绑定刷新布局
        // 头布局
        headerView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.custom_view_item, null, false);
        mAutoHome = (CustomView) headerView.findViewById(R.id.custom_item);
        tv_pull_to_refresh = (TextView) headerView.findViewById(R.id.custom_item_tv);
        mAnimContainer = (FrameLayout) headerView.findViewById(R.id.custom_framLayout);
        mAutoHomeAnim = (PointerView) headerView.findViewById(R.id.customPoint_view);
        // 动画
        animation = AnimationUtils.loadAnimation(context, R.anim.pointer_rotate);
        //确定刷新的itemView的高度
        measureView(headerView);
        // 头布局
        addHeaderView(headerView);
        headerViewHeight = headerView.getMeasuredHeight();

        headerView.setPadding(0, -1 * headerViewHeight, 0, 0);
        //一开始的状态就是下拉刷新完的状态，所以为DONE
        state = DONE;
        isEnd = true;
        isRefreable = false;// 是否正在刷新
    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    /**
     * @param absListView
     * @param firstVisibleItem 表示显示在屏幕第一个Listitem(部分显示的也算)
     * @param visibleItemCount 表示显示在屏幕可以见到的ListItem总数（部分显示的也算）
     * @param totalItemCount   表示listview的listItem的总数
     */
    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mFirstVisibleItem = firstVisibleItem;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isEnd) {
            if (isRefreable) {
                switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mFirstVisibleItem != 0 && !isRecord) {
                            isRecord = true;
                            startY = (int) ev.getY();
                        }
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float tempY = ev.getY();
                        if (mFirstVisibleItem == 0 && !isRecord) {
                            isRecord = true;
                            startY = tempY;
                        }
                        if (state != REFRESHING && isRecord) {
                            offsetY = tempY - startY;
                            float currentHeight = (-headerViewHeight + offsetY / RATIO);
                            float currentProgress = 1 + currentHeight / headerViewHeight;
                            if (currentProgress >= 1) {
                                currentProgress = 1;
                            }
                            if (state == RELEASE_TO_REFRESH && isRecord) {
                                setSelection(0);
                                if (-headerViewHeight + offsetY / RATIO < 0) {
                                    state = PULL_TO_REFRESH;
                                    changeHeaderByState(state);
                                } else if (offsetY <= 0) {
                                    state = DONE;
                                    changeHeaderByState(state);
                                }
                            }
                            if (state == PULL_TO_REFRESH && isRecord) {
                                setSelection(0);
                                if (-headerViewHeight + offsetY / RATIO >= 0) {
                                    state = RELEASE_TO_REFRESH;
                                    changeHeaderByState(state);
                                } else if (offsetY <= 0) {
                                    state = DONE;
                                    changeHeaderByState(state);
                                }
                            }
                            if (state == DONE && isRecord) {
                                if (offsetY >= 0) {
                                    state = PULL_TO_REFRESH;
                                }
                            }
                            if (state == PULL_TO_REFRESH) {
                                headerView.setPadding(0, (int) (-headerViewHeight + offsetY / RATIO), 0, 0);
                                mAutoHome.setCurrentProgress(currentProgress);
                                mAutoHome.postInvalidate();
                            }
                            if (state == RELEASE_TO_REFRESH) {
                                headerView.setPadding(0, (int) (-headerViewHeight + offsetY / RATIO), 0, 0);
                                mAutoHome.setCurrentProgress(currentProgress);
                                mAutoHome.postInvalidate();
                            }
                        }


                        break;
                    case MotionEvent.ACTION_UP:
                        if (state == PULL_TO_REFRESH) {
                            this.smoothScrollBy((int) (-headerViewHeight + offsetY / RATIO) + headerViewHeight, 500);
                            changeHeaderByState(state);
                        }
                        if (state == RELEASE_TO_REFRESH) {
                            this.smoothScrollBy((int) (-headerViewHeight + offsetY / RATIO), 500);
                            state = REFRESHING;
                            mOnRefreshListener.onRefresh();
                            changeHeaderByState(state);
                        }
                        isRecord = false;
                        break;
                }

            }
        }
        return super.onTouchEvent(ev);
    }

    private void changeHeaderByState(int state) {
        switch (state) {
            case DONE:
                headerView.setPadding(0, -headerViewHeight, 0, 0);
                //第一状态的view显示出来
                mAutoHome.setVisibility(View.VISIBLE);
                //先停止一下第二阶段view的动画
                mAutoHomeAnim.clearAnimation();
                //将第二阶段view隐藏起来
                mAnimContainer.setVisibility(View.GONE);
                break;
            case RELEASE_TO_REFRESH:
                tv_pull_to_refresh.setText("放开刷新");

                break;
            case PULL_TO_REFRESH:
                tv_pull_to_refresh.setText("下拉刷新");
                //第一状态view显示出来
                mAutoHome.setVisibility(View.VISIBLE);
                //停止第二阶段动画
                mAutoHomeAnim.clearAnimation();
                //将第二阶段view隐藏
                mAnimContainer.setVisibility(View.GONE);
                break;
            case REFRESHING:
                tv_pull_to_refresh.setText("正在刷新");
                //将第一阶段view隐藏
                mAutoHome.setVisibility(View.GONE);
                //将第二阶段view显示出来
                mAnimContainer.setVisibility(View.VISIBLE);
                //先停止第二阶段动画
                mAutoHomeAnim.clearAnimation();
                //启动第二阶段动画
                mAutoHomeAnim.startAnimation(animation);
                break;
            default:
                break;
        }
    }

    //确定刷新的itemView的高度
    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);

        //    public void setAdapter(BaseAdapter adapter) {
        //        lastUpdatedTextView.setText("最近更新" + new Date().toLocaleString());
        //        super.setAdapter(adapter);
        //    }
    }

}
