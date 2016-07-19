package com.project.main.autohome.ui.fragment.forumpager;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.HotDataBean;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.tools.CustomListView;
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
    }

    @Override
    public void success(String str) {
        Gson gson = new Gson();
        HotDataBean hotDataBean = gson.fromJson(str, HotDataBean.class);
        List<HotDataBean.ResultBean.ListBean> as = hotDataBean.getResult().getList();
        HotpastsAdapter hotpastsAdapter = new HotpastsAdapter(as, getContext());
        hotpastsAdapter.setHotpastaBeen(as);
        fo_hotpasts_ls.setAdapter(hotpastsAdapter);
        fo_hotpasts_ls.setOnAutoHomeRefreshListener(this);
    }

    @Override
    public void failure() {

    }

    @Override
    public void onRefresh() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                fo_hotpasts_ls.setOnRefreshComplete();
            }
        }.execute((Void[]) null);
    }
}
