package com.project.main.autohome.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.main.autohome.R;
import com.project.main.autohome.tools.FeaturedListener;

import java.util.List;

/**
 * Created by youyo on 2016/7/15 0015.
 * RecyclerView 横滑  适配器
 */
public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.FeatuHolder> {
    private List<String> list;
    private Context context;
    private FeaturedListener featuredListener;

    public void setFeaturedListener(FeaturedListener featuredListener) {
        this.featuredListener = featuredListener;
    }

    public FeatureAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public FeatuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FeatuHolder featuHolder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.fo_featured_item, parent, false);
        featuHolder = new FeatuHolder(view);
        return featuHolder;
    }

    @Override
    public void onBindViewHolder(final FeatuHolder holder, int position) {
        if (featuredListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getAdapterPosition();
                    featuredListener.FeatListent(pos);
                }
            });
        }
        holder.fo_fea_item_recyc.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size() > 0 && list != null ? list.size() : 0;
    }

    class FeatuHolder extends RecyclerView.ViewHolder {
        private TextView fo_fea_item_recyc;

        public FeatuHolder(View itemView) {
            super(itemView);
            fo_fea_item_recyc = (TextView) itemView.findViewById(R.id.fo_fea_item_recyc);
        }
    }
}
