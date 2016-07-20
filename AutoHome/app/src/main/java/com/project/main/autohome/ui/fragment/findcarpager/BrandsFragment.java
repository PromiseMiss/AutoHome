package com.project.main.autohome.ui.fragment.findcarpager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.BrandBean;
import com.project.main.autohome.model.bean.BrandIconBean;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.ui.adapter.BrandExpandableAdapter;
import com.project.main.autohome.ui.adapter.BrandGVAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by youyo on 2016/7/13 0013.
 * 品牌页 ---- 找车总页
 */
public class BrandsFragment extends AbsBaseFragment implements VolleyInterfaceResult {

    private BrandGVAdapter gvAdapter;
    private BrandExpandableAdapter expandableAdapter;
    private ExpandableListView listView;
    private GridView gridView;
    private String url = NetUrl.BRANDGV_URL;
    private String iconUrl = NetUrl.BRAND_BIAOZHI;

    @Override
    protected int setLayout() {
        return R.layout.brand_expandable;
    }

    @Override
    protected void initView() {

        listView = byView(R.id.brand_ExpandableListView);

    }

    @Override
    protected void initData() {
        // 布局注入，原这个布局是ExpandableListView 需要这个页面滑动
        View view = LayoutInflater.from(getContext()).inflate(R.layout.find_brands_fragment, null);
        gridView = (GridView) view.findViewById(R.id.brand_GridView);// 初始化GridView
        listView.addHeaderView(view);
        gvAdapter = new BrandGVAdapter(getContext());
        // Gradview
        VolleyInstence.getInstence(getContext()).startRequest(url, this);
        // 解析下面的二级列表
        VolleyInstence.getInstence(getContext()).startRequest(iconUrl, new VolleyInterfaceResult() {
            @Override
            public void success(String str) {
                Gson gson = new Gson();
                BrandIconBean brandIconBean = gson.fromJson(str, BrandIconBean.class);
                expandableAdapter = new BrandExpandableAdapter(getContext(), brandIconBean.getResult().getBrandlist());
                listView.setAdapter(expandableAdapter);

                for (int i = 0; i < expandableAdapter.getGroupCount(); i++) {
                    listView.expandGroup(i);
                }
            }

            @Override
            public void failure() {

            }
        });

    }

    // 解析GridView部分中间的图标
    @Override
    public void success(String str) {
        Gson gson = new Gson();
        BrandBean brandBean = gson.fromJson(str, BrandBean.class);
        List<BrandBean.ResultBean.ListBean> listBeen = brandBean.getResult().getList();
        gvAdapter.setBrandBeen(listBeen);
        gridView.setAdapter(gvAdapter);
    }

    @Override
    public void failure() {

    }
}
