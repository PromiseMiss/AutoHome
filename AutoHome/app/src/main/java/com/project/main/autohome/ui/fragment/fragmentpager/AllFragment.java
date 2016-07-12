package com.project.main.autohome.ui.fragment.fragmentpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.project.main.autohome.R;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

/**
 * Created by youyo on 2016/7/12 0012.
 */
public class AllFragment extends AbsBaseFragment {
    private String url;
    public AllFragment(){

    }

    public AllFragment(String url){
        this.url = url;
    }

    @Override
    protected int setLayout() {
        return R.layout.all_fragment;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
    public static Fragment getInstance(String url){
        AllFragment allFragment = new AllFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        allFragment.setArguments(bundle);
        return allFragment;
    }
}
