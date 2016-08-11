package com.project.main.autohome.ui.fragment.fragmentpager;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.SaleAutoBean;
import com.project.main.autohome.model.bean.SaleGVFirstBean;
import com.project.main.autohome.model.bean.SalerecycleBean;
import com.project.main.autohome.model.net.NetUrl;
import com.project.main.autohome.model.net.VolleyInstence;
import com.project.main.autohome.model.net.VolleyInterfaceResult;
import com.project.main.autohome.ui.activity.SaleLsActivity;
import com.project.main.autohome.ui.adapter.SaleAdapter;
import com.project.main.autohome.ui.adapter.SaleGVFirstAdapter;
import com.project.main.autohome.ui.adapter.SaleGVSeconAdapter;
import com.project.main.autohome.ui.adapter.SaleListViewAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by youyo on 2016/7/11 0011.
 * 发现页
 */
public class SaleFragment extends AbsBaseFragment implements VolleyInterfaceResult, AdapterView.OnItemClickListener {
    private Banner sale_banner;
    private String[] imgsurl;
    private GridView sale_gv, sale_gv_first, sale_gv_secon;
    private ListView sale_ls;
    private SaleAdapter saleAdapter;
    private SaleGVFirstAdapter gvFirstAdapter;
    private SaleGVSeconAdapter gvSeconAdapter;
    private SaleListViewAdapter listViewAdapter;
    private List<SalerecycleBean.ResultBean.FunctionlistBean> funcList;
    private List<SaleGVFirstBean.ResultBean.GoodslistBean.ListBean> goodslistBeen;

    @Override
    protected int setLayout() {
        return R.layout.sale_listview_item;
    }

    @Override
    protected void initView() {
        sale_ls = byView(R.id.sale_listView_item);
    }

    @Override
    protected void initData() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.sale_fragment, null);
        sale_banner = (Banner) view.findViewById(R.id.sale_banner);
        sale_gv = (GridView) view.findViewById(R.id.sale_gv);
        sale_gv_first = (GridView) view.findViewById(R.id.sale_gv_first);
        sale_gv_secon = (GridView) view.findViewById(R.id.sale_gv_secon);
        sale_ls.addHeaderView(view);
        sale_ls.setOnItemClickListener(this);
        listViewAdapter = new SaleListViewAdapter(getContext());// ls的适配器
        saleAdapter = new SaleAdapter(getContext());
        // GridView
        VolleyInstence.getInstence(getContext()).startRequest(NetUrl.FIND_FIRST, this);
        // 轮播图
        VolleyInstence.getInstence(getContext()).startRequest(NetUrl.FIND_FIRST_RECYCLER, new VolleyInterfaceResult() {
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
        // 下面的GV
        VolleyInstence.getInstence(getContext()).startRequest(NetUrl.SALE_GVFIRST_URL, new VolleyInterfaceResult() {
            @Override
            public void success(String str) {
                Gson gson = new Gson();
                SaleGVFirstBean firstBean = gson.fromJson(str, SaleGVFirstBean.class);
                List<SaleGVFirstBean.ResultBean.ModulelistBean> modulelistBeen = firstBean.getResult().getModulelist();
                // 猜你喜欢
                gvFirstAdapter = new SaleGVFirstAdapter(getContext());
                gvFirstAdapter.setGvfristBean(modulelistBeen);
                sale_gv_first.setAdapter(gvFirstAdapter);
                // 为我推荐的解析
                gvSeconAdapter = new SaleGVSeconAdapter(getContext());
                gvSeconAdapter.setGvsecondBean(modulelistBeen);
                sale_gv_secon.setAdapter(gvSeconAdapter);
            }

            @Override
            public void failure() {

            }
        });
        // ListView 解析(商品列表)
        VolleyInstence.getInstence(getContext()).startRequest(NetUrl.SALE_GVFIRST_URL, new VolleyInterfaceResult() {
            @Override
            public void success(String str) {
                Gson gson = new Gson();
                SaleGVFirstBean gvFirstBean = gson.fromJson(str, SaleGVFirstBean.class);
                goodslistBeen = gvFirstBean.getResult().getGoodslist().getList();
                listViewAdapter.setLsBean(goodslistBeen);
                sale_ls.setAdapter(listViewAdapter);
            }

            @Override
            public void failure() {

            }
        });
        initBanner();
    }

    private void initBanner() {
        sale_banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        sale_banner.setDelayTime(2000);
        sale_banner.setImages(imgsurl);
    }

    /**
     * GridView 部分（轮播图下面的）
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

    /**
     * 商品列表详情
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), SaleLsActivity.class);
        intent.putExtra("saleUrl", goodslistBeen.get(position).getMurl());
        startActivity(intent);
    }
}
