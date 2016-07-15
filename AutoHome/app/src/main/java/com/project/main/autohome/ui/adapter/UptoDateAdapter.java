package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.UpCarouselBean;
import com.project.main.autohome.tools.DoubleCache;

import java.util.List;

/**
 * Created by youyo on 2016/7/12 0012.
 * 最新页 BaseAdapter
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
        UpCarouselBean.ResultBean.NewslistBean carouselBean =
                (UpCarouselBean.ResultBean.NewslistBean) getItem(position);

        viewHolder.art_updata_tv_tit.setText(carouselBean.getTitle());
        viewHolder.art_uptodata_time.setText(carouselBean.getTime());
        viewHolder.art_updata_lengh.setText(carouselBean.getReplycount() + " ");


//            Picasso.with(context).load(mDatas.get(position).getSmallpic()).
//                    into(viewHolder.art_headiv_uptodata);
            RequestQueue queue = Volley.newRequestQueue(context);
            ImageLoader imageLoader = new ImageLoader(queue,new DoubleCache());
            ImageLoader.ImageListener imageListener = ImageLoader.
                    getImageListener(viewHolder.art_headiv_uptodata,
                            R.mipmap.ic_launcher,R.mipmap.ic_launcher);
            imageLoader.get(mDatas.get(position).getSmallpic(),imageListener);

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
