package com.project.main.autohome.ui.fragment.forumpager;

import android.widget.ListView;

import com.project.main.autohome.R;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

/**
 * Created by youyo on 2016/7/13 0013.
 * 论坛----子页 精选页
 */
public class FeaturedFragment extends AbsBaseFragment {
    private ListView fo_featured_ls;


    @Override
    protected int setLayout() {

        return R.layout.fo_featured_fragment;

    }

    @Override
    protected void initView() {
        fo_featured_ls = byView(R.id.fo_featured_ls);

    }

    @Override
    protected void initData() {

    }
}
