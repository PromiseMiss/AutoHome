package com.project.main.autohome.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.main.autohome.ui.activity.AbsBaseActivity;

/**
 * Created by youyo on 2016/7/11 0011.
 */
public abstract class AbsBaseFragment extends Fragment {
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(), container, false);
    }

    protected abstract int setLayout();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected abstract void initView();

    /*简化findViewById*/
    protected <T extends View> T byView(int resId) {
        return (T) getView().findViewById(resId);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();

    protected void go2(Context from, Class<? extends AbsBaseActivity> to) {
        Intent intent = new Intent(context, to);
        context.startActivity(intent);
    }
}
