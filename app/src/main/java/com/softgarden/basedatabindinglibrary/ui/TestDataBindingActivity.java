package com.softgarden.basedatabindinglibrary.ui;

import com.softgarden.basedatabindinglibrary.R;
import com.softgarden.basedatabindinglibrary.databinding.ActivityTestMvpBinding;
import com.softgarden.baselibrary.base.databinding.DataBindingActivity;
import com.softgarden.baselibrary.widget.CommonToolbar;

public class TestDataBindingActivity extends DataBindingActivity<ActivityTestMvpBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_mvp;
    }

    @Override
    protected CommonToolbar setToolbar() {
        return new CommonToolbar.Builder().setTitle("通用的toolbar").build(this);
    }

    @Override
    protected void initialize() {
        binding.tvText.setText("此Activity 可用于简单页面，利用databinding进行数据绑定");
        binding.tvText.setOnClickListener(v -> {});




    }

    @Override
    public void showError(Throwable throwable) {

    }
}
