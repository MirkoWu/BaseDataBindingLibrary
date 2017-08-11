package com.softgarden.baselibrary.base.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.softgarden.baselibrary.R;
import com.softgarden.baselibrary.base.BaseActivity;
import com.softgarden.baselibrary.base.IBasePresenter;

/**
 *可以使用DataBinding的 Activity的基类
 */

public abstract class DataBindingActivity<T extends IBasePresenter, B extends ViewDataBinding> extends BaseActivity<T> {
    protected B binding;

    @Override
    protected void initContentView() {
        commonToolbar = setToolbar();
        if (commonToolbar != null) {
            //添加标题栏
            LinearLayout view = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(params);
            view.setOrientation(LinearLayout.VERTICAL);
            view.setFitsSystemWindows(true);
            view.addView(commonToolbar);
            view.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            view.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.toolbar_line));

            binding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutId(), view, false);

            view.addView(binding.getRoot());
            setContentView(view);
            setSupportActionBar(commonToolbar);//这里将toolbar设置为actionbar
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } else {
            binding = DataBindingUtil.setContentView(this, getLayoutId());
        }
    }


}
