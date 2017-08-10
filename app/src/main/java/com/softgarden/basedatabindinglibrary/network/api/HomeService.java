package com.softgarden.basedatabindinglibrary.network.api;


import com.softgarden.basedatabindinglibrary.app.Constants;
import com.softgarden.basedatabindinglibrary.bean.GoodsBean;
import com.softgarden.basedatabindinglibrary.network.BaseBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by MirkoWu on 2017/3/23 0023.
 */

public interface HomeService {

    /**
     * 商品列表
     *
     * @param page          (可选)当前页码
     * @param pageSize      (可选)每页条数
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.HOME_GOODSLIST)
    Observable<BaseBean<List<GoodsBean>>> goodsList(@Field("page") int page,
                                                    @Field("pageSize") int pageSize
    );


}
