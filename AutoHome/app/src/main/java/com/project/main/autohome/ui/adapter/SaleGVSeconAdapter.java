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
public class SaleGVSeconAdapter extends BaseAdapter {
    private Context context;
    private List<SaleGVFirstBean.ResultBean.ModulelistBean> gvsecondBean;

    public SaleGVSeconAdapter(Context context) {
        this.context = context;
    }

    public void setGvsecondBean(List<SaleGVFirstBean.ResultBean.ModulelistBean> gvsecondBean) {
        this.gvsecondBean = gvsecondBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return gvsecondBean.size();
    }

    @Override
    public Object getItem(int position) {
        return gvsecondBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GVSeconHolder seconHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sale_gvfirst_item, parent, false);
            seconHolder = new GVSeconHolder(convertView);
            convertView.setTag(seconHolder);
        } else {
            seconHolder = (GVSeconHolder) convertView.getTag();
        }

        VolleyInstence.getInstence(context).loadImage(gvsecondBean.get(1).getList().get(position).getLogo(), seconHolder.sale_gvfirst_iv);
        seconHolder.sale_gvfirst_tv_tit.setText(gvsecondBean.get(1).getList().get(position).getTitle());
        seconHolder.sale_gvfirst_tv_smitit.setText(gvsecondBean.get(1).getList().get(position).getShorttitle());
        seconHolder.sale_gvfirst_tv_num.setText(gvsecondBean.get(1).getList().get(position).getPrice());
        seconHolder.sale_gv_first_tv_smalnum.setText(gvsecondBean.get(1).getList().get(position).getFctprice());

        return convertView;
    }

    class GVSeconHolder {
        private TextView sale_gvfirst_tv_tit, sale_gvfirst_tv_smitit, sale_gvfirst_tv_num,
                sale_gv_first_tv_smalnum;
        private ImageView sale_gvfirst_iv;

        public GVSeconHolder(View view) {
            sale_gvfirst_iv = (ImageView) view.findViewById(R.id.sale_gvfirst_iv);
            sale_gvfirst_tv_tit = (TextView) view.findViewById(R.id.sale_gvfirst_tv_tit);
            sale_gvfirst_tv_smitit = (TextView) view.findViewById(R.id.sale_gvfirst_tv_smitit);
            sale_gvfirst_tv_num = (TextView) view.findViewById(R.id.sale_gvfirst_tv_num);
            sale_gv_first_tv_smalnum = (TextView) view.findViewById(R.id.sale_gv_first_tv_smalnum);
        }

    }

}
