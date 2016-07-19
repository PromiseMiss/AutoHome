package com.project.main.autohome.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by youyo on 2016/7/18 0018.
 */
public class MyIntoAdapter extends FragmentPagAdapter {
    private List<Fragment> myData;
    private List<String> myTitle;

    public MyIntoAdapter(FragmentManager fm, List<Fragment> data, List<String> title) {
        super(fm, data, title);
        this.myTitle = title;
        this.myData = data;

    }
    @Override
    public Fragment getItem(int position) {
        return myData.get(position);
    }

    @Override
    public int getCount() {
        return myData != null ? myData.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return myTitle.get(position);

    }
}
