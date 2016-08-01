package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.AllOfBean;
import com.project.main.autohome.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by youyo on 2016/7/15 0015.
 * 推荐页  所有的Adapter 复用这一个
 */
public class AllIntoAdapter extends BaseAdapter {
    private List<AllOfBean.ResultBean.NewslistBean> allBean;
    private Context context;

    public AllIntoAdapter(Context context) {
        this.context = context;
    }

    public void setAllBean(List<AllOfBean.ResultBean.NewslistBean> allBean) {
        this.allBean = allBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return allBean != null ? allBean.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return allBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AllHolder allHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.all_item, parent, false);
            allHolder = new AllHolder(convertView);
            convertView.setTag(allHolder);
        } else {
            allHolder = (AllHolder) convertView.getTag();
        }
        AllOfBean.ResultBean.NewslistBean bean = (AllOfBean.ResultBean.NewslistBean) getItem(position);
        //        VolleyInstence.getInstence(context).loadImage(bean.getSmallpic(), allHolder.all_img);
        ImageLoaderUtil.getInstance().load(bean.getSmallpic(), allHolder.all_img);
        allHolder.all_title.setText(bean.getTitle());
        allHolder.all_time.setText(bean.getTime());
        allHolder.all_broadcase.setText(bean.getReplycount() + "评论");
        return convertView;
    }

    class AllHolder {
        private ImageView all_img;
        private TextView all_title, all_time, all_broadcase;

        public AllHolder(View view) {
            all_img = (ImageView) view.findViewById(R.id.all_img);
            all_title = (TextView) view.findViewById(R.id.all_title);
            all_time = (TextView) view.findViewById(R.id.all_time);
            all_broadcase = (TextView) view.findViewById(R.id.all_broadcase);

        }
    }
}
