package com.softgarden.basedatabindinglibrary.ui.goodsList;

import com.softgarden.basedatabindinglibrary.bean.GoodsBean;
import com.softgarden.baselibrary.base.BaseDisplay;
import com.softgarden.baselibrary.base.BasePresenter;

import java.util.List;

/**
 * Created by MirkoWu on 2017/3/22 0022.
 */

public interface GoodsListContract {
    interface Display extends BaseDisplay {


        void goodsList(List<GoodsBean> list);
    }

    interface Presenter extends BasePresenter<Display> {

        void goodsList(int page,int pageSize);


    }
}
