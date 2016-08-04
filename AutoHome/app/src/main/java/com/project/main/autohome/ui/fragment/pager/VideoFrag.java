package com.project.main.autohome.ui.fragment.pager;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.VideoBean;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.tools.CustomRefreshListView;
import com.project.main.autohome.tools.NetWorkConnectedToast;
import com.project.main.autohome.ui.activity.VideoActivity;
import com.project.main.autohome.ui.adapter.VideoAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by youyo on 2016/7/12 0012.
 * 视频页
 */
public class VideoFrag extends AbsBaseFragment implements VolleyInterfaceResult, CustomRefreshListView.OnCustomRefreshListener {
    private CustomRefreshListView video_ls;
    private VideoAdapter videoAdapter;
    private String viseoUrl = NetUrl.VIDEO_URL;
    private List<VideoBean.ResultBean.ListBean> listBeen;

    @Override
    protected int setLayout() {
        return R.layout.video_frag;
    }

    @Override
    protected void initView() {
        video_ls = byView(R.id.video_ls);
    }

    @Override
    protected void initData() {
        videoAdapter = new VideoAdapter(getContext());
        VolleyInstence.getInstence(getContext()).startRequest(viseoUrl, this);
        // 下拉刷新监听
        video_ls.setOnCustomRefreshListener(this);
        // 行布局监听，进行二级界面跳转
        video_ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), VideoActivity.class);
                String url = NetUrl.VIDEO_DETAILS + listBeen.get(position).getId() + NetUrl.VIDEO_DETAILS_BOTTOM;
                intent.putExtra("videoUrl", url);
                startActivity(intent);
            }
        });

        //检查网路
        NetWorkConnectedToast.getConnectedToast().isNet(getContext());
    }

    @Override
    public void success(String str) {
        Gson gson = new Gson();
        VideoBean videoBean = gson.fromJson(str, VideoBean.class);
        listBeen = videoBean.getResult().getList();
        videoAdapter.setVideoBeen(listBeen);
        video_ls.setAdapter(videoAdapter);
    }

    @Override
    public void failure() {

    }

    /**
     * 下拉刷新方法
     */
    @Override
    public void onRefresh() {

    }
}
