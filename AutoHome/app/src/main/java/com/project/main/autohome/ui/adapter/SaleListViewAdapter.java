package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.SaleGVFirstBean;
import com.project.main.autohome.model.net.VolleyInstence;

import java.util.List;

/**
 * Created by youyo on 2016/7/23 0023.
 */
public class SaleListViewAdapter extends BaseAdapter {
    private Context context;
    private List<SaleGVFirstBean.ResultBean.GoodslistBean.ListBean> lsBean;

    public SaleListViewAdapter(Context context) {
        this.context = context;
    }

    public void setLsBean(List<SaleGVFirstBean.ResultBean.GoodslistBean.ListBean> lsBean) {
        this.lsBean = lsBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lsBean.size();
    }

    @Override
    public Object getItem(int position) {
        return lsBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LsHolder lsHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sale_ls_item_body, parent, false);
            lsHolder = new LsHolder(convertView);
            convertView.setTag(lsHolder);
        } else {
            lsHolder = (LsHolder) convertView.getTag();
        }
        VolleyInstence.getInstence(context).loadImage(lsBean.get(position).getLogo(), lsHolder.sale_ls_iv);
        lsHolder.sale_ls_tit_tv.setText(lsBean.get(position).getTitle());
        lsHolder.sale_ls_smaltit_tv.setText(lsBean.get(position).getAdinfo());
        lsHolder.sale_ls_num_tv.setText(lsBean.get(position).getPrice());
        lsHolder.sale_ls_smalnum.setText(lsBean.get(position).getFctprice());

        return convertView;
    }

    class LsHolder {
        private ImageView sale_ls_iv;
        private TextView sale_ls_tit_tv, sale_ls_smaltit_tv, sale_ls_num_tv, sale_ls_smalnum;

        public LsHolder(View view) {
            sale_ls_iv = (ImageView) view.findViewById(R.id.sale_ls_iv);
            sale_ls_tit_tv = (TextView) view.findViewById(R.id.sale_ls_tit_tv);
            sale_ls_smaltit_tv = (TextView) view.findViewById(R.id.sale_ls_smaltit_tv);
            sale_ls_num_tv = (TextView) view.findViewById(R.id.sale_ls_num_tv);
            sale_ls_smalnum = (TextView) view.findViewById(R.id.sale_ls_smalnum);

        }
    }

}
