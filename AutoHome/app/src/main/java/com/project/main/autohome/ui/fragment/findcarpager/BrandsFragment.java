package com.project.main.autohome.ui.fragment.findcarpager;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.BrandBean;
import com.project.main.autohome.model.bean.BrandGroupAllBean;
import com.project.main.autohome.model.bean.BrandIconBean;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.tools.SideBar;
import com.project.main.autohome.ui.adapter.BrandExpandableAdapter;
import com.project.main.autohome.ui.adapter.BrandGVAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;
import com.project.main.autohome.ui.fragment.findcarpager.brandchildfragment.BrandGroupAllFragment;
import com.project.main.autohome.ui.fragment.findcarpager.brandchildfragment.BrandGroupNowFragment;

import java.util.List;

/**
 * Created by youyo on 2016/7/13 0013.
 * 品牌页 ---- 找车总页
 */
public class BrandsFragment extends AbsBaseFragment implements VolleyInterfaceResult, ExpandableListView.OnChildClickListener {

    private BrandGVAdapter gvAdapter;
    private BrandExpandableAdapter expandableAdapter;
    private ExpandableListView listView; // 二级列表
    private GridView gridView;
    private String urls = NetUrl.BRANDGV_URL;
    private String iconUrl = NetUrl.BRAND_BIAOZHI;

    private DrawerLayout drawerLayout;
    private LinearLayout brand_frameLayout;
    private RadioGroup radioGroup;
    private RadioButton brand_now, brand_all;
    private BrandGroupNowFragment brandGroupNowFragment;
    private BrandGroupAllFragment brandGroupAllFragment;
    private List<BrandIconBean.ResultBean.BrandlistBean> data;

    private List<BrandBean.ResultBean.ListBean> listBeen;
    private List<BrandIconBean.ResultBean.BrandlistBean> brandlistBeanList;
    private List<BrandGroupAllBean.ResultBean.FctlistBean> allBean;

    private SideBar sideBar;
    boolean flag;


    @Override
    protected int setLayout() {
        return R.layout.brand_expandable;
    }

    @Override
    protected void initView() {
        drawerLayout = byView(R.id.brand_drawer);
        listView = byView(R.id.brand_ExpandableListView);
        radioGroup = byView(R.id.brand_radio);
        brand_now = byView(R.id.brand_radio_now);
        brand_all = byView(R.id.brand_radio_all);

        sideBar = byView(R.id.sidrbar);
        brand_frameLayout = byView(R.id.brand_frameLayout);

    }

    @Override
    protected void initData() {
        // 布局注入，原这个布局是ExpandableListView 需要这个页面滑动
        View view = LayoutInflater.from(getContext()).inflate(R.layout.find_brands_fragment, null);
        gridView = (GridView) view.findViewById(R.id.brand_GridView);// 初始化GridView
        listView.addHeaderView(view);
        gvAdapter = new BrandGVAdapter(getContext());
        // Gradview
        VolleyInstence.getInstence(getContext()).startRequest(urls, this);
        // 解析下面的二级列表
        VolleyInstence.getInstence(getContext()).startRequest(iconUrl, new VolleyInterfaceResult() {
            @Override
            public void success(String str) {
                Gson gson = new Gson();
                BrandIconBean brandIconBean = gson.fromJson(str, BrandIconBean.class);
                brandlistBeanList = brandIconBean.getResult().getBrandlist();
                expandableAdapter = new BrandExpandableAdapter(getContext(), brandlistBeanList);
                listView.setAdapter(expandableAdapter);

                for (int i = 0; i < expandableAdapter.getGroupCount(); i++) {
                    listView.expandGroup(i);
                }
            }

            @Override
            public void failure() {

            }
        });


        listView.setGroupIndicator(null);
        // 二级列表监听，打开抽屉
        listView.setOnChildClickListener(this);
        radioGroup.check(R.id.brand_radio_now);

        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void OnTouchingLetterChangedListener(String s) {
                int position = expandableAdapter.getSectionForPosition(s.charAt(0));
                if (position != -1) {
                    listView.setSelection(position);
                }
            }
        });
    }

    // 解析GridView部分中间的图标
    @Override
    public void success(String str) {
        Gson gson = new Gson();
        BrandBean brandBean = gson.fromJson(str, BrandBean.class);
        listBeen = brandBean.getResult().getList();
        gvAdapter.setBrandBeen(listBeen);
        gridView.setAdapter(gvAdapter);
    }

    @Override
    public void failure() {

    }

    /**
     * 点击二级列表弹出的抽屉
     *
     * @param parent
     * @param v
     * @param groupPosition
     * @param childPosition
     * @param id
     * @return
     */
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        drawerLayout.openDrawer(Gravity.RIGHT);
        initBody(groupPosition, childPosition);
        return true;
    }

    /*通过Bundle传值*/
    private void initBody(int groupPosition, int childPosition) {
        String urlNow = "http://app.api.autohome.com.cn/autov5.0.0/cars/seriesprice-pm1-b" + brandlistBeanList.get(groupPosition).getList().get(childPosition).getId() + "-t1.json";
        String urlAll = "http://app.api.autohome.com.cn/autov5.0.0/cars/seriesprice-pm2-b" + brandlistBeanList.get(groupPosition).getList().get(childPosition).getId() + "-t2.json";
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        brandGroupAllFragment = new BrandGroupAllFragment();
        brandGroupNowFragment = new BrandGroupNowFragment();
        transaction.add(R.id.brand_frameLayout, brandGroupNowFragment);
        transaction.add(R.id.brand_frameLayout, brandGroupAllFragment);

        transaction.replace(R.id.brand_frameLayout, brandGroupNowFragment);
        transaction.commit();
        //        通过Bundle传值
        Bundle bundle = new Bundle();
        bundle.putString("key", urlNow);
        bundle.putString("All", urlAll);
        brandGroupNowFragment.setArguments(bundle);
        brandGroupAllFragment.setArguments(bundle);
        //        radioGroup的监听事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.brand_radio_now:
                        if (flag) {
//                            transaction.hide(brandGroupNowFragment).show(brandGroupAllFragment).commit();
                        } else {
                            transaction.hide(brandGroupAllFragment).show(brandGroupNowFragment).commit();
                        }
                        break;
                    case R.id.brand_radio_all:
                        if (flag) {
                            transaction.show(brandGroupNowFragment).hide(brandGroupAllFragment).commit();

                        } else {
                            transaction.show(brandGroupAllFragment).hide(brandGroupNowFragment).commit();
                            flag = true;

                        }
                        break;
                }
            }
        });
    }
}
