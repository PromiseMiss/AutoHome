package com.project.main.autohome.ui.fragment.pager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.OriginalBean;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.ui.adapter.UptoDateAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by youyo on 2016/7/12 0012.
 * 优创页
 */
public class OriginalFrag extends AbsBaseFragment implements VolleyInterfaceResult {
    private TextView head_all_pop, orig_item_all_pop, orig_item_my_pop;
    private PopupWindow orig_pop;
    private String[] imgurl;
    private Banner banner;
    private ListView orig_ls;
    private UptoDateAdapter dateAdapter;

    private String url = "http://news.app.autohome.com.cn/news_v6.1.0/newspf/NPNewsList.ashx?a=2&pm=2&v=6.1.0&au=&type=3&lastid=&lastuid=0&size=30";

    @Override
    protected int setLayout() {
        return R.layout.original_frag;
    }

    @Override
    protected void initView() {
        orig_ls = byView(R.id.orig_listview);
        orig_item_all_pop = byView(R.id.orig_item_all_pop);
        orig_item_my_pop = byView(R.id.orig_item_my_pop);
    }

    @Override
    protected void initData() {
        dateAdapter = new UptoDateAdapter(getContext());
        orig_ls.setAdapter(dateAdapter);
        VolleyInstence.getInstence(getContext()).startRequest(url, new VolleyInterfaceResult() {
            @Override
            public void success(String str) {
                Gson gson = new Gson();
                OriginalBean originalBean = gson.fromJson(str, OriginalBean.class);
                List<OriginalBean.ResultBean.FocusimgsBean> focusimgs = originalBean.getResult().getFocusimgs();
                imgurl = new String[focusimgs.size()];
                for (int i = 0; i < focusimgs.size(); i++) {
                    imgurl[i] = String.valueOf(originalBean.getResult().getNewslist().get(i).getIndexdetail());
                    Log.d("OriginalFrag", imgurl[i]);
                }
                initBanner();
            }

            @Override
            public void failure() {

            }
        });

        View view = LayoutInflater.from(getContext()).inflate(R.layout.art_item_up_ls, null);
        orig_ls.addHeaderView(view);
        banner = (Banner) view.findViewById(R.id.uptodata_banner);

    }

    @Override
    public void success(String str) {

    }

    @Override
    public void failure() {

    }

    private void initBanner() {
        //        设置指示器
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        //        注入头布局
        //        设置时间
        banner.setDelayTime(3000);
        //        轮播图片
        banner.setImages(imgurl);
    }
}
