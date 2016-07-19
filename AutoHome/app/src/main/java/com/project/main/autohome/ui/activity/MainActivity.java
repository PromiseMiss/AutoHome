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

/**
 * 主Activity
 */
public class MainActivity extends AbsBaseActivity implements RadioGroup.OnCheckedChangeListener {
    private TabLayout act_main_tabLy;
    private ViewPager act_main_viewPager;
    private RadioGroup act_main_radioG;
    private FragmentPagAdapter pagAdapter;

    private ArticleFragment articleFragment;
    private ForumFragment forumFragment;
    private FindCarFragment findCarFragment;
    private SaleFragment saleFragment;
    private MyFragment myFragment;


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
        act_main_radioG.check(R.id.act_main_article);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (checkedId) {
            case R.id.act_main_article:
                if (articleFragment == null) {
                    articleFragment = new ArticleFragment();
                }
                fragmentTransaction.replace(R.id.fragLy, articleFragment);
                break;
            case R.id.act_main_forum:
                if (forumFragment == null) {
                    forumFragment = new ForumFragment();
                }
                fragmentTransaction.replace(R.id.fragLy, forumFragment);
                break;
            case R.id.act_main_findCar:
                if (findCarFragment == null) {
                    findCarFragment = new FindCarFragment();
                }
                fragmentTransaction.replace(R.id.fragLy, findCarFragment);
                break;
            case R.id.act_main_sale:
                if (saleFragment == null) {
                    saleFragment = new SaleFragment();
                }
                fragmentTransaction.replace(R.id.fragLy, saleFragment);
                break;
            case R.id.act_main_my:
                if (myFragment == null) {

                    myFragment = new MyFragment();
                }
                fragmentTransaction.replace(R.id.fragLy, myFragment);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }
}
