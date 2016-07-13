package com.project.main.autohome.ui.fragment.pager;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.UpCarouselBean;
import com.project.main.autohome.ui.adapter.UptoDateAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;

/**
 * Created by youyo on 2016/7/12 0012.
 * 最新页
 */
public class UptoDateFrag extends AbsBaseFragment {
    private ListView uptoDate_ls;
    private ArrayList<String> mDate;
    private UptoDateAdapter uptoDateAdapter;
    private ViewPager uptodata_vp_head;
    private ArrayList<UpCarouselBean> upCarouselBeen;

    @Override
    protected int setLayout() {
        return R.layout.uptodate_frag;
    }

    @Override
    protected void initView() {
        uptoDate_ls = byView(R.id.uoToDate_ls);
//        uptoDate_vp = byView(R.id.upToDate_vp);
        uptodata_vp_head = byView(R.id.uptodata_vp_head);

    }

    @Override
    protected void initData() {
        mDate = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mDate.add("dddfsgfsgs");
        }
        View view= LayoutInflater.from(getContext()).inflate(R.layout.art_item_up_ls,null);



        uptoDate_ls.addHeaderView(view);
        uptoDateAdapter = new UptoDateAdapter(getContext());
        uptoDateAdapter.setmDatas(mDate);
        uptoDate_ls.setAdapter(uptoDateAdapter);
    }
    private void initBanner(){

    }
}
