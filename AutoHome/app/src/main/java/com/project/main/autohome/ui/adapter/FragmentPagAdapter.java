package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by youyo on 2016/7/11 0011.
 */
public class FragmentPagAdapter extends FragmentPagerAdapter {
    private Context context;


    public FragmentPagAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
