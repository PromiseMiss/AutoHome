package com.project.main.autohome.ui.fragment.findcarpager;

import android.widget.GridView;

import com.project.main.autohome.R;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

/**
 * Created by youyo on 2016/7/13 0013.
 * 找二手车 ---- 总页找车
 */
public class SecondHeadCar extends AbsBaseFragment {
    private GridView find_second_gv;
    private String[] name = {"","","","","","","","","","","","","","","","","","","","","","","","","","","",""};

    @Override
    protected int setLayout() {
        return R.layout.find_secondcar_fragment;
    }

    @Override
    protected void initView() {
        find_second_gv = byView(R.id.find_second_gv);


    }

    @Override
    protected void initData() {

    }
}
