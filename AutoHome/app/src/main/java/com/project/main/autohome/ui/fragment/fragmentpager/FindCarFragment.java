package com.project.main.autohome.ui.fragment.fragmentpager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.project.main.autohome.R;
import com.project.main.autohome.ui.adapter.FragmentPagAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;
import com.project.main.autohome.ui.fragment.findcarpager.BrandsFragment;
import com.project.main.autohome.ui.fragment.findcarpager.FilterFragment;
import com.project.main.autohome.ui.fragment.findcarpager.PriceReductionFragment;
import com.project.main.autohome.ui.fragment.findcarpager.SecondHeadCar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youyo on 2016/7/11 0011.
 * 找车页
 */
public class FindCarFragment extends AbsBaseFragment {
    private ViewPager findcar_vp;
    private TabLayout findcar_tab;
    private List<String> findcar_title;
    private List<Fragment> findcar_fragment;
    private FragmentPagAdapter findcar_adapter;

    @Override
    protected int setLayout() {
        return R.layout.findcar_fargment;
    }

    @Override
    protected void initView() {
        findcar_vp = byView(R.id.findcar_vp);
        findcar_tab = byView(R.id.findcar_tabL);

    }

    @Override
    protected void initData() {
        findcar_title = new ArrayList<>();
        findcar_fragment = new ArrayList<>();

        findcar_fragment.add(new BrandsFragment());
        findcar_fragment.add(new FilterFragment());
        findcar_fragment.add(new PriceReductionFragment());
        findcar_fragment.add(new SecondHeadCar());

        findcar_title.add("品牌");
        findcar_title.add("筛选");
        findcar_title.add("降价");
        findcar_title.add("找二手车");
        findcar_adapter = new FragmentPagAdapter(getChildFragmentManager(), findcar_fragment, findcar_title);
        findcar_vp.setAdapter(findcar_adapter);
        findcar_tab.setupWithViewPager(findcar_vp);

    }
}
