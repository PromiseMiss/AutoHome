package com.project.main.autohome.ui.fragment.pager;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.BulletinBean;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.tools.CustomListView;
import com.project.main.autohome.ui.adapter.BulletinAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by youyo on 2016/7/12 0012.
 * 快报页
 */
public class BulletinFrag extends AbsBaseFragment implements VolleyInterfaceResult, CustomListView.OnAutoHomeRefreshListener {
    private CustomListView bul_ls;
    private BulletinAdapter bulAdapter;
    private String url = NetUrl.BULLETIN_URL;

    @Override
    protected int setLayout() {
        return R.layout.bulletin_frag;
    }

    @Override
    protected void initView() {
        bul_ls = byView(R.id.bulletion_ls);

    }

    @Override
    protected void initData() {
        bulAdapter = new BulletinAdapter(getContext());
        VolleyInstence.getInstence(getContext()).startRequest(url, this);
        bul_ls.setOnAutoHomeRefreshListener(this);
    }

    @Override
    public void success(String str) {
        Gson gson = new Gson();
        BulletinBean bullBean = gson.fromJson(str, BulletinBean.class);
        List<BulletinBean.ResultBean.ListBean> listBeen = bullBean.getResult().getList();
        bulAdapter.setBulletinBeen(listBeen);
        bul_ls.setAdapter(bulAdapter);
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
