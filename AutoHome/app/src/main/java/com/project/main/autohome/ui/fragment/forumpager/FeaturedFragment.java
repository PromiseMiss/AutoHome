package com.project.main.autohome.ui.fragment.forumpager;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.FeatureAllBean;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.tools.CustomRefreshListView;
import com.project.main.autohome.tools.FeaturedListener;
import com.project.main.autohome.tools.NetWorkConnectedToast;
import com.project.main.autohome.ui.activity.FeatureActivity;
import com.project.main.autohome.ui.adapter.FeatureAdapter;
import com.project.main.autohome.ui.adapter.FeatureLSAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youyo on 2016/7/13 0013.
 * 论坛----子页 精选推荐页
 */
public class FeaturedFragment extends AbsBaseFragment implements VolleyInterfaceResult, View.OnClickListener, CustomRefreshListView.OnCustomRefreshListener, AdapterView.OnItemClickListener {
    private CustomRefreshListView fo_featured_ls;
    private DrawerLayout fo_drawer;
    private RecyclerView fo_featur_recView;
    private FeatureAdapter featureAdapter;
    private FeatureLSAdapter lsAdapter;
    private List<String> stringList;
    private ImageView fo_feature_iv;

    private ArrayAdapter<String> fo_mAdapter;
    private List<String> fo_draw_list;
    private ListView fo_feat_draw_ls;

    private List<FeatureAllBean.ResultBean.ListBean> lists;


    @Override
    protected int setLayout() {
        return R.layout.fo_featured_fragment;
    }

    @Override
    protected void initView() {
        fo_featured_ls = byView(R.id.fo_featured_ls);
        fo_featur_recView = byView(R.id.fo_featured_recView);
        fo_feature_iv = byView(R.id.fo_feature_iv);
        fo_drawer = byView(R.id.fo_feature_drawLayout);
        // 抽屉的LS
        fo_feat_draw_ls = byView(R.id.fo_feat_draw_ls);
    }

    @Override
    protected void initData() {
        // 图片监听
        fo_feature_iv.setOnClickListener(this);
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
        /// 解析的是全部那页，为了在一打开界面就显示内容而不是什么都没有
        VolleyInstence.getInstence(getContext()).startRequest(NetUrl.RECOMMEND_ALL[0], this);
        // 为横滑的RecyclerView添加事件监听
        featureAdapter.setFeaturedListener(new FeaturedListener() {
            @Override
            public void FeatListent(int pos) {
                // 解析的42页
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
        // 下拉刷新
        fo_featured_ls.setOnCustomRefreshListener(this);
        fo_featured_ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idNum = lists.get(position).getTopicid();
                String strUrl = NetUrl.FEATURED_QUERY + idNum + NetUrl.FEATURED_BOTTOM;
                Intent intent = new Intent(getContext(), FeatureActivity.class);
                intent.putExtra("url", strUrl);
                startActivity(intent);
            }
        });
        initSeringTit();

        NetWorkConnectedToast.getConnectedToast().isNet(getContext());

        isDrawByLs();

    }

    private void initSeringTit() {
        stringList.add("全部");
        stringList.add("媳妇当车模");
        stringList.add("美人“记”");
        stringList.add("论坛名 人堂");
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

    /**
     * 抽屉显示的内容
     */
    private void isDrawByLs() {
        String[] data = new String[]{"全部", "媳妇当车模", "美人“记”", "论坛名人堂", "论坛讲师",
                "汽车之家十年", "精挑细选", "现身说法", "高端阵地", "电动车", "汇买车", "行车点评",
                "超级试驾员", "海外购车", "经典老车", "妹子选车", "优惠购车", "原创大片", "顶配风采",
                "改装有理", "养车有道", "首发阵营", "新车直播", "历史选题", "摩友天地", "蜜月之旅",
                "甜蜜婚礼", "摄影课堂", "车友聚会", "单车部落", "杂谈俱乐部", "华北游记", "西南游记",
                "东北游记", "西北游记", "华中游记", "华南游记", "港澳台游", "海外游记", "沧海遗珠"};
        fo_mAdapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, data);
        fo_feat_draw_ls.setAdapter(fo_mAdapter);
        fo_feat_draw_ls.setOnItemClickListener(this);
    }

    /**
     * 全部（也就是第一页）
     *
     * @param str
     */
    @Override
    public void success(String str) {
        Gson gson = new Gson();
        FeatureAllBean allBean = gson.fromJson(str, FeatureAllBean.class);
        lists = allBean.getResult().getList();
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


    }

    @Override
    public void onClick(View v) {
        fo_drawer.openDrawer(GravityCompat.END);
    }

    /**
     * 抽屉的监听
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        VolleyInstence.getInstence(getContext()).startRequest(NetUrl.RECOMMEND_ALL[position], new VolleyInterfaceResult() {
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
}
