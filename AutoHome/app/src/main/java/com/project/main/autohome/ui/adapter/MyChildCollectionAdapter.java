package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.model.db.AutoHomeBean;
import com.project.main.autohome.model.db.DBInstance;
import com.project.main.autohome.util.ImageLoaderUtil;

import java.util.List;

/**
 * Created by youyo on 2016/7/26 0026.
 */
public class MyChildCollectionAdapter extends BaseAdapter {
    private List<AutoHomeBean> mDatas;
    private Context context;
    private AutoHomeBean autoHomeBean;

    public MyChildCollectionAdapter(Context context) {
        this.context = context;
    }

    public void setmDatas(List<AutoHomeBean> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void dele(int position) {
        mDatas.remove(position);
        DBInstance.getsInstance().dele(autoHomeBean);
        notifyDataSetChanged();
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

        autoHomeBean = mDatas.get(position);
        viewHolder.art_updata_tv_tit.setText(autoHomeBean.getTitle());
        viewHolder.art_uptodata_time.setText(autoHomeBean.getContent());
        viewHolder.art_updata_lengh.setText(autoHomeBean.getClassify());
        // ListView 有多少行就执行多少次getView
        // 就创建了多少个请求队列， 请求队列里面有线程池，现在ListView 30行，30线程池
//        VolleyInstence.getInstence(context).loadImage(autoHomeBean.getPrice(),
//                viewHolder.art_headiv_uptodata);

        ImageLoaderUtil.getInstance().load(autoHomeBean.getPrice(),viewHolder.art_headiv_uptodata);

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
