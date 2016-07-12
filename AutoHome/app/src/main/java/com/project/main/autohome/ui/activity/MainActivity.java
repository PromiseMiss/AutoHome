package com.project.main.autohome.ui.activity;


import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.project.main.autohome.R;
import com.project.main.autohome.ui.adapter.FragmentPagAdapter;
import com.project.main.autohome.ui.fragment.fragmentpager.ArticleFragment;
import com.project.main.autohome.ui.fragment.fragmentpager.FindCarFragment;
import com.project.main.autohome.ui.fragment.fragmentpager.ForumFragment;
import com.project.main.autohome.ui.fragment.fragmentpager.MyFragment;
import com.project.main.autohome.ui.fragment.fragmentpager.SaleFragment;

public class MainActivity extends AbsBaseActivity implements RadioGroup.OnCheckedChangeListener {
    private TabLayout act_main_tabLy;
    private ViewPager act_main_viewPager;
    private RadioGroup act_main_radioG;
    private FragmentPagAdapter pagAdapter;


    @Override
    protected int setlayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        act_main_radioG = byView(R.id.act_main_radio);


    }

    @Override
    protected void initDatas() {
        act_main_radioG.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (checkedId) {
            case R.id.act_main_article:
                fragmentTransaction.replace(R.id.fragLy, new ArticleFragment());
                break;
            case R.id.act_main_forum:
                fragmentTransaction.replace(R.id.fragLy, new ForumFragment());
                break;
            case R.id.act_main_findCar:
                fragmentTransaction.replace(R.id.fragLy, new FindCarFragment());
                break;
            case R.id.act_main_sale:
                fragmentTransaction.replace(R.id.fragLy, new SaleFragment());
                break;
            case R.id.act_main_my:
                fragmentTransaction.replace(R.id.fragLy, new MyFragment());
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }
}
