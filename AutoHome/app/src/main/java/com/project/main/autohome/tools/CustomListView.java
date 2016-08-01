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
    private static final int DONE = 0; // 刷新完毕状态
    private static final int PULL_TO_REFRESH = 1; // 下拉刷新状态
    private static final int RELEASE_TO_REFRESH = 2; // 松开刷新状态
    private static final int REFRESHING = 3; // 正在刷新
    private static final int RATIO = 3;
    private LinearLayout headerView; // 下拉刷新头
    private CustomView mAutoHome;  // 自定义视图
    private int headerViewHeight; // 头布局高度
    private float startY; // Y轴开始坐标
    private float offsetY; // y轴偏移量
    private TextView tv_pull_to_refresh;
    private OnAutoHomeRefreshListener mOnRefreshListener; // 刷新接口
    private int state; // 状态值
    private int mFirstVisibleItem;  // 第一项可见item索引
    private boolean isRecord;  // 是否记录
    private boolean isEnd;  // 是否结束
    private boolean isRefreable;  // 是否刷新

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

    /**
     * 回调接口，想实现下拉刷新的listview实现此接口
     *
     * @param onRefreshListener
     */
    public void setOnAutoHomeRefreshListener(OnAutoHomeRefreshListener onRefreshListener) {
        mOnRefreshListener = onRefreshListener;
        isRefreable = true;
    }

    /**
     * 刷新完成，从主线程发送过来，并且改变headview的状态和文字动画信息
     */
    public void setOnRefreshComplete() {
        // 一定要将isEnd设置为true ，以便下次的下拉刷新
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
        headerView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.custom_view_item, this, false);
        //确定刷新的itemView的高度（测量头布局）
        measureView(headerView);
        // 头布局
        addHeaderView(headerView);
        // 设置头文件隐藏在ListView的第一项
        headerViewHeight = headerView.getMeasuredHeight();
        headerView.setPadding(0, -headerViewHeight, 0, 0);
        // 获取布局组件
        mAutoHome = (CustomView) headerView.findViewById(R.id.custom_item);
        tv_pull_to_refresh = (TextView) headerView.findViewById(R.id.custom_item_tv);
        mAnimContainer = (FrameLayout) headerView.findViewById(R.id.custom_framLayout);
        mAutoHomeAnim = (PointerView) headerView.findViewById(R.id.customPoint_view);
        // 动画
        animation = AnimationUtils.loadAnimation(context, R.anim.pointer_rotate);

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
        if (isEnd) {// 如果现在是刷新结束的状态，可以再次刷新了，在onRefreshComplete中设置
            if (isRefreable) { //
                switch (ev.getAction()) {
                    // 用户按下
                    case MotionEvent.ACTION_DOWN:
                        // 如果当前是在listview顶部并且没有记录Y坐标
                        if (mFirstVisibleItem != 0 && !isRecord) {
                            // 将isRecord设置为true，说明现在已经记录Y坐标
                            isRecord = true;
                            // 将当前y坐标赋值给startY起始y坐标
                            startY = (int) ev.getY();
                        }
                        break;
                    // 用户滑动
                    case MotionEvent.ACTION_MOVE:
                        // 再次得到y坐标，用来和startY相减来计算offsetY位移值
                        float tempY = ev.getY();
                        // 在判断一下是否为listview顶部并且没有记录y坐标
                        if (mFirstVisibleItem == 0 && !isRecord) {
                            isRecord = true;
                            startY = tempY;
                        }
                        // 如果当前状态不是正在刷新的状态，并且已经记录了y坐标
                        if (state != REFRESHING && isRecord) {
                            // 计算y的偏移量
                            offsetY = tempY - startY;
                            // 计算当前滑动的高度
                            float currentHeight = (-headerViewHeight + offsetY / 3);
                            // 用当前划定的高度和头部headerView的总高度进行比，计算出当前划定的百分比 0到1
                            float currentProgress = 1 + currentHeight / headerViewHeight;
                            // 如果当前百分比大于1了，将其值设置为1，
                            if (currentProgress >= 1) {
                                currentProgress = 1;
                            }
                            // 如果当前的状态时放开刷新，并且已经记录y坐标
                            if (state == RELEASE_TO_REFRESH && isRecord) {
                                setSelection(0);
                                // 如果当前滑动的距离小于headerView的总高度
                                if (-headerViewHeight + offsetY / RATIO < 0) {
                                    // 将状态设置为下拉刷新状态
                                    state = PULL_TO_REFRESH;
                                    // 根据状态改变headerView
                                    changeHeaderByState(state);
                                    // 如果当前y的位移值小于0，即为headerView隐藏了
                                } else if (offsetY <= 0) {
                                    // 将状态变为DONE
                                    state = DONE;
                                    changeHeaderByState(state);
                                }
                            }
                            // 如果当前状态为下拉刷新并且已经记录y坐标
                            if (state == PULL_TO_REFRESH && isRecord) {
                                setSelection(0);
                                // 如果下拉刷新大于等于headerView的总高度
                                if (-headerViewHeight + offsetY / RATIO >= 0) {
                                    //将状态变为放开刷新
                                    state = RELEASE_TO_REFRESH;
                                    //
                                    changeHeaderByState(state);
                                    // 如果当前y的位移小于0 ，即为headerView隐藏了
                                } else if (offsetY <= 0) {
                                    // 状态改为done
                                    state = DONE;
                                    changeHeaderByState(state);
                                }
                            }
                            // 如果当前位置为done并且已经记录y坐标
                            if (state == DONE && isRecord) {
                                // 如果位移值大于0
                                if (offsetY >= 0) {
                                    // 将状态改为下拉刷新状态
                                    state = PULL_TO_REFRESH;
                                }
                            }
                            // 如果为下拉刷新状态
                            if (state == PULL_TO_REFRESH) {
                                // 则改变headerView的padding来实现下拉的效果
                                headerView.setPadding(0, (int) (-headerViewHeight + offsetY / 3), 0, 0);
                                mAutoHome.setCurrentProgress(currentProgress);
                                mAutoHome.postInvalidate();
                            }
                            // 如果为放开刷新状态
                            if (state == RELEASE_TO_REFRESH) {
                                // 改变headerView的padding值
                                headerView.setPadding(0, (int) (-headerViewHeight + offsetY / 3), 0, 0);
                                mAutoHome.setCurrentProgress(currentProgress);
                                mAutoHome.postInvalidate();
                            }
                        }
                        break;
                    // 当用户抬手时
                    case MotionEvent.ACTION_UP:
                        // 如果当前状态为下拉刷新状态
                        if (state == PULL_TO_REFRESH) {
                            // 平滑的隐藏headerView
                            this.smoothScrollBy((int) (-headerViewHeight + offsetY / 3) + headerViewHeight, 500);
                            changeHeaderByState(state);
                        }
                        // 如果当前状态为放开刷新
                        if (state == RELEASE_TO_REFRESH) {
                            // 平滑的滑到正好显示headerView
                            this.smoothScrollBy((int) (-headerViewHeight + offsetY / 3), 500);
                            // 将当前状态设置为正在刷新
                            state = REFRESHING;
                            // 回调接口的onRefresh方法
                            mOnRefreshListener.onRefresh();
                            changeHeaderByState(state);
                        }
                        // 所有手势执行完，将记录y坐标的isRecord改为false，以便于下次手势的执行
                        isRecord = false;
                        break;
                }

            }
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 根据状态改变headerView的动画和文字显示
     *
     * @param state
     */
    private void changeHeaderByState(int state) {
        switch (state) {
            case DONE:// 如果是隐藏的状态
                // 设置headerView的padding为隐藏
                headerView.setPadding(0, -headerViewHeight, 0, 0);
                //第一状态的view显示出来
                mAutoHome.setVisibility(View.VISIBLE);
                //先停止一下第二阶段view的动画
                mAutoHomeAnim.clearAnimation();
                //将第二阶段view隐藏起来
                mAnimContainer.setVisibility(View.GONE);
                break;
            case RELEASE_TO_REFRESH:// 当前状态为放开刷新
                tv_pull_to_refresh.setText("放开刷新");

                break;
            case PULL_TO_REFRESH: // 当前状态为下拉刷新
                tv_pull_to_refresh.setText("下拉刷新");
                //第一状态view显示出来
                mAutoHome.setVisibility(View.VISIBLE);
                //停止第二阶段动画
                mAutoHomeAnim.clearAnimation();
                //将第二阶段view隐藏
                mAnimContainer.setVisibility(View.GONE);
                break;
            case REFRESHING: //当前状态为正在刷新
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
