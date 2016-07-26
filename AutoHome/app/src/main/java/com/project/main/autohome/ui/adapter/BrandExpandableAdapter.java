package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.BrandIconBean;
import com.project.main.autohome.model.net.VolleyInstence;

import java.util.List;

/**
 * Created by youyo on 2016/7/20 0020.
 * 品牌页 的二级列表适配器
 */
public class BrandExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<BrandIconBean.ResultBean.BrandlistBean> brandlistBeen;

    public BrandExpandableAdapter(Context context, List<BrandIconBean.ResultBean.BrandlistBean> brandlistBeen) {
        this.context = context;
        this.brandlistBeen = brandlistBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return brandlistBeen.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return brandlistBeen.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return brandlistBeen.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return brandlistBeen.get(groupPosition).getList().get(childPosition);
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
        BrandIconBean.ResultBean.BrandlistBean brandlistBean = (BrandIconBean.ResultBean.BrandlistBean) getGroup(groupPosition);
        letterHolder.item_brand_letter.setText(brandlistBean.getLetter());
        /*如果不写的话会报空指针，，而且不会报那空，但是经过baidu，说是一般这会没有返回值--就是View为空*/
        return convertView;
    }

    /**
     * 带标志的二级列表
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
            convertView = LayoutInflater.from(context).inflate(R.layout.brand_item_listbody, parent, false);
            bodyHolder = new ListBodyHolder(convertView);
            convertView.setTag(bodyHolder);
        } else {
            bodyHolder = (ListBodyHolder) convertView.getTag();
        }
        BrandIconBean.ResultBean.BrandlistBean.ListBean listBean = (BrandIconBean.ResultBean.BrandlistBean.ListBean) getChild(groupPosition, childPosition);
        VolleyInstence.getInstence(context).loadImage(listBean.getImgurl(), bodyHolder.item_brand_carsBrand);
        bodyHolder.item_brand_tv_cars.setText(listBean.getName());
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
        private TextView item_brand_tv_cars;
        private ImageView item_brand_carsBrand;

        public ListBodyHolder(View view) {
            item_brand_tv_cars = (TextView) view.findViewById(R.id.item_brand_tv_cars);
            item_brand_carsBrand = (ImageView) view.findViewById(R.id.item_brand_carsBrand);
        }
    }

    public int getSectionForPosition(int position) {
        return brandlistBeen.get(position).getLetter().charAt(0);
    }
}
