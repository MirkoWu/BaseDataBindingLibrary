package com.softgarden.baselibrary.base;


import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Administrator on 2016/9/10 0010.
 * 基于Rx的Presenter,控制订阅的生命周期
 */
public class RxPresenter<T extends BaseDisplay> implements BasePresenter<T> {
    protected T mView;
    protected CompositeDisposable mDisposable;

    protected void addSubscribe(CompositeDisposable mDisposable) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(mDisposable);
    }

    protected void unSubscribe() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    @Override
    public void attachView(T display) {
        this.mView = display;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
