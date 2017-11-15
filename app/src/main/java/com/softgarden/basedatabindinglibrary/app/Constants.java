package com.softgarden.basedatabindinglibrary.app;

/**
 * Created by DELL on 2017/8/10.
 */

public class Constants {
    //network
    public static final String HOST_URL = "http://guocai.test2.ruanjiekeji.com/";

    public static final String MD5_KEY = "d367f4699214cec412f7c2a1d513fe05";
    public static final String HOME_GOODSLIST = "App/Goods/goodsList";//商品列表
    public static final int PAGE_COUNT = 10;

    public static String getImageURL(String url) {
        return HOST_URL + url;
    }
}
