package com.softgarden.basedatabindinglibrary.ui;

import android.support.v4.content.ContextCompat;

import com.softgarden.basedatabindinglibrary.R;
import com.softgarden.basedatabindinglibrary.databinding.ActivityTranslucentBinding;
import com.softgarden.baselibrary.base.databinding.DataBindingActivity;
import com.softgarden.baselibrary.widget.CommonToolbar;

public class TranslucentActivity extends DataBindingActivity<ActivityTranslucentBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_translucent;
    }

    @Override
    protected CommonToolbar setToolbar() {
        return new CommonToolbar.Builder().setTitle("我能沉浸透明").build(this);
    }

    @Override
    protected void initialize() {
        getToolbar().hideStatusBar();//默认是显示 默认透明
        binding.tvSetPadding.setOnClickListener(v -> {
            getToolbar().showStatusBar();//设置显示状态栏  默认透明
        });
        binding.tvSetPaddingColor.setOnClickListener(v -> {
            getToolbar().showStatusBar(ContextCompat.getColor(this, R.color.colorAccent)); //可修改状态栏显色
        });
        binding.mBaseToolbar.setTitle("cesss");
    }

    @Override
    public void showError(Throwable throwable) {

    }
}
