package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.FeatureAllBean;
import com.project.main.autohome.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by youyo on 2016/7/16 0016.
 * ListView  的适配器
 */
public class FeatureLSAdapter extends BaseAdapter {
    private List<FeatureAllBean.ResultBean.ListBean> lsList;
    private Context context;

    public FeatureLSAdapter(Context context) {
        this.context = context;
    }

    public void setLsList(List<FeatureAllBean.ResultBean.ListBean> lsList) {
        this.lsList = lsList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lsList.size();
    }

    @Override
    public Object getItem(int position) {
        return lsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position-1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FeatuLSHolder lsHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fo_featured_ls_item, parent, false);
            lsHolder = new FeatuLSHolder(convertView);
            convertView.setTag(lsHolder);
        } else {
            lsHolder = (FeatuLSHolder) convertView.getTag();
        }
        FeatureAllBean.ResultBean.ListBean listBean = (FeatureAllBean.ResultBean.ListBean) getItem(position);

        //        VolleyInstence.getInstence(context).loadImage(listBean.getSmallpic(), lsHolder.fo_fea_item_iv);
        ImageLoaderUtil.getInstance().load(listBean.getSmallpic(), lsHolder.fo_fea_item_iv);
        lsHolder.fo_fea_item_title.setText(listBean.getTitle());
        lsHolder.fo_fea_item_smitit.setText(listBean.getBbsname());
        lsHolder.fo_fea_item_num.setText(listBean.getReplycounts() + "回");


        return convertView;
    }

    class FeatuLSHolder {
        private TextView fo_fea_item_title, fo_fea_item_smitit, fo_fea_item_num;
        private ImageView fo_fea_item_iv;

        public FeatuLSHolder(View itemView) {
            fo_fea_item_iv = (ImageView) itemView.findViewById(R.id.fo_fea_item_iv);
            fo_fea_item_title = (TextView) itemView.findViewById(R.id.fo_fea_item_title);
            fo_fea_item_smitit = (TextView) itemView.findViewById(R.id.fo_fea_item_smitit);
            fo_fea_item_num = (TextView) itemView.findViewById(R.id.fo_fea_item_num);
        }
    }
}
