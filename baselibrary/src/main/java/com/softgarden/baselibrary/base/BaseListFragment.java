package com.softgarden.baselibrary.base;

import android.support.v4.content.ContextCompat;

import com.softgarden.baselibrary.R;
import com.softgarden.baselibrary.databinding.LayoutRecyclerviewBinding;
import com.softgarden.baselibrary.widget.ColorDividerDecoration;

/**
 * Created by MirkoWu on 2017/3/24 0024.
 */

public abstract class BaseListFragment<T extends BasePresenter> extends BaseLazyFragment<T, LayoutRecyclerviewBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recyclerview;
    }


    @Override
    protected void initialize() {
        bindng.mRecyclerView.addItemDecoration(new ColorDividerDecoration(ContextCompat.getColor(getActivity(),
                R.color.common_line_color), 2));
        super.initialize();


    }

}
