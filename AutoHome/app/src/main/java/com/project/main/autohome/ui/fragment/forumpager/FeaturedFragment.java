package com.project.main.autohome.ui.fragment.forumpager;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.FeatureAllBean;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.tools.CustomListView;
import com.project.main.autohome.tools.FeaturedListener;
import com.project.main.autohome.ui.adapter.FeatureAdapter;
import com.project.main.autohome.ui.adapter.FeatureLSAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youyo on 2016/7/13 0013.
 * 论坛----子页 精选推荐页
 */
public class FeaturedFragment extends AbsBaseFragment implements VolleyInterfaceResult, CustomListView.OnAutoHomeRefreshListener {
    private CustomListView fo_featured_ls;
    private RecyclerView fo_featur_recView;
    private FeatureAdapter featureAdapter;
    private FeatureLSAdapter lsAdapter;
    private List<String> stringList;


    @Override
    protected int setLayout() {
        return R.layout.fo_featured_fragment;
    }

    @Override
    protected void initView() {
        fo_featured_ls = byView(R.id.fo_featured_ls);
        fo_featur_recView = byView(R.id.fo_featured_recView);
    }

    @Override
    protected void initData() {
        // 两个适配器，一个RecyclerView横滑的，一个是ListView页的
        featureAdapter = new FeatureAdapter(getContext());
        lsAdapter = new FeatureLSAdapter(getContext());

        stringList = new ArrayList<>();
        for (int i = 0; i < 42; i++) {
            stringList.size();
        }
        featureAdapter.setList(stringList);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        fo_featur_recView.setLayoutManager(llm);
        fo_featur_recView.setAdapter(featureAdapter);
        VolleyInstence.getInstence(getContext()).startRequest(NetUrl.RECOMMEND_ALL[0], this);
        // 为横滑的RecyclerView添加事件监听
        featureAdapter.setFeaturedListener(new FeaturedListener() {
            @Override
            public void FeatListent(int pos) {
                VolleyInstence.getInstence(getContext()).startRequest(NetUrl.RECOMMEND_ALL[pos], new VolleyInterfaceResult() {
                    @Override
                    public void success(String str) {
                        Gson gson = new Gson();
                        FeatureAllBean allBean = gson.fromJson(str, FeatureAllBean.class);
                        List<FeatureAllBean.ResultBean.ListBean> list = allBean.getResult().getList();
                        lsAdapter.setLsList(list);
                        fo_featured_ls.setAdapter(lsAdapter);
                    }

                    @Override
                    public void failure() {

                    }
                });

            }
        });
        fo_featured_ls.setOnAutoHomeRefreshListener(this);

        initSeringTit();
    }

    private void initSeringTit() {
        stringList.add("全部");
        stringList.add("媳妇当车模");
        stringList.add("美人“记”");
        stringList.add("论坛名人堂");
        stringList.add("论坛讲师");
        stringList.add("汽车之家十年");
        stringList.add("精挑细选");
        stringList.add("现身说法");
        stringList.add("高端阵地");
        stringList.add("电动车");
        stringList.add("汇买车");
        stringList.add("行车点评");
        stringList.add("超级试驾员");
        stringList.add("海外购车");
        stringList.add("经典老车");
        stringList.add("妹子选车");
        stringList.add("优惠购车");
        stringList.add("原创大片");
        stringList.add("顶配风采");
        stringList.add("改装有理");
        stringList.add("养车有道");
        stringList.add("首发阵营");
        stringList.add("新车直播");
        stringList.add("历史选题");
        stringList.add("摩友天地");
        stringList.add("蜜月之旅");
        stringList.add("甜蜜婚礼");
        stringList.add("摄影课堂");
        stringList.add("车友聚会");
        stringList.add("单车部落");
        stringList.add("杂谈俱乐部");
        stringList.add("华北游记");
        stringList.add("西南游记");
        stringList.add("东北游记");
        stringList.add("西北游记");
        stringList.add("华中游记");
        stringList.add("华南游记");
        stringList.add("港澳台游");
        stringList.add("海外游记");
        stringList.add("沧海遗珠");
    }

    @Override
    public void success(String str) {
        Gson gson = new Gson();
        FeatureAllBean allBean = gson.fromJson(str, FeatureAllBean.class);
        List<FeatureAllBean.ResultBean.ListBean> lists = allBean.getResult().getList();
        lsAdapter.setLsList(lists);
        fo_featured_ls.setAdapter(lsAdapter);
    }

    @Override
    public void failure() {

    }

    /**
     * onRefresh 是自定义ListView时提供的方法，用来刷新用的
     */
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
                fo_featured_ls.setOnRefreshComplete();
                super.onPostExecute(aVoid);
            }
        }.execute((Void) null);
    }
}
