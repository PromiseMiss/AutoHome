package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.BulletinBean;
import com.project.main.autohome.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by youyo on 2016/7/15 0015.
 * 快报页 Adapter【一级界面】
 */
public class BulletinAdapter extends BaseAdapter {
    private List<BulletinBean.ResultBean.ListBean> bulletinBeen;
    private Context context;

    public BulletinAdapter(Context context) {
        this.context = context;
    }

    public void setBulletinBeen(List<BulletinBean.ResultBean.ListBean> bulletinBeen) {
        this.bulletinBeen = bulletinBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bulletinBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return bulletinBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BulletinHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.bul_bulletin_item, parent, false);
            holder = new BulletinHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (BulletinHolder) convertView.getTag();
        }
        BulletinBean.ResultBean.ListBean bulBean = (BulletinBean.ResultBean.ListBean) getItem(position);
        holder.bul_title.setText(bulBean.getTitle());
        ImageLoaderUtil.getInstance().load(bulBean.getBgimage(), holder.bul_img);
        holder.bul_num_browse.setText(bulBean.getReviewcount() + "人浏览");
        holder.bul_time.setText(bulBean.getCreatetime());
        return convertView;
    }

    class BulletinHolder {
        private TextView bul_title, bul_num_browse, bul_time;
        private ImageView bul_img;

        public BulletinHolder(View view) {
            bul_title = (TextView) view.findViewById(R.id.bul_title);
            bul_num_browse = (TextView) view.findViewById(R.id.bul_num_browse);
            bul_time = (TextView) view.findViewById(R.id.bul_time);
            bul_img = (ImageView) view.findViewById(R.id.bul_img);

        }
    }
}
