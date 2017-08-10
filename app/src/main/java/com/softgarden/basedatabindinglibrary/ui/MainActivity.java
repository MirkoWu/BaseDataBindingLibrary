package com.softgarden.basedatabindinglibrary.ui;

import com.softgarden.basedatabindinglibrary.R;
import com.softgarden.basedatabindinglibrary.bean.TestBean;
import com.softgarden.basedatabindinglibrary.databinding.ActivityMainBinding;
import com.softgarden.basedatabindinglibrary.ui.goodsList.GoodsListActivity;
import com.softgarden.baselibrary.base.databinding.DataBindingActivity;
import com.softgarden.baselibrary.widget.CommonToolbar;

public class MainActivity extends DataBindingActivity<ActivityMainBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected CommonToolbar setToolbar() {
        return new CommonToolbar.Builder().setBackButton(0).setTitle(R.string.app_name).build(this);
    }

    @Override
    protected void initialize() {
        binding.setTest(new TestBean("测试"));
        binding.setTest(new TestBean("seses"));
        binding.tvHello.setText("");

        binding.tvList.setOnClickListener(v -> openActivity(GoodsListActivity.class));
    }
}
