package com.project.main.autohome.ui.fragment.fragmentpager;

import android.content.Intent;
import android.view.View;
import android.widget.GridView;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.SaleAutoBean;
import com.project.main.autohome.model.bean.SaleBannerDetailsBean;
import com.project.main.autohome.model.bean.SalerecycleBean;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.ui.activity.SaleBannerActivity;
import com.project.main.autohome.ui.adapter.SaleAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by youyo on 2016/7/11 0011.
 * 发现页
 */
public class SaleFragment extends AbsBaseFragment implements VolleyInterfaceResult, Banner.OnBannerClickListener {
    private Banner sale_banner;
    private String[] imgsurl;
    private GridView sale_gv;
    private String urls = NetUrl.FIND_DETATIL;

    private SaleAdapter saleAdapter;

    private String imgUrl = NetUrl.FIND_FIRST_RECYCLER;
    private String iconimgurl = NetUrl.FIND_FIRST;
    private List<SalerecycleBean.ResultBean.FunctionlistBean> funcList;
    private List<SaleBannerDetailsBean.ResultBean.ListBean> listBeen;
    private String url;

    @Override
    protected int setLayout() {
        return R.layout.sale_fragment;
    }

    @Override
    protected void initView() {
        sale_banner = byView(R.id.sale_banner);
        sale_gv = byView(R.id.sale_gv);

    }

    @Override
    protected void initData() {
        saleAdapter = new SaleAdapter(getContext());
        // GridView
        VolleyInstence.getInstence(getContext()).startRequest(iconimgurl, this);
        // 轮播图
        VolleyInstence.getInstence(getContext()).startRequest(imgUrl, new VolleyInterfaceResult() {
            @Override
            public void success(String str) {
                Gson gson = new Gson();
                SaleAutoBean saleAutoBean = gson.fromJson(str, SaleAutoBean.class);
                List<SaleAutoBean.ResultBean.ListBean> listBeen = saleAutoBean.getResult().getList();
                imgsurl = new String[listBeen.size()];
                for (int i = 0; i < listBeen.size(); i++) {
                    imgsurl[i] = listBeen.get(i).getImgurl();
                }
                initBanner();
            }

            @Override
            public void failure() {

            }
        });

        sale_banner.setOnBannerClickListener(this);
        initBanner();
    }

    private void initBanner() {
        sale_banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        sale_banner.setDelayTime(2000);
        sale_banner.setImages(imgsurl);
    }

    /**
     * GridView 部分
     *
     * @param str
     */
    @Override
    public void success(String str) {
        Gson gson = new Gson();
        SalerecycleBean salerecycleBeanList = gson.fromJson(str, SalerecycleBean.class);
        funcList = salerecycleBeanList.getResult().getFunctionlist();
        saleAdapter.setSaleBeen(funcList);
        sale_gv.setAdapter(saleAdapter);
    }

    @Override
    public void failure() {

    }


    @Override
    public void OnBannerClick(View view, final int position) {
        Intent intent = new Intent(getContext(), SaleBannerActivity.class);
        //        final int num = funcList.get(position).getId();
        for (int i = 0; i < funcList.get(position).getId(); i++) {

            VolleyInstence.getInstence(getContext()).startRequest(urls, new VolleyInterfaceResult() {
                @Override
                public void success(String str) {
                    Gson gson = new Gson();
                    SaleBannerDetailsBean detailsBean = gson.fromJson(str, SaleBannerDetailsBean.class);
                    listBeen = detailsBean.getResult().getList();
                    url = listBeen.get(position).getUrl();
                }

                @Override
                public void failure() {

                }
            });
        }
        intent.putExtra("url", url);
        getContext().startActivity(intent);


    }
}
