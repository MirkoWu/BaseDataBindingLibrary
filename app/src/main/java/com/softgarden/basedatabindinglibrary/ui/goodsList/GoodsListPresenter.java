package com.softgarden.basedatabindinglibrary.ui.goodsList;


import com.softgarden.basedatabindinglibrary.network.NetworkTransformerHelper;
import com.softgarden.basedatabindinglibrary.network.RetrofitManager;
import com.softgarden.baselibrary.base.BasePresenter;


/**
 * Created by MirkoWu on 2017/3/22 0022.
 */
public class GoodsListPresenter extends BasePresenter<GoodsListContract.Display> implements GoodsListContract.Presenter {

    public GoodsListPresenter() {

    }


    @Override
    public void goodsList(int page, int pageSize) {
        RetrofitManager.getHomeService()
                .goodsList(page, pageSize)
                .compose(new NetworkTransformerHelper<>(mView))
                .subscribe(mView::goodsList, mView::showError);
    }

    public void countGoods(){

    }
}
