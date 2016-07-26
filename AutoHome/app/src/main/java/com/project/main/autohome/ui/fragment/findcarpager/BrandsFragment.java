package com.project.main.autohome.ui.fragment.findcarpager;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.BrandBean;
import com.project.main.autohome.model.bean.BrandIconBean;
import com.project.main.autohome.model.bean.EventBean;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.tools.SideBar;
import com.project.main.autohome.ui.adapter.BrandExpandableAdapter;
import com.project.main.autohome.ui.adapter.BrandGVAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;
import com.project.main.autohome.ui.fragment.findcarpager.brandchildfragment.BrandGroupAllFragment;
import com.project.main.autohome.ui.fragment.findcarpager.brandchildfragment.BrandGroupNowFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by youyo on 2016/7/13 0013.
 * 品牌页 ---- 找车总页
 */
public class BrandsFragment extends AbsBaseFragment implements VolleyInterfaceResult, ExpandableListView.OnChildClickListener, RadioGroup.OnCheckedChangeListener {

    private BrandGVAdapter gvAdapter;
    private BrandExpandableAdapter expandableAdapter;
    private ExpandableListView listView; // 二级列表
    private GridView gridView;
    private String urls = NetUrl.BRANDGV_URL;
    private String iconUrl = NetUrl.BRAND_BIAOZHI;

    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private RadioGroup radioGroup;
    private RadioButton brand_now, brand_all;
    private BrandGroupNowFragment brandGroupNowFragment;
    private BrandGroupAllFragment brandGroupAllFragment;
    private EventBus eventBus;
    private List<BrandIconBean.ResultBean.BrandlistBean> data;

    private String urlss;
    private List<BrandBean.ResultBean.ListBean> listBeen;
    private List<BrandIconBean.ResultBean.BrandlistBean> brandlistBeanList;

    private SideBar sideBar;
    //    private ListView ls;

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


        // 二级列表监听，打开抽屉
        listView.setOnChildClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
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
        drawerLayout.openDrawer(GravityCompat.END);
        urlss = "http://app.api.autohome.com.cn/autov5.0.0/cars/seriesprice-pm1-b" + brandlistBeanList.get(groupPosition).getList().get(childPosition).getId() + "-t1.json";
        EventBus.getDefault().post(new EventBean(urlss));
        return true;
    }

    /**
     * RadioGroup 监听事件
     * 并定义两个Fragment 用来显示不同数据信息
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (checkedId) {
            case R.id.brand_radio_now:
                if (brandGroupNowFragment == null) {
                    brandGroupNowFragment = new BrandGroupNowFragment();
                }
                fragmentTransaction.replace(R.id.brand_frameLayout, brandGroupNowFragment);
                break;
            case R.id.brand_radio_all:
                if (brandGroupAllFragment == null) {
                    brandGroupAllFragment = new BrandGroupAllFragment();
                }
                fragmentTransaction.replace(R.id.brand_frameLayout, brandGroupAllFragment);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();

    }

    @Subscribe
    public void onRecever() {

    }


}
