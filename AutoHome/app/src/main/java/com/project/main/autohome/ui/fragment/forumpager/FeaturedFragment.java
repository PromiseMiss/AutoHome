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
public class FeaturedFragment extends AbsBaseFragment implements VolleyInterfaceResult,
        View.OnClickListener, CustomRefreshListView.OnCustomRefreshListener, AdapterView.OnItemClickListener {
    private CustomRefreshListView fo_featured_ls;
    private DrawerLayout fo_drawer;
    private RecyclerView fo_featur_recView;
    private FeatureAdapter featureAdapter;
    private FeatureLSAdapter lsAdapter;
    private List<String> stringList;
    private ImageView fo_feature_iv;
    private ArrayAdapter<String> fo_mAdapter;
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
        initSeringTit();
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

        NetWorkConnectedToast.getConnectedToast().isNet(getContext());
        // 调用 下面方法显示抽屉内容
        isDrawByLs();
    }

    /**
     * 横向RecyclerView中那42个词组
     */
    private void initSeringTit() {
        stringList = new ArrayList<>();
        stringList.add(getResources().getString(R.string.featured_all));
        stringList.add(getResources().getString(R.string.featured_wife));
        stringList.add(getResources().getString(R.string.featured_gile));
        stringList.add(getResources().getString(R.string.featured_forum_person));
        stringList.add(getResources().getString(R.string.featured_forum));
        stringList.add(getResources().getString(R.string.featured_car));
        stringList.add(getResources().getString(R.string.featured_carefu));
        stringList.add(getResources().getString(R.string.featured_now));
        stringList.add(getResources().getString(R.string.featured_height));
        stringList.add(getResources().getString(R.string.featured_power));
        stringList.add(getResources().getString(R.string.featured_gather));
        stringList.add(getResources().getString(R.string.featured_bridge));
        stringList.add(getResources().getString(R.string.featured_super));
        stringList.add(getResources().getString(R.string.featured_overseas));
        stringList.add(getResources().getString(R.string.featured_classics));
        stringList.add(getResources().getString(R.string.featured_youger));
        stringList.add(getResources().getString(R.string.featured_ample));
        stringList.add(getResources().getString(R.string.featured_original));
        stringList.add(getResources().getString(R.string.featured_crown));
        stringList.add(getResources().getString(R.string.featured_change));
        stringList.add(getResources().getString(R.string.featured_keep));
        stringList.add(getResources().getString(R.string.featured_first));
        stringList.add(getResources().getString(R.string.featured_newcar));
        stringList.add(getResources().getString(R.string.featured_history));
        stringList.add(getResources().getString(R.string.featured_friend));
        stringList.add(getResources().getString(R.string.featured_honey));
        stringList.add(getResources().getString(R.string.featured_sticky));
        stringList.add(getResources().getString(R.string.featured_shoot));
        stringList.add(getResources().getString(R.string.featured_carfriend));
        stringList.add(getResources().getString(R.string.featured_bicycle));
        stringList.add(getResources().getString(R.string.featured_bytalk));
        stringList.add(getResources().getString(R.string.featured_NC));
        stringList.add(getResources().getString(R.string.featured_SW));
        stringList.add(getResources().getString(R.string.featured_EN));
        stringList.add(getResources().getString(R.string.featured_WN));
        stringList.add(getResources().getString(R.string.featured_central));
        stringList.add(getResources().getString(R.string.featured_south));
        stringList.add(getResources().getString(R.string.featured_harbor));
        stringList.add(getResources().getString(R.string.featured_over));
        stringList.add(getResources().getString(R.string.featured_thesea));
    }

    /**
     * 抽屉显示的内容
     */
    private void isDrawByLs() {
        String[] data = getResources().getStringArray(R.array.all);
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

    /**
     * 打开抽屉的监听事件
     *
     * @param v
     */
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
        // 关闭手势滑动，不能从侧边被划出
        fo_drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }
}
