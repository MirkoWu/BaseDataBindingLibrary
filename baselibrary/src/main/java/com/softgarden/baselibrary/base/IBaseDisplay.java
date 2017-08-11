package com.softgarden.baselibrary.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public interface IBaseDisplay {
    void showError(Throwable throwable);

    void showProgressDialog();

    void hideProgressDialog();

    void useNightMode(boolean isNight);

    <T> LifecycleTransformer<T> bindToLifecycle();

  //  <T> LifecycleTransformer<T> bindUntilEvent();

}
