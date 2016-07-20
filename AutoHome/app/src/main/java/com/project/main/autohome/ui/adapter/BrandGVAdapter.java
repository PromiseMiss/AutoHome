package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.BrandBean;
import com.project.main.autohome.model.net.VolleyInstence;

import java.util.List;

/**
 * Created by youyo on 2016/7/20 0020.
 * 品牌页 GridView
 */
public class BrandGVAdapter extends BaseAdapter {
    private List<BrandBean.ResultBean.ListBean> brandBeen;
    private Context context;

    public BrandGVAdapter(Context context) {
        this.context = context;
    }

    public void setBrandBeen(List<BrandBean.ResultBean.ListBean> brandBeen) {
        this.brandBeen = brandBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return brandBeen != null ? brandBeen.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return brandBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BrandHolder brandHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.brand_item_gridview, parent, false);
            brandHolder = new BrandHolder(convertView);
            convertView.setTag(brandHolder);
        } else {
            brandHolder = (BrandHolder) convertView.getTag();
        }
        BrandBean.ResultBean.ListBean listBean = (BrandBean.ResultBean.ListBean) getItem(position);
        VolleyInstence.getInstence(context).loadImage(listBean.getImg(), brandHolder.item_brand_imageView);
        brandHolder.item_brand_plate.setText(listBean.getName());
        return convertView;
    }

    class BrandHolder {
        private ImageView item_brand_imageView;
        private TextView item_brand_plate;

        public BrandHolder(View view) {
            item_brand_imageView = (ImageView) view.findViewById(R.id.item_brand_imageView);
            item_brand_plate = (TextView) view.findViewById(R.id.item_brand_plate);
        }
    }
}
