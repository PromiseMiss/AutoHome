package com.project.main.autohome.ui.fragment.fragmentpager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.project.main.autohome.R;
import com.project.main.autohome.ui.adapter.MyIntoAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youyo on 2016/7/11 0011.
 * 我 页
 */
public class MyFragment extends AbsBaseFragment {
    private TabLayout mytab;
    private ViewPager myPager;
    private MyIntoAdapter myAdapter;
    private List<String> myTitle;
    private List<Fragment> myFragment;

    @Override
    protected int setLayout() {
        return R.layout.my_fargment;
    }

    @Override
    protected void initView() {
        mytab = byView(R.id.my_tabLayout);
        myPager = byView(R.id.my_vp);

    }

    @Override
    protected void initData() {
        myTitle = new ArrayList<>();
        myFragment = new ArrayList<>();
        myFragment.add(new MyChildFragment());
        myTitle.add("我");
        myAdapter = new MyIntoAdapter(getChildFragmentManager(), myFragment, myTitle);
        myPager.setAdapter(myAdapter);
        mytab.setTabMode(TabLayout.GRAVITY_FILL);
        mytab.setupWithViewPager(myPager);

    }
}
