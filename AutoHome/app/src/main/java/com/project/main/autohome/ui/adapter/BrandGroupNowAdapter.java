package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.BrandGroupBean;
import com.project.main.autohome.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by youyo on 2016/7/22 0022.
 * 品牌页二级列表 抽屉
 */
public class BrandGroupNowAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<BrandGroupBean.ResultBean.FctlistBean> brandNowBeen;

    public BrandGroupNowAdapter(Context context, List<BrandGroupBean.ResultBean.FctlistBean> brandNowBeen) {
        this.context = context;
        this.brandNowBeen = brandNowBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return brandNowBeen != null ? brandNowBeen.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return brandNowBeen.get(groupPosition).getSerieslist().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return brandNowBeen.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return brandNowBeen.get(groupPosition).getSerieslist().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LetterHolder letterHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.brand_item_letter, parent, false);
            letterHolder = new LetterHolder(convertView);
            convertView.setTag(letterHolder);
        } else {
            letterHolder = (LetterHolder) convertView.getTag();
        }
        BrandGroupBean.ResultBean.FctlistBean brandlistBean = (BrandGroupBean.ResultBean.FctlistBean) getGroup(groupPosition);

        letterHolder.item_brand_letter.setText(brandlistBean.getName());
        return convertView;
    }

    /**
     * 行布局
     *
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ListBodyHolder bodyHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.filter_item, parent, false);
            bodyHolder = new ListBodyHolder(convertView);
            convertView.setTag(bodyHolder);
        } else {
            bodyHolder = (ListBodyHolder) convertView.getTag();
        }
        BrandGroupBean.ResultBean.FctlistBean.SerieslistBean listBean = (BrandGroupBean.ResultBean.FctlistBean.SerieslistBean) getChild(groupPosition, childPosition);
        //        VolleyInstence.getInstence(context).loadImage(listBean.getImgurl(), bodyHolder.filter_item_iv);
        ImageLoaderUtil.getInstance().load(listBean.getImgurl(), bodyHolder.filter_item_iv);
        bodyHolder.filter_item_tv.setText(listBean.getName());
        bodyHolder.filter_item_num_tv.setText(listBean.getPrice());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class LetterHolder {
        private TextView item_brand_letter;

        public LetterHolder(View view) {
            item_brand_letter = (TextView) view.findViewById(R.id.item_brand_letter);
        }
    }

    class ListBodyHolder {
        private TextView filter_item_tv, filter_item_num_tv;
        private ImageView filter_item_iv;

        public ListBodyHolder(View view) {
            filter_item_iv = (ImageView) view.findViewById(R.id.filter_item_iv);
            filter_item_tv = (TextView) view.findViewById(R.id.filter_item_tv);
            filter_item_num_tv = (TextView) view.findViewById(R.id.filter_item_num_tv);
        }
    }
}
