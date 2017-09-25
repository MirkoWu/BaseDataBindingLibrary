package com.softgarden.basedatabindinglibrary.ui;

import com.softgarden.basedatabindinglibrary.R;
import com.softgarden.basedatabindinglibrary.databinding.ActivityTestBaseBinding;
import com.softgarden.baselibrary.base.databinding.DataBindingActivity;
import com.softgarden.baselibrary.widget.CommonToolbar;

public class TestBaseActivity extends DataBindingActivity<ActivityTestBaseBinding> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_base;
    }

    @Override
    protected CommonToolbar setToolbar() {
        return new CommonToolbar.Builder().build(this);
    }

    @Override
    protected void initialize() {
        binding.tvText.setText("此类Activity,只需继承DataBindingActivity，用于简单的界面");
    }

    @Override
    public void showError(Throwable throwable) {

    }
}
