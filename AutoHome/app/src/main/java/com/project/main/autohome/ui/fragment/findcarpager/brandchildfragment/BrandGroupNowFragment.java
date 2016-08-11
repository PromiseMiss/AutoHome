package com.project.main.autohome.ui.fragment.findcarpager.brandchildfragment;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.BrandGroupBean;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.ui.adapter.BrandGroupNowAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by youyo on 2016/7/22 0022.
 * 抽屉的在售页  -- 品牌总页
 */
public class BrandGroupNowFragment extends AbsBaseFragment {
    private BrandGroupNowAdapter nowAdapter;
    private List<BrandGroupBean.ResultBean.FctlistBean> brandNowBeen;
    private ExpandableListView expandableListView;
    private String url;


    @Override
    protected int setLayout() {
        return R.layout.brand_chikd_radio_now;
    }

    @Override
    protected void initView() {
        expandableListView = byView(R.id.brand_child_radio_now_ex);
    }

    @Override
    protected void initData() {
        nowAdapter = new BrandGroupNowAdapter(getContext());
        expandableListView.setGroupIndicator(null);
        expandableListView.setAdapter(nowAdapter);
        // 通过Bundle取值
        Bundle bundle = getArguments();
        url = bundle.getString("key");
        VolleyInstence.getInstence(getContext()).startRequest(url, new VolleyInterfaceResult() {
            @Override
            public void success(String str) {
                Gson gson = new Gson();
                BrandGroupBean groupBean = gson.fromJson(str, BrandGroupBean.class);
                brandNowBeen = groupBean.getResult().getFctlist();
                nowAdapter.setBrandNowBeen(brandNowBeen);
                expandableListView.setAdapter(nowAdapter);
                for (int i = 0; i < nowAdapter.getGroupCount(); i++) {
                    expandableListView.expandGroup(i);
                }
            }

            @Override
            public void failure() {

            }
        });
    }
}
