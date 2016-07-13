package com.project.main.autohome.ui.fragment.forumpager;

import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.HotDataBean;
import com.project.main.autohome.ui.adapter.HotpastsAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by youyo on 2016/7/13 0013.
 * 热帖页
 */
public class HotPostsFragment extends AbsBaseFragment {
    private ListView fo_hotpasts_ls;
    private String url = "http://club.app.autohome.com.cn/club_v6.1.0/club/shotfoumlist-pm1-p1-s50.json";
    private RequestQueue queue;// 请求队列


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
        queue = Volley.newRequestQueue(getContext());
        StringRequest sr = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                HotDataBean hotDataBean = gson.fromJson(response, HotDataBean.class);
                List<HotDataBean.ResultBean.ListBean> as = hotDataBean.getResult().getList();
                HotpastsAdapter hotpastsAdapter = new HotpastsAdapter(as, getContext());

                hotpastsAdapter.setHotpastaBeen(as);
                fo_hotpasts_ls.setAdapter(hotpastsAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(sr);

    }
}
