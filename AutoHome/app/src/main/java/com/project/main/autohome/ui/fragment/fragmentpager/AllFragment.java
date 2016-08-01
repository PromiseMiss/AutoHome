package com.project.main.autohome.ui.fragment.fragmentpager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.AllOfBean;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.tools.CustomListView;
import com.project.main.autohome.tools.NetWorkConnectedToast;
import com.project.main.autohome.ui.activity.AllActivity;
import com.project.main.autohome.ui.adapter.AllIntoAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by youyo on 2016/7/12 0012.
 * 推荐页 所有页   （引用了自定义ListView）
 */
public class AllFragment extends AbsBaseFragment implements VolleyInterfaceResult, CustomListView.OnAutoHomeRefreshListener {
    private String url;
    private CustomListView all_ls;
    private AllIntoAdapter allAdapter;
    private List<AllOfBean.ResultBean.NewslistBean> allbean;

    public AllFragment() {

    }


    @Override
    protected int setLayout() {
        return R.layout.all_fragment;
    }

    @Override
    protected void initView() {
        all_ls = byView(R.id.all_ls);

    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        this.url = bundle.getString("url_key");

        allAdapter = new AllIntoAdapter(getContext());
        VolleyInstence.getInstence(getContext()).startRequest(url, this);
        // 给ListView添加刷新监听
        all_ls.setOnAutoHomeRefreshListener(this);

        all_ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), AllActivity.class);
                String allUrl = NetUrl.NEWS_TOP_URL + id + NetUrl.NEWS_BOTTOM_URL;
                intent.putExtra("allUrl", allUrl);
                getContext().startActivity(intent);
            }
        });

        // 检查网路
        NetWorkConnectedToast.getConnectedToast().isNet(getContext());
    }

    public static AllFragment getInstance(String url) {
        AllFragment allFragment = new AllFragment();
        //        利用Activity向Fragment传值的方法，将网址传入
        Bundle bundle = new Bundle();
        bundle.putString("url_key", url);
        //        Fragment 对象调用 setArguments方法传值
        allFragment.setArguments(bundle);
        return allFragment;
    }

    @Override
    public void success(String str) {
        Gson gson = new Gson();
        AllOfBean bean = gson.fromJson(str, AllOfBean.class);
        allbean = bean.getResult().getNewslist();
        allAdapter.setAllBean(allbean);
        all_ls.setAdapter(allAdapter);
    }

    @Override
    public void failure() {

    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {

    }
}
