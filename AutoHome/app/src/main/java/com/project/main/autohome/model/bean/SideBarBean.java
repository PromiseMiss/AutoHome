package com.project.main.autohome.model.bean;

/**
 * Created by Beyond on 2016/7/22.
 * 索引实体类
 */
public class SideBarBean {
    private String name;//显示的数据
    private String sortLetters;//首字母

    public SideBarBean() {
        super();
    }

    public SideBarBean(String name, String sortLetters) {
        this.name = name;
        this.sortLetters = sortLetters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}
