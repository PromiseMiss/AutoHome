package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.OriginalBean;
import com.project.main.autohome.util.ImageLoaderUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by youyo on 2016/7/15 0015.
 * 优创页  适配器
 */
public class OriginalAdapter extends BaseAdapter {
    private List<OriginalBean.ResultBean.NewslistBean> oriBean;
    private Context context;
    private final int TYPE_0 = 0;
    private final int TYPE_1 = 1;
    private final int TYPE_COUNT = 2;

    public OriginalAdapter(Context context) {
        this.context = context;
    }

    public void setOriBean(List<OriginalBean.ResultBean.NewslistBean> oriBean) {
        this.oriBean = oriBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return oriBean != null ? oriBean.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return oriBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FistHolder fistHolder = null;
        SecondHolder secondHolder = null;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TYPE_0:
                    convertView = LayoutInflater.from(context).inflate(R.layout.origin_item_one, parent, false);
                    fistHolder = new FistHolder(convertView);
                    convertView.setTag(fistHolder);
                    break;
                case TYPE_1:
                    convertView = LayoutInflater.from(context).inflate(R.layout.origin_item_two, parent, false);
                    secondHolder = new SecondHolder(convertView);
                    convertView.setTag(secondHolder);
                    break;
                default:
                    break;
            }
        } else {
            switch (type) {
                case TYPE_0:
                    fistHolder = (FistHolder) convertView.getTag();
                    break;
                case TYPE_1:
                    secondHolder = (SecondHolder) convertView.getTag();
                    break;
                default:
                    break;
            }
        }
        OriginalBean.ResultBean.NewslistBean newslistBean = (OriginalBean.ResultBean.NewslistBean) getItem(position);
        switch (type) {
            // 小图
            case TYPE_0:
                fistHolder.origin_username_tv.setText(newslistBean.getUsername());
                ImageLoaderUtil.getInstance().load(oriBean.get(position).getUserpic(), fistHolder.origin_circle_userpic);
                ImageLoaderUtil.getInstance().load(newslistBean.getThumbnailpics().get(0), fistHolder.origin_iv_index);
                fistHolder.origin_title_tv.setText(newslistBean.getTitle());
                fistHolder.origin_time.setText(newslistBean.getPublishtime());
                break;
            // 大图
            case TYPE_1:
                ImageLoaderUtil.getInstance().load(oriBean.get(position).getUserpic(), secondHolder.origin_two_circle_iv);
                secondHolder.origin_two_tv.setText(newslistBean.getUsername());
                secondHolder.origin_tv_body.setText(newslistBean.getTitle());
                ImageLoaderUtil.getInstance().load(newslistBean.getIndexdetail().get(0), secondHolder.origin_body_iv);
                secondHolder.origin_time_tv.setText(newslistBean.getPublishtime());
                break;
            default:
                break;
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        if (oriBean.get(position).getMediatype() == 3) {
            return TYPE_1;
        } else {
            return TYPE_0;
        }
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    class FistHolder {
        private TextView origin_username_tv, origin_title_tv, origin_time;
        private CircleImageView origin_circle_userpic;
        private ImageView origin_iv_index;

        public FistHolder(View itemView) {
            origin_username_tv = (TextView) itemView.findViewById(R.id.origin_username_tv);
            origin_title_tv = (TextView) itemView.findViewById(R.id.origin_title_tv);
            origin_time = (TextView) itemView.findViewById(R.id.origin_time);
            origin_circle_userpic = (CircleImageView) itemView.findViewById(R.id.origin_circle_userpic);
            origin_iv_index = (ImageView) itemView.findViewById(R.id.origin_iv_index);
        }
    }

    class SecondHolder {
        private TextView origin_two_tv, origin_tv_body, origin_time_tv;
        private CircleImageView origin_two_circle_iv;
        private ImageView origin_body_iv;


        public SecondHolder(View itemView) {
            origin_two_tv = (TextView) itemView.findViewById(R.id.origin_two_tv);
            origin_tv_body = (TextView) itemView.findViewById(R.id.origin_tv_body);
            origin_time_tv = (TextView) itemView.findViewById(R.id.origin_time_tv);
            origin_two_circle_iv = (CircleImageView) itemView.findViewById(R.id.origin_two_circle_iv);
            origin_body_iv = (ImageView) itemView.findViewById(R.id.origin_body_iv);


        }
    }

}
