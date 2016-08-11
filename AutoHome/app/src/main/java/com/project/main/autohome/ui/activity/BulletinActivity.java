package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.widget.ListView;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.BulletinActivityBean;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.ui.adapter.BulletinActivityAdapter;

import java.util.List;

/**
 * Created by youyo on 2016/7/21 0021.
 * 快报 详情页
 */
public class BulletinActivity extends AbsBaseActivity {
    private List<BulletinActivityBean.ResultBean.MessagelistBean> beanList;
    private BulletinActivityAdapter bulletinAdapter;
    private BulletinActivityBean activityBean;
    private ListView bulletin_LS;

    @Override
    protected int setlayout() {
        return R.layout.activity_bulletin;
    }

    @Override
    protected void initViews() {
        bulletin_LS = byView(R.id.bulletin_LS);
    }

    @Override
    protected void initDatas() {
        bulletinAdapter = new BulletinActivityAdapter(this);
        Intent intent = getIntent();
        String url = intent.getStringExtra("bullUrl");
        VolleyInstence.getInstence(this).startRequest(url, new VolleyInterfaceResult() {
            @Override
            public void success(String str) {
                Gson gson = new Gson();
                activityBean = gson.fromJson(str, BulletinActivityBean.class);
                beanList = activityBean.getResult().getMessagelist();
                bulletinAdapter.setBullitenList(beanList);
                bulletin_LS.setAdapter(bulletinAdapter);
            }

            @Override
            public void failure() {

            }
        });
    }
}
