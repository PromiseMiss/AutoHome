package com.project.main.autohome.ui.fragment.findcarpager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.FilterBean;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.tools.CustomListView;
import com.project.main.autohome.ui.adapter.FilterAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by youyo on 2016/7/13 0013.
 * 筛选页  ----- 找车总页
 */
public class FilterFragment extends AbsBaseFragment implements View.OnClickListener {
    private CustomListView listView;
    private FilterAdapter adapter;
    private TextView price_tv;
    private PopupWindow popupWindow;
    private String url = NetUrl.FILTER;

    @Override
    protected int setLayout() {
        return R.layout.find_filter_fragment;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.price_list);
        price_tv = byView(R.id.price_tv);
    }

    @Override
    protected void initData() {
        adapter = new FilterAdapter(getContext());
        VolleyInstence.getInstence(getContext()).startRequest(url, new VolleyInterfaceResult() {
            @Override
            public void success(String str) {
                Gson gson = new Gson();
                FilterBean seriesBean = gson.fromJson(str, FilterBean.class);
                List<FilterBean.ResultBean.SeriesBean> beanList = seriesBean.getResult().getSeries();
                adapter.setPricBean(beanList);
                listView.setAdapter(adapter);
            }

            @Override
            public void failure() {

            }
        });

        price_tv.setOnClickListener(this);

    }

    /**
     * 弹出pop的事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        initPop();
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(price_tv);
        }
    }

    private void initPop() {
        popupWindow = new PopupWindow(getContext());
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 加载布局
        View view = LayoutInflater.from(getContext()).inflate(R.layout.find_filter_pop_itm, null);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.load_icon_dial2x));
        popupWindow.setFocusable(true);// 获取焦点
        // 点击收回
        popupWindow.setOutsideTouchable(true);
    }
}
