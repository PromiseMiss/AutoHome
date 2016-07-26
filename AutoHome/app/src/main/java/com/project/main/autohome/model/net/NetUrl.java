package com.project.main.autohome.model.net;

/**
 * Created by youyo on 2016/7/15 0015.
 * 网址常量类
 */
public final class NetUrl {
    // 最新
    public static final String UP_TO_DATA_URL = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt0-p1-s30-l0.json";
    // 刷新最新
    public static final String CUSTOM_UP_TO_DATA_URL = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt0-p1-s30-l890560.json";
    // 优创+
    public static final String UNIHUB_URL = "http://news.app.autohome.com.cn/news_v6.1.0/newspf/NPNewsList.ashx?a=2&pm=2&v=6.1.0&au=&type=3&lastid=&lastuid=0&size=30";
    // 热帖
    public static final String HOT_POSTS_URL = "http://club.app.autohome.com.cn/club_v6.1.0/club/shotfoumlist-pm1-p1-s50.json";
    //视频
    public static final String VIDEO_URL = "http://app.api.autohome.com.cn/autov4.8.8/news/videolist-pm1-vt0-s30-lastid0.json";
    // 快报
    public static final String BULLETIN_URL = "http://app.api.autohome.com.cn/autov4.8.8/news/fastnewslist-pm1-b0-l0-s20-lastid0.json";
    // 新闻
    public static final String NEWS_URL = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt1-p1-s30-l0.json";
    // 评测
    public static final String EVALUATION_URL = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt3-p1-s30-l0.json";
    // 导购
    public static final String GUIDE_URL = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt60-p1-s30-l0.json";
    // 行情
    public static final String QUOTATION_URL = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c110100-nt2-p1-s30-l0.json";
    // 用车
    public static final String FIND_CAR_URL = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt82-p1-s30-l0.json";
    //技术
    public static final String TECHNOLOGY_URL = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt102-p1-s30-l0.json";
    //文化
    public static final String CULTURE_URL = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt97-p1-s30-l0.json";
    // 改装
    public static final String CONVERSION_URL = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt107-p1-s30-l0.json";
    // 游记
    public static final String TRAVEL_URL = "http://app.api.autohome.com.cn/autov4.8.8/news/newslist-pm1-c0-nt100-p1-s30-l0.json";
    // 原创视频
    public static final String ORIGINALLY_VIDEO_URL = "http://app.api.autohome.com.cn/autov4.8.8/news/videolist-pm1-vt8-s30-lastid0.json";
    //说客
    public static final String LOBBYIST_URL = "http://app.api.autohome.com.cn/autov4.8.8/news/shuokelist-pm1-s30-lastid0.json";

    // 那42个网址的数组
    public static final String[] RECOMMEND_ALL = {"http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c0-p1-s30.json",// 全部
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c104-p1-s30.json",// 媳妇当车模
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c110-p1-s30.json",// 美人计
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c172-p1-s30.json",// 论坛名人堂
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c230-p1-s30.json",//论坛讲师
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c233-p1-s30.json",// 汽车之家十年
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c121-p1-s30.json",// 精挑细选
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c106-p1-s30.json",// 现身说法
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c118-p1-s30.json",// 高端阵地
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c210-p1-s30.json",// 电动车
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c199-p1-s30.json",//汇买车
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c198-p1-s30.json",//行车点评
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c168-p1-s30.json",// 超级试驾员
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c113-p1-s30.json",// 海外购车
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c109-p1-s30.json",//经典老车
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c191-p1-s30.json",//妹子选车
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c196-p1-s30.json",// 优惠购车
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c107-p1-s30.json",//原创大片
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c105-p1-s30.json",// 顶配风采
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c122-p1-s30.json",// 改装有理
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c194-p1-s30.json",// 养车有道
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c119-p1-s30.json",// 首发阵营
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c112-p1-s30.json",// 新车直播
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c120-p1-s30.json",// 历史选题
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c184-p1-s30.json",// 摩友天地
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c108-p1-s30.json",// 蜜月之旅
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c124-p1-s30.json",//甜蜜婚礼
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c123-p1-s30.json",//摄影课堂
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c185-p1-s30.json",// 车友聚会
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c186-p1-s30.json",// 单车部落
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c214-p1-s30.json",// 杂谈俱乐部
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c218-p1-s30.json",// 华北游记
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c223-p1-s30.json",// 西南游记
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c221-p1-s30.json",// 东北游记
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c222- p1 - s30.json",// 西北游记
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c220-p1-s30.json",// 华中游记
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c224-p1-s30.json",// 华南游记
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c219-p1-s30.json",// 华东游记
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c225-p1-s30.json",// 港澳台游记
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c2 26 - p1 - s30.json",//海外游记
            "http://app.api.autohome.com.cn/autov4.8.8/club/jingxuantopic-pm1-c212-p1-s30.json",//沧海遗珠
    };
    // 发现  有8个GridView
    public static final String FIND_FIRST = "http://223.99.255.20/mobilenc.app.autohome.com.cn/discover_v5.8.0/mobile/functionlist-a2-pm2-v5.8.5-pid210000-cid210200.json";
    // 有轮播图
    public static final String FIND_FIRST_RECYCLER = "http://app.api.autohome.com.cn/autov5.0.0/mobile/appadvert-a2-pm1-v5.0.1-sid2-pid340000-cid0-lat0.000000-lng0.000000.json";
    //发现页轮播图详情
    public static final String FIND_DETATIL = "http://app.api.autohome.com.cn/autov5.0.0/mobile/appadvert-a2-pm1-v5.0.1-sid2-pid340000-cid0-lat0.000000-lng0.000000.json";

    // 筛选
    public static final String FILTER = "http://cars.app.autohome.com.cn/cars_v5.8.0/cars/gethotseries-a2-pm2-v6.1.0-p1-s20.json";
    // 品牌GridV
    public static final String BRANDGV_URL = "http://223.99.255.20/cars.app.autohome.com.cn/dealer_v5.7.0/dealer/hotbrands-pm2.json";
    // 标志
    public static final String BRAND_BIAOZHI = "http://app.api.autohome.com.cn/autov5.0.0/news/brandsfastnews-pm1-ts0.json";

    // 论坛页面网址拼接
    public static final String FEATURED_QUERY = "http://forum.app.autohome.com.cn/autov5.0.0/forum/club/topiccontent-a2-pm2-v5.0.0-t";
    public static final String FEATURED_BOTTOM = "-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw320.json";

    // 视频详情页
    public static final String VIDEO_DETAILS = "http://v.autohome.com.cn/v_4_";
    public static final String VIDEO_DETAILS_BOTTOM = ".html";

    // 最新页详情
    public static final String UPTODATA_DETAILS = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n";
    public static final String UPTODATA_BOTTOM = "-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html";

    // 热帖详情页
    public static final String HOT_TOP_URL = "http://forum.app.autohome.com.cn/autov5.0.0/forum/club/topiccontent-a2-pm2-v5.0.0-t";
    public static final String HTOP_BOTTOM_URL = "-o0-p1-s20-c1-nt0-fs0-sp0-al0-cw320.json";
    // 新闻详情页
    public static final String NEWS_TOP_URL = "http://cont.app.autohome.com.cn/autov4.2.5/content/News/newscontent-a2-pm1-v4.2.5-n";
    public static final String NEWS_BOTTOM_URL = "-lz0-sp0-nt0-sa1-p0-c1-fs0-cw320.html";

    // 快报详情页
    public static final String BULLETIN_TOP_URL = "http://cont.app.autohome.com.cn/autov5.0.0/content/News/fastnewscontent-n";
    public static final String BULLTIN_BOTTOM_URL = "-lastid0-o1.json";

    // 找车详情
    // 在售
    public static final String Test = "http://app.api.autohome.com.cn/autov5.0.0/cars/seriesprice-pm1-b33-t1.json";


    public static final String BRAND_NOW_TOP = "http://app.api.autohome.com.cn/autov5.0.0/cars/seriesprice-pm1-b";
    public static final String BRAND_NOW_BOTT = "-t1.json";
    // 全部
    public static final String BRAND_ALL_TOP = "http://app.api.autohome.com.cn/autov5.0.0/cars/seriesprice-pm2-b";
    public static final String BARND_ALL_BOTT = "-t2.json";


    // 发现页  轮播图下面的下面的GV
    public static final String SALE_GVFIRST_URL = "http://223.99.255.20/mobilenc.app.autohome.com.cn/discover_v5.8.0/mall/intelligentrecommend.ashx?a=2&pm=2&v=5.8.5&uid=0&deviceid=99000628573771&gps=38.889659,121.551063&cityid=210200&pid=210000&pageindex=1&pagesize=20&hid";


}
