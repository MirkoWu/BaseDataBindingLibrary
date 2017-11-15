package com.softgarden.baselibrary.base.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.softgarden.baselibrary.R;

import java.util.List;

/**
 * @author by DELL
 * @date on 2017/11/3
 * @describe 使用DataBinding的Adapter  可以通用
 */

public class DataBindingAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    protected int variable;//BR

    public DataBindingAdapter(int layoutResId, int variable, @Nullable List<T> data) {
        super(layoutResId, data);
        this.variable = variable;
    }

    public DataBindingAdapter(@LayoutRes int mLayoutResId, int variable) {
        super(mLayoutResId);
        this.variable = variable;
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        ViewDataBinding binding =
                (ViewDataBinding) helper.itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        binding.setVariable(variable, item);
        dataBinding(helper, binding, item);
        binding.executePendingBindings();
    }


    /***
     * 该方法在 executePendingBindings 前执行
     * 所以可以重写该方法  进行其他的数据绑定
     *
     * 一般不用重写
     * @param holder
     * @param binding
     * @param item
     */
    public void dataBinding(BaseViewHolder holder, ViewDataBinding binding, T item) {

    }


    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

}
