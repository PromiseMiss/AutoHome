package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.UpCarouselBean;
import com.project.main.autohome.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by youyo on 2016/7/12 0012.
 * 最新页 适配器
 */
public class UptoDateAdapter extends BaseAdapter {
    private List<UpCarouselBean.ResultBean.NewslistBean> mDatas;
    private Context context;

    public void setmDatas(List<UpCarouselBean.ResultBean.NewslistBean> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public UptoDateAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return mDatas.size();
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
        UpCarouselBean.ResultBean.NewslistBean carouselBean =
                (UpCarouselBean.ResultBean.NewslistBean) getItem(position);

        viewHolder.art_updata_tv_tit.setText(carouselBean.getTitle());
        viewHolder.art_uptodata_time.setText(carouselBean.getTime());
        viewHolder.art_updata_lengh.setText(carouselBean.getReplycount() + "评论");
        // ListView 有多少行就执行多少次getView
        // 就创建了多少个请求队列， 请求队列里面有线程池，现在ListView 30行，30线程池
//        VolleyInstence.getInstence(context).loadImage(mDatas.get(position).getSmallpic(),
        //                viewHolder.art_headiv_uptodata);
        ImageLoaderUtil.getInstance().load(mDatas.get(position).getSmallpic(),
                viewHolder.art_headiv_uptodata);
        return convertView;
    }

    class ViewHolder {
        private ImageView art_headiv_uptodata;
        private TextView art_updata_tv_tit, art_uptodata_time, art_updata_lengh;

        public ViewHolder(View view) {
            art_updata_tv_tit = (TextView) view.findViewById(R.id.art_updata_tv_tit);
            art_uptodata_time = (TextView) view.findViewById(R.id.art_uptodata_time);
            art_updata_lengh = (TextView) view.findViewById(R.id.art_updata_lengh);
            art_headiv_uptodata = (ImageView) view.findViewById(R.id.art_headiv_uptodata);
        }
    }
}
