package com.project.main.autohome.ui.fragment.fragmentpager;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.project.main.autohome.R;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.ui.adapter.FragmentPagAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;
import com.project.main.autohome.ui.fragment.pager.BulletinFrag;
import com.project.main.autohome.ui.fragment.pager.OriginalFrag;
import com.project.main.autohome.ui.fragment.pager.UptoDateFrag;
import com.project.main.autohome.ui.fragment.pager.VideoFrag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youyo on 2016/7/11 0011.
 * 推荐页
 */
public class ArticleFragment extends AbsBaseFragment {
    private TabLayout act_main_tabLy;
    private ViewPager act_main_viewPager;
    private FragmentPagAdapter pagAdapter;
    private List<String> list_title;
    private List<Fragment> list_fragment;


    @Override
    protected int setLayout() {
        return R.layout.article_fragment;
    }

    @Override
    protected void initView() {
        act_main_tabLy = byView(R.id.act_main_tabLy);
        act_main_viewPager = byView(R.id.act_main_viewPager);

    }

    @Override
    protected void initData() {
        initTab();


    }

    protected void initTab() {
        list_title = new ArrayList<>();
        list_fragment = new ArrayList<>();

        list_fragment.add(new UptoDateFrag());
        list_fragment.add(new OriginalFrag());
        list_fragment.add(new BulletinFrag());
        list_fragment.add(new VideoFrag());


        list_title.add("最新");
        list_title.add("优创+");
        list_title.add("快报");
        list_title.add("视频");

        list_title.add("新闻");
        list_title.add("测评");
        list_title.add("导购");
        list_title.add("行情");
        list_title.add("用车");
        list_title.add("技术");
        list_title.add("文化");
        list_title.add("改装");
        list_title.add("游记");
        list_title.add("原创视频");
        list_title.add("说客");

        list_fragment.add(AllFragment.getInstance(NetUrl.NEWS_URL));
        list_fragment.add(AllFragment.getInstance(NetUrl.EVALUATION_URL));
        list_fragment.add(AllFragment.getInstance(NetUrl.GUIDE_URL));
        list_fragment.add(AllFragment.getInstance(NetUrl.QUOTATION_URL));
        list_fragment.add(AllFragment.getInstance(NetUrl.FIND_CAR_URL));
        list_fragment.add(AllFragment.getInstance(NetUrl.TECHNOLOGY_URL));
        list_fragment.add(AllFragment.getInstance(NetUrl.CULTURE_URL));
        list_fragment.add(AllFragment.getInstance(NetUrl.CONVERSION_URL));
        list_fragment.add(AllFragment.getInstance(NetUrl.TRAVEL_URL));
        list_fragment.add(AllFragment.getInstance(NetUrl.ORIGINALLY_VIDEO_URL));
        list_fragment.add(AllFragment.getInstance(NetUrl.LOBBYIST_URL));

        pagAdapter = new FragmentPagAdapter(getChildFragmentManager(), list_fragment, list_title);
        act_main_viewPager.setAdapter(pagAdapter);
        act_main_viewPager.setOffscreenPageLimit(2);
        act_main_tabLy.setupWithViewPager(act_main_viewPager);

    }
}
