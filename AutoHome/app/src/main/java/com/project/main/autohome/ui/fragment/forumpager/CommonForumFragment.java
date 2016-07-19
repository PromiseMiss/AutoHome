package com.project.main.autohome.ui.fragment.forumpager;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.project.main.autohome.R;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

/**
 * Created by youyo on 2016/7/13 0013.
 * 常用论坛
 */
public class CommonForumFragment extends AbsBaseFragment implements View.OnClickListener {
    private LinearLayout fo_comm_carcomm, fo_comm_city, fo_comm_theme_comm;

    @Override
    protected int setLayout() {
        return R.layout.fo_commonforum_frag;
    }

    @Override
    protected void initView() {
        fo_comm_carcomm = byView(R.id.fo_comm_carcomm);
        fo_comm_city = byView(R.id.fo_comm_city);
        fo_comm_theme_comm = byView(R.id.fo_comm_theme_comm);

    }

    @Override
    protected void initData() {
        fo_comm_carcomm.setOnClickListener(this);
        fo_comm_city.setOnClickListener(this);
        fo_comm_theme_comm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fo_comm_carcomm:
                Toast.makeText(getContext(), "车系", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fo_comm_city:
                Toast.makeText(getContext(), "地区", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fo_comm_theme_comm:
                Toast.makeText(getContext(), "主题", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
