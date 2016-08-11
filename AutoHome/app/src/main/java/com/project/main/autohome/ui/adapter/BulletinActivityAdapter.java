package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.BulletinActivityBean;
import com.project.main.autohome.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by youyo on 2016/8/5 0005.
 * <p/>
 * 快报页二级界面适配器
 */
public class BulletinActivityAdapter extends BaseAdapter {
    private Context context;
    private List<BulletinActivityBean.ResultBean.MessagelistBean> bullitenList;

    public BulletinActivityAdapter(Context context) {
        this.context = context;
    }

    public void setBullitenList(List<BulletinActivityBean.ResultBean.MessagelistBean> bullitenList) {
        this.bullitenList = bullitenList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bullitenList != null ? bullitenList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return bullitenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BullitenViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.bulletin_activity_item, parent, false);
            viewHolder = new BullitenViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (BullitenViewHolder) convertView.getTag();
        }
        BulletinActivityBean.ResultBean.MessagelistBean newsdataBean = (BulletinActivityBean.ResultBean.MessagelistBean) getItem(position);
        ImageLoaderUtil.getInstance().load(newsdataBean.getHeadimg(), viewHolder.bulletin_iv_icon);
        viewHolder.bulletin_tv_name.setText(newsdataBean.getAuthorname());
        viewHolder.bulletin_tv_time.setText(newsdataBean.getPublishtime());
        viewHolder.bulletin_tv_body.setText(newsdataBean.getContent());
        return convertView;
    }

    class BullitenViewHolder {
        private ImageView bulletin_iv_icon;
        private TextView bulletin_tv_name, bulletin_tv_time, bulletin_tv_body;

        public BullitenViewHolder(View view) {
            bulletin_iv_icon = (ImageView) view.findViewById(R.id.bulletin_iv_icon);
            bulletin_tv_name = (TextView) view.findViewById(R.id.bulletin_tv_name);
            bulletin_tv_time = (TextView) view.findViewById(R.id.bulletin_tv_time);
            bulletin_tv_body = (TextView) view.findViewById(R.id.bulletin_tv_body);
        }
    }
}
