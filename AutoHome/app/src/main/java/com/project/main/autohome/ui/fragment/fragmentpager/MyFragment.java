package com.project.main.autohome.ui.fragment.fragmentpager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.project.main.autohome.R;
import com.project.main.autohome.ui.adapter.MyIntoAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youyo on 2016/7/11 0011.
 * 我 页 （主要是Tablayout 就一页，个人中心页）
 */
public class MyFragment extends AbsBaseFragment implements View.OnClickListener {
    private TabLayout mytab;
    private ViewPager myPager;
    private MyIntoAdapter myAdapter;
    private List<String> myTitle;
    private List<Fragment> myFragment;
    private Button my_night;

    @Override
    protected int setLayout() {

        return R.layout.my_fargment;
    }

    @Override
    protected void initView() {
        mytab = byView(R.id.my_tabLayout);
        myPager = byView(R.id.my_vp);
        my_night = byView(R.id.my_night);

    }

    @Override
    protected void initData() {
        myTitle = new ArrayList<>();
        myFragment = new ArrayList<>();
        myFragment.add(new MyChildFragment()); /// TabLayout布局视图
        myTitle.add("我");
        myAdapter = new MyIntoAdapter(getChildFragmentManager(), myFragment, myTitle);
        myPager.setAdapter(myAdapter);
        mytab.setTabMode(TabLayout.GRAVITY_FILL);
        mytab.setupWithViewPager(myPager);

        my_night.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
    }
}
