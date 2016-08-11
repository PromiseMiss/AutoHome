package com.project.main.autohome.model.bean;

import java.util.List;

/**
 * Created by youyo on 2016/7/21 0021.
 * 发现页轮播图详情 实体类
 */
public class SaleBannerDetailsBean {

    /**
     * returncode : 0
     * message :
     * result : {"list":[{"id":7915,"title":"20160720","shorttitle":"开放平台活动","url":"http://m.mall.autohome.com.cn/topic/2016/7/july2/#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g4/M0D/D5/F6/autohomecar__wKgHy1ePBwqAN8BpAAKF-Vz0coE385.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":7917,"title":"20160720","shorttitle":"北汽幻速S2","url":"http://m.mall.autohome.com.cn/topic/2016/6/huansujune/index.html#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g11/M07/D6/12/autohomecar__wKjBzFePB7-AerfNAAIfqpmG_TE935.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":7964,"title":"7月21日分期活动","shorttitle":"两证一卡新","url":"http://topic.m.autohome.com.cn/mall/2016/7/18/#pvareaid=106574","imgurl":"http://app2.autoimg.cn/appdfs/g4/M07/D7/B4/autohomecar__wKjB01eQhpKAdCSMAAGoOKWFlI8453.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":7913,"title":"20160720","shorttitle":"瑞虎5","url":"http://mall.m.autohome.com.cn/detail/40829-110100-0.html#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g12/M0B/D5/1A/autohomecar__wKgH01ePBrKAPhwbAADrHXhktp0366.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":7919,"title":"20160720","shorttitle":"网红招募","url":"http://topic.m.autohome.com.cn/mall/2016/7/superstarvote/#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g17/M14/D1/BE/autohomecar__wKgH2FePCMSAfwsPAAGozHTgFMw862.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0}]}
     */

    private int returncode;
    private String message;
    private ResultBean result;

    public int getReturncode() {
        return returncode;
    }

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 7915
         * title : 20160720
         * shorttitle : 开放平台活动
         * url : http://m.mall.autohome.com.cn/topic/2016/7/july2/#pvareaid=104735
         * imgurl : http://app2.autoimg.cn/appdfs/g4/M0D/D5/F6/autohomecar__wKgHy1ePBwqAN8BpAAKF-Vz0coE385.jpg
         * urlscheme :
         * type : 2
         * appicon :
         * siteindex : 0
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int id;
            private String title;
            private String shorttitle;
            private String url;
            private String imgurl;
            private String urlscheme;
            private int type;
            private String appicon;
            private int siteindex;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getShorttitle() {
                return shorttitle;
            }

            public void setShorttitle(String shorttitle) {
                this.shorttitle = shorttitle;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public String getUrlscheme() {
                return urlscheme;
            }

            public void setUrlscheme(String urlscheme) {
                this.urlscheme = urlscheme;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAppicon() {
                return appicon;
            }

            public void setAppicon(String appicon) {
                this.appicon = appicon;
            }

            public int getSiteindex() {
                return siteindex;
            }

            public void setSiteindex(int siteindex) {
                this.siteindex = siteindex;
            }
        }
    }
}
