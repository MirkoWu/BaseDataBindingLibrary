package com.softgarden.baselibrary.base.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mirkowu.library.BaseRVAdapter;
import com.mirkowu.library.BaseRVHolder;
import com.softgarden.baselibrary.R;

/**
 * Created by MirkoWu on 2017/4/10 0010.
 */

public class DataBindingAdapter<T> extends BaseRVAdapter<T, BaseRVHolder> {
    protected int variable;

    public DataBindingAdapter(@LayoutRes int mLayoutResId, int variable) {
        super(mLayoutResId);
        this.variable = variable;
    }


    @Override
    public void onBindVH(BaseRVHolder holder, T data, int position) {
        ViewDataBinding binding = (ViewDataBinding) holder.itemView
                .getTag(R.id.XRVAdapter_databinding_support);
        binding.setVariable(variable, data);
        databinding(holder, data, position, binding);
        binding.executePendingBindings();
    }

    /***
     * 一般不用重写
     * 可以重写该方法  进行其他的数据绑定
     *
     * @param holder
     * @param data
     * @param position
     * @param binding
     */
    public void databinding(BaseRVHolder holder, T data, int position, ViewDataBinding binding) {

    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.XRVAdapter_databinding_support, binding);
        return view;
    }

    @Override
    protected void onItemClick(View v, int position) {
        if (isLoadMoreEnable() && (position == getItemCount() - 1))
            return;//当开启更多的时候，就屏蔽掉底部的点击事件
        super.onItemClick(v, position);
    }

}
