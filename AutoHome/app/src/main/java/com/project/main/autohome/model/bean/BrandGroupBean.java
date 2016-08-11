package com.project.main.autohome.model.bean;

import java.util.List;

/**
 * Created by youyo on 2016/7/22 0022.
 * 品牌页  抽屉的实体类
 */
public class BrandGroupBean {

    /**
     * returncode : 0
     * message : ok
     * result : {"fctlist":[{"name":"一汽-大众奥迪","serieslist":[{"id":3170,"name":"奥迪A3","imgurl":"http://car2.autoimg.cn/cardfs/product/g8/M0F/72/19/s_autohomecar__wKjBz1cy_SaAWhoaAAkLW9ousmE052.jpg","levelid":3,"levelname":"紧凑型车","price":"18.49-28.10万"},{"id":692,"name":"奥迪A4L","imgurl":"http://car3.autoimg.cn/cardfs/product/g9/M0C/AA/11/s_autohomecar__wKgH31Yc20iAf1fSAAlGgy0uSJs969.jpg","levelid":4,"levelname":"中型车","price":"28.99-39.39万"},{"id":18,"name":"奥迪A6L","imgurl":"http://car2.autoimg.cn/cardfs/product/g4/M11/3F/17/s_autohomecar__wKjB01aYxASAaykrAAeG0id7VEE957.jpg","levelid":5,"levelname":"中大型车","price":"41.53-75.76万"},{"id":2951,"name":"奥迪Q3","imgurl":"http://car2.autoimg.cn/cardfs/product/g8/M09/57/E8/s_autohomecar__wKjBz1cYu12ANSDIAAZJpKAxx6A192.jpg","levelid":17,"levelname":"紧凑型SUV","price":"23.42-34.49万"},{"id":812,"name":"奥迪Q5","imgurl":"http://car3.autoimg.cn/cardfs/product/g4/M00/C7/4D/s_autohomecar__wKjB01YzQLOAEwk5AAg-hXUe1kI849.jpg","levelid":18,"levelname":"中型SUV","price":"38.34-53.40万"}]},{"name":"奥迪(进口)","serieslist":[{"id":650,"name":"奥迪A1","imgurl":"http://car3.autoimg.cn/cardfs/product/g10/M14/0A/93/s_autohomecar__wKgH0VZqrNyAFYIuAAqZdgOh5OU692.jpg","levelid":2,"levelname":"小型车","price":"19.98-28.98万"},{"id":370,"name":"奥迪A3(进口)","imgurl":"http://car0.autoimg.cn/upload/2014/3/7/s_201403071839012684435.jpg","levelid":3,"levelname":"紧凑型车","price":"29.98-40.78万"},{"id":2730,"name":"奥迪S3","imgurl":"http://car0.autoimg.cn/car/upload/2014/12/26/s_20141226194244775421811.jpg","levelid":3,"levelname":"紧凑型车","price":"39.98万"},{"id":471,"name":"奥迪A4(进口)","imgurl":"http://car0.autoimg.cn/upload/2012/12/6/s_201212061749134034136.jpg","levelid":4,"levelname":"中型车","price":"41.80万"},{"id":538,"name":"奥迪A5","imgurl":"http://car3.autoimg.cn/cardfs/product/g22/M01/B6/5B/s_autohomecar__wKjBwVbdXbuALmJMAAmQWuwdsiM117.jpg","levelid":4,"levelname":"中型车","price":"39.80-62.80万"},{"id":2734,"name":"奥迪S5","imgurl":"http://car2.autoimg.cn/cardfs/product/g13/M07/84/D5/s_autohomecar__wKgH41X_XRuAPk5pAAm4nOU5oWU216.jpg","levelid":4,"levelname":"中型车","price":"67.90-85.80万"},{"id":472,"name":"奥迪A6(进口)","imgurl":"http://car3.autoimg.cn/cardfs/product/g9/M11/F1/79/s_autohomecar__wKgH31ZVF_OAR5jdAAmZJ6_61sY152.jpg","levelid":5,"levelname":"中大型车","price":"59.98-63.80万"},{"id":2736,"name":"奥迪S6","imgurl":"http://car2.autoimg.cn/cardfs/product/g19/M07/21/16/s_autohomecar__wKjBxFanZiSAdsTbAAfYPU8oOZM314.jpg","levelid":5,"levelname":"中大型车","price":"99.98万"},{"id":740,"name":"奥迪A7","imgurl":"http://car3.autoimg.cn/cardfs/product/g21/M01/8A/B6/s_autohomecar__wKgFWldjyoqATbKyAAs27_I9VlU608.jpg","levelid":5,"levelname":"中大型车","price":"59.80-93.80万"},{"id":2738,"name":"奥迪S7","imgurl":"http://car2.autoimg.cn/cardfs/product/g8/M05/5D/E8/s_autohomecar__wKjBz1XhcD2AFMUFAAcv1XoQe1Q045.jpg","levelid":5,"levelname":"中大型车","price":"135.80万"},{"id":146,"name":"奥迪A8","imgurl":"http://car3.autoimg.cn/cardfs/product/g14/M03/89/E4/s_autohomecar__wKjByVdFe2aAdF2WAAolcSOJ8-E760.jpg","levelid":6,"levelname":"大型车","price":"87.98-271.50万"},{"id":2739,"name":"奥迪S8","imgurl":"http://car3.autoimg.cn/cardfs/product/g13/M05/B2/07/s_autohomecar__wKgH41drwESAUpXpAAdswFIl4jk047.jpg","levelid":6,"levelname":"大型车","price":"198.80万"},{"id":2264,"name":"奥迪Q3(进口)","imgurl":"http://car0.autoimg.cn/upload/spec/13466/s_201207261746153014136.jpg","levelid":17,"levelname":"紧凑型SUV","price":"37.70-42.88万"},{"id":593,"name":"奥迪Q5(进口)","imgurl":"http://car0.autoimg.cn/upload/2013/2/5/s_201302051848032953686.jpg","levelid":18,"levelname":"中型SUV","price":"58.80-61.80万"},{"id":2841,"name":"奥迪SQ5","imgurl":"http://car0.autoimg.cn/upload/2014/1/22/s_201401221807299744435.jpg","levelid":18,"levelname":"中型SUV","price":"66.80万"},{"id":412,"name":"奥迪Q7","imgurl":"http://car3.autoimg.cn/cardfs/product/g18/M03/E3/37/s_autohomecar__wKjBxVZKgcmAVCk6AAdfzk_y-Ms594.jpg","levelid":19,"levelname":"中大型SUV","price":"75.38-109.88万"},{"id":148,"name":"奥迪TT","imgurl":"http://car0.autoimg.cn/car/upload/2015/4/30/s_201504301834341855465110.jpg","levelid":7,"levelname":"跑车","price":"54.28-61.78万"},{"id":2740,"name":"奥迪TTS","imgurl":"http://car3.autoimg.cn/cardfs/product/g22/M05/72/EA/s_autohomecar__wKjBwVdL85WAIXB0AAjt_ZIMN-w070.jpg","levelid":7,"levelname":"跑车","price":"68.18-72.98万"},{"id":511,"name":"奥迪R8","imgurl":"http://car2.autoimg.cn/cardfs/product/g6/M14/CA/CB/s_autohomecar__wKgH3FeDHAKAWvT3AATJxXYzFg8749.jpg","levelid":7,"levelname":"跑车","price":"182.30-252.30万"}]},{"name":"奥迪RS","serieslist":[{"id":2735,"name":"奥迪RS 5","imgurl":"http://car0.autoimg.cn/upload/2014/8/14/s_20140814175728852443511.jpg","levelid":4,"levelname":"中型车","price":"109.80-128.80万"},{"id":2994,"name":"奥迪RS 7","imgurl":"http://car3.autoimg.cn/cardfs/product/g13/M11/A0/8F/s_autohomecar__wKgH41YWQJGAajVoAAkX9b_4aw0309.jpg","levelid":5,"levelname":"中大型车","price":"169.88万"}]}]}
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
         * name : 一汽-大众奥迪
         * serieslist : [{"id":3170,"name":"奥迪A3","imgurl":"http://car2.autoimg.cn/cardfs/product/g8/M0F/72/19/s_autohomecar__wKjBz1cy_SaAWhoaAAkLW9ousmE052.jpg","levelid":3,"levelname":"紧凑型车","price":"18.49-28.10万"},{"id":692,"name":"奥迪A4L","imgurl":"http://car3.autoimg.cn/cardfs/product/g9/M0C/AA/11/s_autohomecar__wKgH31Yc20iAf1fSAAlGgy0uSJs969.jpg","levelid":4,"levelname":"中型车","price":"28.99-39.39万"},{"id":18,"name":"奥迪A6L","imgurl":"http://car2.autoimg.cn/cardfs/product/g4/M11/3F/17/s_autohomecar__wKjB01aYxASAaykrAAeG0id7VEE957.jpg","levelid":5,"levelname":"中大型车","price":"41.53-75.76万"},{"id":2951,"name":"奥迪Q3","imgurl":"http://car2.autoimg.cn/cardfs/product/g8/M09/57/E8/s_autohomecar__wKjBz1cYu12ANSDIAAZJpKAxx6A192.jpg","levelid":17,"levelname":"紧凑型SUV","price":"23.42-34.49万"},{"id":812,"name":"奥迪Q5","imgurl":"http://car3.autoimg.cn/cardfs/product/g4/M00/C7/4D/s_autohomecar__wKjB01YzQLOAEwk5AAg-hXUe1kI849.jpg","levelid":18,"levelname":"中型SUV","price":"38.34-53.40万"}]
         */

        private List<FctlistBean> fctlist;

        public List<FctlistBean> getFctlist() {
            return fctlist;
        }

        public void setFctlist(List<FctlistBean> fctlist) {
            this.fctlist = fctlist;
        }

        public static class FctlistBean {
            private String name;
            /**
             * id : 3170
             * name : 奥迪A3
             * imgurl : http://car2.autoimg.cn/cardfs/product/g8/M0F/72/19/s_autohomecar__wKjBz1cy_SaAWhoaAAkLW9ousmE052.jpg
             * levelid : 3
             * levelname : 紧凑型车
             * price : 18.49-28.10万
             */

            private List<SerieslistBean> serieslist;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<SerieslistBean> getSerieslist() {
                return serieslist;
            }

            public void setSerieslist(List<SerieslistBean> serieslist) {
                this.serieslist = serieslist;
            }

            public static class SerieslistBean {
                private int id;
                private String name;
                private String imgurl;
                private int levelid;
                private String levelname;
                private String price;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getImgurl() {
                    return imgurl;
                }

                public void setImgurl(String imgurl) {
                    this.imgurl = imgurl;
                }

                public int getLevelid() {
                    return levelid;
                }

                public void setLevelid(int levelid) {
                    this.levelid = levelid;
                }

                public String getLevelname() {
                    return levelname;
                }

                public void setLevelname(String levelname) {
                    this.levelname = levelname;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }
            }
        }
    }
}
