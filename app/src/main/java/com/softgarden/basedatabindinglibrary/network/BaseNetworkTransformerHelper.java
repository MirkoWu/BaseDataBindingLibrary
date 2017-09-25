package com.softgarden.basedatabindinglibrary.network;


import com.softgarden.baselibrary.base.IBaseDisplay;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Lightwave on 2016/6/28.
 */
public class BaseNetworkTransformerHelper<T> implements ObservableTransformer<BaseBean<T>, T> {
    private IBaseDisplay view;

    public BaseNetworkTransformerHelper(IBaseDisplay view) {
        this.view = view;
    }

    @Override
    public ObservableSource<T> apply(Observable<BaseBean<T>> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseBean -> {
                    if (baseBean.status == 1) {
                        return baseBean;
                    } else {
                        view.hideProgressDialog();
                        //TODO   ToastUtil.s(baseBean.errorMsg); 还是放到activity 和fragment 显示吧
                        throw Exceptions.propagate(new ApiException(baseBean.status, baseBean.info));
                    }
                })
                .map(baseBean -> {
                    if (baseBean.data == null) {
                        baseBean.data = (T) "";
                    }
                    return baseBean;
                })
                .map(baseBean -> baseBean.data)
                .compose(view.bindToLifecycle());

    }
}
