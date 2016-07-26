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
 * 发现 GridView 轮播图下面的下面
 */
public class SaleGVFirstAdapter extends BaseAdapter {
    private Context context;
    private List<SaleGVFirstBean.ResultBean.ModulelistBean> gvfristBean;

    public SaleGVFirstAdapter(Context context) {
        this.context = context;
    }

    public void setGvfristBean(List<SaleGVFirstBean.ResultBean.ModulelistBean> gvfristBean) {
        this.gvfristBean = gvfristBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return gvfristBean.size();
    }

    @Override
    public Object getItem(int position) {
        return gvfristBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GvfristHolder gvfristHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sale_gvfirst_item, parent, false);
            gvfristHolder = new GvfristHolder(convertView);
            convertView.setTag(gvfristHolder);
        } else {
            gvfristHolder = (GvfristHolder) convertView.getTag();
        }

        VolleyInstence.getInstence(context).loadImage(gvfristBean.get(0).getList().get(position).getLogo(), gvfristHolder.sale_gvfirst_iv);
        gvfristHolder.sale_gvfirst_tv_tit.setText(gvfristBean.get(0).getList().get(position).getTitle());
        gvfristHolder.sale_gvfirst_tv_smitit.setText(gvfristBean.get(0).getList().get(position).getShorttitle());
        gvfristHolder.sale_gvfirst_tv_num.setText(gvfristBean.get(0).getList().get(position).getPrice());
        gvfristHolder.sale_gv_first_tv_smalnum.setText(gvfristBean.get(0).getList().get(position).getFctprice());


        return convertView;
    }

    class GvfristHolder {
        private TextView sale_gvfirst_tv_tit, sale_gvfirst_tv_smitit, sale_gvfirst_tv_num,
                sale_gv_first_tv_smalnum;
        private ImageView sale_gvfirst_iv;

        public GvfristHolder(View view) {
            sale_gvfirst_iv = (ImageView) view.findViewById(R.id.sale_gvfirst_iv);
            sale_gvfirst_tv_tit = (TextView) view.findViewById(R.id.sale_gvfirst_tv_tit);
            sale_gvfirst_tv_smitit = (TextView) view.findViewById(R.id.sale_gvfirst_tv_smitit);
            sale_gvfirst_tv_num = (TextView) view.findViewById(R.id.sale_gvfirst_tv_num);
            sale_gv_first_tv_smalnum = (TextView) view.findViewById(R.id.sale_gv_first_tv_smalnum);
        }
    }
}
