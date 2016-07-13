package com.project.main.autohome.ui.fragment.fragmentpager;

import android.os.Bundle;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

/**
 * Created by youyo on 2016/7/12 0012.
 */
public class AllFragment extends AbsBaseFragment {
    private String url;
    private TextView all_tv;

    public AllFragment() {

    }


    @Override
    protected int setLayout() {
        return R.layout.all_fragment;
    }

    @Override
    protected void initView() {
        all_tv = byView(R.id.art_tv_all);

    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        this.url = bundle.getString("url_key");
        all_tv.setText(url);
    }

    public static AllFragment getInstance(String url) {
        AllFragment allFragment = new AllFragment();
        //        利用Activity向Fragment传值的方法，将网址传入
        Bundle bundle = new Bundle();
        bundle.putString("url_key", url);
        //        Fragment 对象调用 setArguments方法传值
        allFragment.setArguments(bundle);
        return allFragment;
    }
}
