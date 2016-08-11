package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.VideoBean;
import com.project.main.autohome.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by youyo on 2016/7/15 0015.
 * 视频页 适配器
 */
public class VideoAdapter extends BaseAdapter {
    private List<VideoBean.ResultBean.ListBean> videoBeen;
    private Context context;

    public VideoAdapter(Context context) {
        this.context = context;
    }

    public void setVideoBeen(List<VideoBean.ResultBean.ListBean> videoBeen) {
        this.videoBeen = videoBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return videoBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return videoBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VideoHolder videoHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
            videoHolder = new VideoHolder(convertView);
            convertView.setTag(videoHolder);
        } else {
            videoHolder = (VideoHolder) convertView.getTag();
        }
        VideoBean.ResultBean.ListBean videoLsBean = (VideoBean.ResultBean.ListBean) getItem(position);
        ImageLoaderUtil.getInstance().load(videoLsBean.getSmallimg(), videoHolder.video_img);
        videoHolder.video_title.setText(videoLsBean.getTitle());
        videoHolder.video_time.setText(videoLsBean.getTime());
        videoHolder.video_broadcase.setText(videoLsBean.getReplycount() + "播放");
        return convertView;
    }

    class VideoHolder {
        private ImageView video_img;
        private TextView video_title, video_time, video_broadcase;

        public VideoHolder(View view) {
            video_img = (ImageView) view.findViewById(R.id.video_img);
            video_title = (TextView) view.findViewById(R.id.video_title);
            video_time = (TextView) view.findViewById(R.id.video_time);
            video_broadcase = (TextView) view.findViewById(R.id.video_broadcase);

        }
    }
}
