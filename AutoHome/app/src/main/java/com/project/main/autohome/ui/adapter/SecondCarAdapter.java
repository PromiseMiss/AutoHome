package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.main.autohome.R;

import java.util.List;

/**
 * Created by youyo on 2016/7/18 0018.
 * 找二手车 页
 */
public class SecondCarAdapter extends BaseAdapter {
    private List<String> body;
    private Context context;

    @Override
    public int getCount() {
        return body != null ? body.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return body.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SecondCarHolder carHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.find_second_item, parent, false);
            carHolder = new SecondCarHolder(convertView);
            convertView.setTag(carHolder);
        } else {
            carHolder = (SecondCarHolder) getItem(position);
        }
        carHolder.find_second_tv_item.setText(body.get(position));
        return convertView;
    }

    class SecondCarHolder {
        private TextView find_second_tv_item;

        public SecondCarHolder(View view) {
            find_second_tv_item = (TextView) view.findViewById(R.id.find_second_tv_item);

        }
    }
}
