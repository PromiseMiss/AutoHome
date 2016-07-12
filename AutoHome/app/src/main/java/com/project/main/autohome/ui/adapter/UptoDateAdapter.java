package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.main.autohome.R;

import java.util.ArrayList;

/**
 * Created by youyo on 2016/7/12 0012.
 * 最新页 BaseAdapter
 */
public class UptoDateAdapter extends BaseAdapter {
    private ArrayList<String> mDatas;
    private Context context;

    public void setmDatas(ArrayList<String> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public UptoDateAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.art_uptadate_ls, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.uptoDate_tv.setText(mDatas.get(position));
        return convertView;
    }

    class ViewHolder {
        private TextView uptoDate_tv;

        public ViewHolder(View view) {
            uptoDate_tv = (TextView) view.findViewById(R.id.uptoDate_tv);
        }
    }
}
