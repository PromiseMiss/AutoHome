package com.project.main.autohome.ui.fragment.findcarpager;

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
public class FilterFragment extends AbsBaseFragment {
    private CustomListView listView;
    private FilterAdapter adapter;
    private String url = NetUrl.FILTER;

    @Override
    protected int setLayout() {
        return R.layout.find_filter_fragment;
    }

    @Override
    protected void initView() {
        listView = byView(R.id.price_list);
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

    }
}
