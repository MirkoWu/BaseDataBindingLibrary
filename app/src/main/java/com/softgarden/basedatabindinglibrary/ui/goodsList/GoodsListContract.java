package com.softgarden.basedatabindinglibrary.ui.goodsList;

import com.softgarden.basedatabindinglibrary.bean.GoodsBean;
import com.softgarden.baselibrary.base.IBaseDisplay;
import com.softgarden.baselibrary.base.IBasePresenter;

import java.util.List;

/**
 * Created by MirkoWu on 2017/3/22 0022.
 */

public interface GoodsListContract {
    interface Display extends IBaseDisplay {


        void goodsList(List<GoodsBean> list);
    }

    interface Presenter extends IBasePresenter<Display> {

        void goodsList(int page,int pageSize);


    }
}
