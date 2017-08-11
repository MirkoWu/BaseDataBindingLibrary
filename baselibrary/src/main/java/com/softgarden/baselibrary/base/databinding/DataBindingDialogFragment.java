package com.softgarden.baselibrary.base.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.softgarden.baselibrary.base.BaseDialogFragment;

/**
 * Created by Lightwave on 2015/12/3.
 */
public abstract class DataBindingDialogFragment<B extends ViewDataBinding> extends BaseDialogFragment {
    protected B binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(getLayoutId(), container, false);
        binding = DataBindingUtil.bind(view);
        return view;
    }
}
