package com.project.main.autohome.ui.fragment.forumpager;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.project.main.autohome.R;
import com.project.main.autohome.model.bean.SideBarBean;
import com.project.main.autohome.tools.CharacterParser;
import com.project.main.autohome.tools.NetWorkConnectedToast;
import com.project.main.autohome.tools.PinYincomparator;
import com.project.main.autohome.tools.SideBar;
import com.project.main.autohome.ui.adapter.SortAdapter;
import com.project.main.autohome.ui.fragment.AbsBaseFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by youyo on 2016/7/13 0013.
 * 常用论坛
 */
public class CommonForumFragment extends AbsBaseFragment implements View.OnClickListener {
    private LinearLayout fo_comm_carcomm, fo_comm_city, fo_comm_theme_comm;
    private DrawerLayout fo_comm_drawer;
    private SideBar sideBar;
    private SortAdapter sortAdapter;
    private ListView listView;
    private CharacterParser characterParser;
    private List<SideBarBean> sideBarBeen;
    private PinYincomparator pinYincomparator;



    @Override
    protected int setLayout() {
        return R.layout.fo_commonforum_frag;
    }

    @Override
    protected void initView() {
        fo_comm_carcomm = byView(R.id.fo_comm_carcomm);
        fo_comm_city = byView(R.id.fo_comm_city);
        fo_comm_theme_comm = byView(R.id.fo_comm_theme_comm);
        fo_comm_drawer = byView(R.id.fo_common_drawer);
        sideBar = byView(R.id.fo_sideBar);
        listView = byView(R.id.fo_commdrawer_ls);
    }

    @Override
    protected void initData() {
        fo_comm_carcomm.setOnClickListener(this);
        fo_comm_city.setOnClickListener(this);
        fo_comm_theme_comm.setOnClickListener(this);

        characterParser = CharacterParser.getInstance();
        pinYincomparator  =new PinYincomparator();

        NetWorkConnectedToast.getConnectedToast().isNet(getContext());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fo_comm_carcomm:
                fo_comm_drawer.openDrawer(GravityCompat.END);
                sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
                    @Override
                    public void OnTouchingLetterChangedListener(String s) {
                        int position = sortAdapter.getPositionForSection(s.charAt(0));
                        if(position != -1){
                            listView.setSelection(position);
                        }
                    }
                });
                sideBarBeen = filledData(getResources().getStringArray(R.array.date));
                Collections.sort(sideBarBeen, pinYincomparator);
                sortAdapter = new SortAdapter(getContext(),sideBarBeen);
                listView.setAdapter(sortAdapter);

                break;
            case R.id.fo_comm_city:
                Toast.makeText(getContext(), "地区", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fo_comm_theme_comm:
                Toast.makeText(getContext(), "主题", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
    /**
     * 为ListView填充数据
     * @param date
     * @return
     */
    private List<SideBarBean> filledData(String [] date){
        List<SideBarBean> mSortList = new ArrayList<SideBarBean>();

        for(int i=0; i<date.length; i++){
            SideBarBean sortModel = new SideBarBean();
            sortModel.setName(date[i]);
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }
}

