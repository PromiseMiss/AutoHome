package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.FilterBean;
import com.project.main.autohome.model.net.VolleyInstence;

import java.util.List;

/**
 * Created by youyo on 2016/7/20 0020.
 */
public class FilterAdapter extends BaseAdapter {
    private List<FilterBean.ResultBean.SeriesBean> pricBean;
    private Context context;

    public FilterAdapter(Context context) {
        this.context = context;
    }

    public void setPricBean(List<FilterBean.ResultBean.SeriesBean> pricBean) {
        this.pricBean = pricBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return pricBean != null ? pricBean.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return pricBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FilterViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.filter_item, parent, false);
            viewHolder = new FilterViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FilterViewHolder) convertView.getTag();
        }
        FilterBean.ResultBean.SeriesBean seriesBean = (FilterBean.ResultBean.SeriesBean) getItem(position);
        VolleyInstence.getInstence(context).loadImage(seriesBean.getThumburl(), viewHolder.filter_item_iv);
        viewHolder.filter_item_tv.setText(seriesBean.getSeriesname());
        viewHolder.filter_item_num_tv.setText(seriesBean.getPricerange());
        return convertView;
    }

    class FilterViewHolder {
        private ImageView filter_item_iv;
        private TextView filter_item_tv, filter_item_num_tv;

        public FilterViewHolder(View view) {
            filter_item_iv = (ImageView) view.findViewById(R.id.filter_item_iv);
            filter_item_tv = (TextView) view.findViewById(R.id.filter_item_tv);
            filter_item_num_tv = (TextView) view.findViewById(R.id.filter_item_num_tv);
        }

    }
}
