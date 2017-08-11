package com.softgarden.baselibrary.base;

import android.support.annotation.LayoutRes;
import android.view.View;

import com.mirkowu.library.BaseRVHolder;
import com.softgarden.baselibrary.base.databinding.DataBindingAdapter;

import java.util.ArrayList;

/**
 * Created by MirkoWu on 2017/3/30 0030.
 */

public class SelectedAdapter<T> extends DataBindingAdapter<T> {
    private boolean openSelecter = true;//总开关 开启选择功能
    private int selectIndex = 0;//单个选中状态
    private boolean multiSelected;//是否多选 默认为单选
    private ArrayList<Integer> selectList = new ArrayList<>();//多选下标集合

    public SelectedAdapter(@LayoutRes int mLayoutResId, int variable) {
        super(mLayoutResId, variable);
    }


    /**
     * 设置开启模式
     *
     * @param isOpen 开启选择模式  默认单选
     */
    public void setOpenSelecter(boolean isOpen) {
        this.openSelecter = isOpen;
        notifyDataSetChanged();
        setOpenSelecter(isOpen, -1);
    }


    /**
     * @param isOpen
     * @param defaultIndex 单选的 默认选中下标
     */
    public void setOpenSelecter(boolean isOpen, int defaultIndex) {
        this.openSelecter = isOpen;
        this.selectIndex = defaultIndex;
        notifyDataSetChanged();
    }

    /**
     * 设置开启模式
     *
     * @param isOpen        开启选择模式
     * @param multiSelected 是否单选
     */
    public void setOpenSelecter(boolean isOpen, boolean multiSelected) {
        this.openSelecter = isOpen;
        this.multiSelected = multiSelected;
        notifyDataSetChanged();
    }

    public ArrayList<Integer> getSelectedList() {
        return selectList;
    }

    public int getSelectedIndex() {
        return selectIndex;
    }

    public void clearSelectIndex() {
        selectIndex = 0;//这里的值 自己定
        notifyDataSetChanged();
    }

    @Override
    protected void onItemClick(View v, int position) {
        super.onItemClick(v, position);
        //设置选择器
        if (openSelecter) {
            if (multiSelected) {
                if (selectList.contains(position)) {
                    selectList.remove((Integer) position);
                } else {
                    selectList.add(position);
                }
            } else {
                if (selectIndex > -1) notifyItemChanged(selectIndex);
                selectIndex = position;
            }
            notifyItemChanged(position);
        }
    }

    @Override
    public void onBindViewHolder(BaseRVHolder holder, int position) {
        holder.isSelected = multiSelected ? selectList.contains(position) : selectIndex == position;//设置状态
        holder.itemView.setSelected(holder.isSelected);
        super.onBindViewHolder(holder, position);
    }


}
