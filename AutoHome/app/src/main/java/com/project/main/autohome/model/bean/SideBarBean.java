package com.project.main.autohome.model.bean;

/**
 * Created by youyo on 2016/7/14 0014.
 * 索引实体类  【找车-常用论坛-车系论坛】
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
