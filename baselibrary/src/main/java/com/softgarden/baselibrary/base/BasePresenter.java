package com.softgarden.baselibrary.base;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public interface BasePresenter<T extends BaseDisplay> {
    void attachView(T view);

    void detachView();
}
