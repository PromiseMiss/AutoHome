package com.project.main.autohome.ui.fragment.pager;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.VideoBean;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.tools.CustomListView;
import com.project.main.autohome.ui.adapter.VideoAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import java.util.List;

/**
 * Created by youyo on 2016/7/12 0012.
 * 视频页
 */
public class VideoFrag extends AbsBaseFragment implements VolleyInterfaceResult, CustomListView.OnAutoHomeRefreshListener {
    private CustomListView video_ls;
    private VideoAdapter videoAdapter;
    private String viseoUrl = NetUrl.VIDEO_URL;

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
        video_ls.setOnAutoHomeRefreshListener(this);
    }

    @Override
    public void success(String str) {
        Gson gson = new Gson();
        VideoBean videoBean = gson.fromJson(str, VideoBean.class);
        List<VideoBean.ResultBean.ListBean> listBeen = videoBean.getResult().getList();
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
