package com.project.main.autohome.model.bean;

import java.util.List;

/**
 * Created by youyo on 2016/7/19 0019.
 * 发现页轮播图
 */
public class SaleAutoBean {

    /**
     * returncode : 0
     * message :
     * result : {"list":[{"id":7873,"title":"20160713","shorttitle":"替换悦动新专题","url":"http://mall.m.autohome.com.cn/topic/2016/6/xiandai/#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g11/M02/CE/BE/autohomecar__wKgH0leGEhmAMb9lAAFWMawbTJg428.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":7868,"title":"20160713","shorttitle":"开放平台－7月活动","url":"http://m.mall.autohome.com.cn/topic/2016/7/717festival/#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g11/M05/CE/73/autohomecar__wKgH0leF47mAWPMPAAKblzmeh58768.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":7875,"title":"7月19日分期活动","shorttitle":"通用列表","url":"http://j.autohome.com.cn/loan/loan/doubleele!warmupAppIndex?pageType=3#pvareaid=106574","imgurl":"http://app2.autoimg.cn/appdfs/g13/M0C/D4/32/autohomecar__wKjByleMh8OATEO0AAG-ppSKuI8353.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":7866,"title":"20160713","shorttitle":"pop－专题活动","url":"http://m.mall.autohome.com.cn/topic/2016/7/xjgcj/index.html#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g20/M03/AE/D9/autohomecar__wKgFWVeF4NmAQbcsAAKQNuLIYoo593.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0},{"id":7870,"title":"20160713","shorttitle":"众泰大迈X5","url":"http://m.mall.autohome.com.cn/topic/2016/7/damai/index.html#pvareaid=104735","imgurl":"http://app2.autoimg.cn/appdfs/g15/M00/CF/5B/autohomecar__wKgH1leF5h-AUz91AAJKYiGXNDk957.jpg","urlscheme":"","type":2,"appicon":"","siteindex":0}]}
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
         * id : 7873
         * title : 20160713
         * shorttitle : 替换悦动新专题
         * url : http://mall.m.autohome.com.cn/topic/2016/6/xiandai/#pvareaid=104735
         * imgurl : http://app2.autoimg.cn/appdfs/g11/M02/CE/BE/autohomecar__wKgH0leGEhmAMb9lAAFWMawbTJg428.jpg
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
