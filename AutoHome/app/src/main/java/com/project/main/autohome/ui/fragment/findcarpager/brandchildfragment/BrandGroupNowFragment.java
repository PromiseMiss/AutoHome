package com.project.main.autohome.ui.fragment.findcarpager.brandchildfragment;

import android.widget.ExpandableListView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.BrandGroupBean;
import com.project.main.autohome.model.bean.EventBean;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.ui.adapter.BrandGroupNowAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by youyo on 2016/7/22 0022.
 * 抽屉的在售页  -- 品牌总页
 */
public class BrandGroupNowFragment extends AbsBaseFragment implements VolleyInterfaceResult {
    private ListView listView;
    private BrandGroupNowAdapter nowAdapter;
    private List<BrandGroupBean.ResultBean.FctlistBean> brandNowBeen;
    private ExpandableListView expandableListView;
    private String urlBan = NetUrl.Test;
    private EventBus eventBus;
    private EventBean eventBean;
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
        nowAdapter = new BrandGroupNowAdapter(getContext(), brandNowBeen);
        VolleyInstence.getInstence(getContext()).startRequest(url, this);
        // 注册订阅者
//        eventBus.getDefault().register(this);

    }

    @Subscribe
    public void onRecever(EventBean bean) {
        url = bean.getStr();
    }

    @Override
    public void success(String str) {
        Gson gson = new Gson();
        BrandGroupBean groupBean = gson.fromJson(str, BrandGroupBean.class);
        brandNowBeen = groupBean.getResult().getFctlist();
        expandableListView.setAdapter(nowAdapter);
        for (int i = 0; i < nowAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
    }

    @Override
    public void failure() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
