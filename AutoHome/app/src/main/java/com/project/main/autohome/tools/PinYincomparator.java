package com.project.main.autohome.tools;

import com.project.main.autohome.model.bean.SideBarBean;

import java.util.Comparator;

/**
 * Created by youyo on 2016/7/14 0014.
 */
public class PinYincomparator implements Comparator<SideBarBean> {
    @Override
    public int compare(SideBarBean sideBarBean, SideBarBean t1) {
        if (sideBarBean.getSortLetters().equals("@")
                || t1.getSortLetters().equals("*")) {
            return -1;
        } else if (sideBarBean.getSortLetters().equals("*")
                || t1.getSortLetters().equals("@")) {
            return 1;
        } else {
            return sideBarBean.getSortLetters().compareTo(t1.getSortLetters());
        }
    }
}
