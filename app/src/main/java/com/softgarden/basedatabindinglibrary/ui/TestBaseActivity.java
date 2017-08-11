package com.softgarden.basedatabindinglibrary.ui;

import com.softgarden.basedatabindinglibrary.R;
import com.softgarden.baselibrary.base.BaseActivity;
import com.softgarden.baselibrary.widget.CommonToolbar;

public class TestBaseActivity extends BaseActivity {


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

    }
}
