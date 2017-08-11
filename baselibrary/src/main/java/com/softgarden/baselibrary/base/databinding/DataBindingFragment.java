package com.softgarden.baselibrary.base.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softgarden.baselibrary.base.BaseFragment;
import com.softgarden.baselibrary.base.IBasePresenter;

/**
 * 可以
 * 使用DataBinding 的Fragment 基类
 */

public abstract class DataBindingFragment<T extends IBasePresenter, B extends ViewDataBinding> extends BaseFragment<T> {
    protected B bindng;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindng = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return bindng.getRoot();
    }


}
