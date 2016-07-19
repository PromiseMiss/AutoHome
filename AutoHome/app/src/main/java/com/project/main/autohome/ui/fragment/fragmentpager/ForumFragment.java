package com.project.main.autohome.ui.fragment.fragmentpager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.project.main.autohome.R;
import com.project.main.autohome.ui.adapter.FragmentPagAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;
import com.project.main.autohome.ui.fragment.forumpager.CommonForumFragment;
import com.project.main.autohome.ui.fragment.forumpager.FeaturedFragment;
import com.project.main.autohome.ui.fragment.forumpager.HotPostsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youyo on 2016/7/11 0011.
 * 论坛页
 */
public class ForumFragment extends AbsBaseFragment {
    private TabLayout foru_tab;
    private ViewPager foru_vp;
    private FragmentPagAdapter foru_adapter;
    private List<String> title_foru;
    private List<Fragment> frag_foru;

    @Override
    protected int setLayout() {
        return R.layout.forum_fragment;
    }

    @Override
    protected void initView() {
        foru_tab = byView(R.id.foru_tab);
        foru_vp = byView(R.id.foru_vp);

    }

    @Override
    protected void initData() {
        title_foru = new ArrayList<>();
        frag_foru = new ArrayList<>();
        frag_foru.add(new FeaturedFragment());
        frag_foru.add(new HotPostsFragment());
        frag_foru.add(new CommonForumFragment());
        title_foru.add("精选推荐");
        title_foru.add("热帖");
        title_foru.add("常用论坛");
        foru_adapter = new FragmentPagAdapter(getChildFragmentManager(), frag_foru, title_foru);
        foru_vp.setAdapter(foru_adapter);
        foru_vp.setOffscreenPageLimit(2);
        foru_tab.setupWithViewPager(foru_vp);

    }
}
