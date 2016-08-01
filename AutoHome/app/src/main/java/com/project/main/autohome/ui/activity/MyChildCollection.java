package com.project.main.autohome.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.db.AutoHomeBean;
import com.project.main.autohome.model.db.DBInstance;
import com.project.main.autohome.ui.adapter.MyChildCollectionAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by youyo on 2016/7/26 0026.
 * 我的收藏详情页
 */
public class MyChildCollection extends AbsBaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private ListView ls;
    private MyChildCollectionAdapter collectionAdapter;
    private List<AutoHomeBean> homeBeen;

    @Override
    protected int setlayout() {
        return R.layout.mychild_collection_activity;
    }

    @Override
    protected void initViews() {
        ls = byView(R.id.mychild_collection);

    }

    @Override
    protected void initDatas() {
        homeBeen = new ArrayList<>();
        homeBeen = DBInstance.getsInstance().query(10);
        Log.d("MyChildCollection", "homeBeen.size():" + homeBeen.size());
        collectionAdapter = new MyChildCollectionAdapter(this);
        collectionAdapter.setmDatas(homeBeen);
        ls.setAdapter(collectionAdapter);
        ls.setOnItemClickListener(this);
        ls.setOnItemLongClickListener(this);

        //testcode
//
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, MyChildcollectActivity.class);
        String url = homeBeen.get(position).getUrl();
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        collectionAdapter.dele(position);
        return true;
    }
}
