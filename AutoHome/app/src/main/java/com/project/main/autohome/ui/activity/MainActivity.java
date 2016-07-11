package com.project.main.autohome.ui.activity;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.project.main.autohome.R;

public class MainActivity extends AbsBaseActivity {
    private TabLayout act_main_tabLy;
    private ViewPager act_main_viewPager;
    private RadioGroup act_main_radioG;


    @Override
    protected int setlayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        act_main_tabLy = byView(R.id.act_main_tabLy);
        act_main_viewPager = byView(R.id.act_main_viewPager);

    }

    @Override
    protected void initDatas() {

    }
}
