package com.project.main.autohome.ui.fragment.pager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.UpCarouselBean;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.ui.adapter.UptoDateAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by youyo on 2016/7/12 0012.
 * 最新页
 */
public class UptoDateFrag extends AbsBaseFragment implements VolleyInterfaceResult {
    private ListView uptoDate_ls;
    private UptoDateAdapter uptoDateAdapter;
    private Banner banner;
    private String[] imgUrls;

    private String url = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt0-p1-s30-l0.json";
    private String strUrl = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt0-p1-s30-l0.json";

    @Override
    protected int setLayout() {
        return R.layout.uptodate_frag;
    }

    @Override
    protected void initView() {
        uptoDate_ls = byView(R.id.uoToDate_ls);
    }

    @Override
    protected void initData() {
        //        解析ListView部分
        VolleyInstence.getInstence(getContext()).startRequest(strUrl, this);
        //轮播图
        VolleyInstence.getInstence(getContext()).startRequest(url, new VolleyInterfaceResult() {
            @Override
            public void success(String str) {
                Gson gson = new Gson();
                UpCarouselBean carouselBean = gson.fromJson(str, UpCarouselBean.class);
                List<UpCarouselBean.ResultBean.FocusimgBean> focusimgBeen = carouselBean.getResult().getFocusimg();
                imgUrls = new String[focusimgBeen.size()];
                for (int i = 0; i < focusimgBeen.size(); i++) {
                    imgUrls[i] = carouselBean.getResult().getFocusimg().get(i).getImgurl();
                }
                initshowBanner();
            }

            @Override
            public void failure() {
            }
        });
        View view = LayoutInflater.from(getContext()).inflate(R.layout.art_item_up_ls, null);
        banner = (Banner) view.findViewById(R.id.uptodata_banner);
        uptoDate_ls.addHeaderView(view);
    }

    private void initshowBanner() {
        //        设置指示器
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        //        注入头布局
        //        设置时间
        banner.setDelayTime(3000);
        //        轮播图片
        banner.setImages(imgUrls);
    }

    @Override
    public void success(String str) {
        //        解析ListView部分
        Gson gson = new Gson();
        UpCarouselBean carouselBeans = gson.fromJson(str, UpCarouselBean.class);
        List<UpCarouselBean.ResultBean.NewslistBean> beanList = carouselBeans.getResult().getNewslist();
        uptoDateAdapter = new UptoDateAdapter(getContext());
        uptoDateAdapter.setmDatas(beanList);
        uptoDate_ls.setAdapter(uptoDateAdapter);
    }

    @Override
    public void failure() {

    }
}
