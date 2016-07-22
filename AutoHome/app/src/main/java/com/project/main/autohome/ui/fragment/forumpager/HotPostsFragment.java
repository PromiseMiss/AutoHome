package com.project.main.autohome.ui.fragment.forumpager;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.HotDataBean;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.tools.CustomListView;
import com.project.main.autohome.ui.activity.HotPostsActivity;
import com.project.main.autohome.ui.adapter.HotpastsAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by youyo on 2016/7/13 0013.
 * 热帖页  加载了自定义ListView
 */
public class HotPostsFragment extends AbsBaseFragment implements VolleyInterfaceResult, CustomListView.OnAutoHomeRefreshListener {
    private CustomListView fo_hotpasts_ls;
    private String url = NetUrl.HOT_POSTS_URL;
    private List<HotDataBean.ResultBean.ListBean> as;

    @Override
    protected int setLayout() {
        return R.layout.fo_hotpasts_frag;
    }

    @Override
    protected void initView() {
        fo_hotpasts_ls = byView(R.id.fo_hotpasts_ls);
    }

    @Override
    protected void initData() {
        VolleyInstence.getInstence(getContext()).startRequest(url, this);
        fo_hotpasts_ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), HotPostsActivity.class);
                String hotUrl = NetUrl.HOT_TOP_URL + as.get(position).getTopicid() + NetUrl.HTOP_BOTTOM_URL;
                Log.d("HotPostsFragment", hotUrl);
                intent.putExtra("url", hotUrl);
                startActivity(intent);
            }
        });
    }

    @Override
    public void success(String str) {
        Gson gson = new Gson();
        HotDataBean hotDataBean = gson.fromJson(str, HotDataBean.class);
        as = hotDataBean.getResult().getList();
        HotpastsAdapter hotpastsAdapter = new HotpastsAdapter(as, getContext());
        hotpastsAdapter.setHotpastaBeen(as);
        fo_hotpasts_ls.setAdapter(hotpastsAdapter);
//        fo_hotpasts_ls.setOnAutoHomeRefreshListener(this);
    }

    @Override
    public void failure() {

    }

    @Override
    public void onRefresh() {

    }
}
