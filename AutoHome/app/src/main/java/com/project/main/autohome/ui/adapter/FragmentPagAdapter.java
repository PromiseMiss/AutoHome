package com.project.main.autohome.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by youyo on 2016/7/11 0011.
 * 推荐页Adapter
 */
public class FragmentPagAdapter extends FragmentPagerAdapter {
    private List<Fragment> mdata;
    private List<String> mTitle;

    public FragmentPagAdapter(FragmentManager fm, List<Fragment> data, List<String> title) {
        super(fm);
        this.mdata = data;
        this.mTitle = title;

    }

    @Override
    public Fragment getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public int getCount() {
        return mdata != null ? mdata.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);

    }
}
