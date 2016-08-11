package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.SalerecycleBean;
import com.project.main.autohome.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by youyo on 2016/7/19 0019.
 * 发现页  ---》 适配器
 */
public class SaleAdapter extends BaseAdapter {
    private List<SalerecycleBean.ResultBean.FunctionlistBean> saleBeen;
    private Context context;

    public SaleAdapter(Context context) {
        this.context = context;
    }

    public void setSaleBeen(List<SalerecycleBean.ResultBean.FunctionlistBean> saleBeen) {
        this.saleBeen = saleBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return saleBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return saleBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SaleHolder saleHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sale_grid_item, parent, false);
            saleHolder = new SaleHolder(convertView);
            convertView.setTag(saleHolder);
        } else {
            saleHolder = (SaleHolder) convertView.getTag();
        }
        SalerecycleBean.ResultBean.FunctionlistBean bean = (SalerecycleBean.ResultBean.FunctionlistBean) getItem(position);
        ImageLoaderUtil.getInstance().load(bean.getIconurl(), saleHolder.sale_item_iv);
        saleHolder.sale_item_tv.setText(bean.getTitle());
        return convertView;
    }

    class SaleHolder {
        private ImageView sale_item_iv;
        private TextView sale_item_tv;

        public SaleHolder(View view) {
            sale_item_iv = (ImageView) view.findViewById(R.id.sale_item_iv);
            sale_item_tv = (TextView) view.findViewById(R.id.sale_item_tv);
        }
    }
}
