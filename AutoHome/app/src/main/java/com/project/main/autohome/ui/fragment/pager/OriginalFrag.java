package com.project.main.autohome.ui.fragment.pager;

import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.project.main.autohome.R;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

/**
 * Created by youyo on 2016/7/12 0012.
 * 原创页
 */
public class OriginalFrag extends AbsBaseFragment {
    private TextView head_all_pop, orig_item_all_pop, orig_item_my_pop;

    private PopupWindow orig_pop;

    @Override
    protected int setLayout() {
        return R.layout.original_frag;
    }

    @Override
    protected void initView() {
        head_all_pop = byView(R.id.head_all_pop);
        orig_item_all_pop = byView(R.id.orig_item_all_pop);
        orig_item_my_pop = byView(R.id.orig_item_my_pop);
    }

    @Override
    protected void initData() {
        head_all_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "sdfsdf", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
