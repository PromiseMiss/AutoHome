package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.HotDataBean;

import java.util.List;

/**
 * Created by youyo on 2016/7/13 0013.
 * 热帖页的适配器
 */
public class HotpastsAdapter extends BaseAdapter {
    private List<HotDataBean.ResultBean.ListBean> hotpastaBeen;
    private Context context;

    public void setHotpastaBeen(List<HotDataBean.ResultBean.ListBean> hotpastaBeen) {
        this.hotpastaBeen = hotpastaBeen;
        notifyDataSetChanged();
    }

    public HotpastsAdapter(List<HotDataBean.ResultBean.ListBean> hotpastaBeen, Context context) {
        this.hotpastaBeen = hotpastaBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return hotpastaBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return hotpastaBeen != null ? hotpastaBeen.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HotHolder hotHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fo_hotpas_item, parent, false);
            hotHolder = new HotHolder(convertView);
            convertView.setTag(hotHolder);
        } else {
            hotHolder = (HotHolder) convertView.getTag();
        }
        //        用的是解析实体类
        HotDataBean.ResultBean.ListBean hotpastaBean = hotpastaBeen.get(position);
        //调用的都是解析实体类里面的方法
        hotHolder.hot_title.setText(hotpastaBean.getTitle());
        hotHolder.hot_simtit.setText(hotpastaBean.getBbsname());
        hotHolder.hot_time.setText(hotpastaBean.getPostdate());
        hotHolder.hot_reply.setText(hotpastaBean.getReplycounts() + " ");
        return convertView;
    }


    class HotHolder {
        private TextView hot_title, hot_simtit, hot_time, hot_reply;

        public HotHolder(View view) {
            hot_title = (TextView) view.findViewById(R.id.fo_hotpas_title);
            hot_simtit = (TextView) view.findViewById(R.id.fo_hotpas_simtitle);
            hot_time = (TextView) view.findViewById(R.id.fo_hotpas_time);
            hot_reply = (TextView) view.findViewById(R.id.fo_hotpas_reply);
        }
    }
}
