package com.softgarden.basedatabindinglibrary.ui;

import com.softgarden.basedatabindinglibrary.R;
import com.softgarden.basedatabindinglibrary.bean.TestBean;
import com.softgarden.basedatabindinglibrary.databinding.ActivityMainBinding;
import com.softgarden.basedatabindinglibrary.ui.goodsList.GoodsListActivity;
import com.softgarden.baselibrary.base.BaseActivity;
import com.softgarden.baselibrary.base.BaseContract;
import com.softgarden.baselibrary.base.BasePresenter;
import com.softgarden.baselibrary.widget.CommonToolbar;

public class MainActivity extends BaseActivity<BasePresenter,ActivityMainBinding> implements BaseContract.Display{

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
        getToolbar().setTitle("改个标题");
        binding.setTest(new TestBean("测试"));
        binding.setTest(new TestBean("seses"));
        binding.tvHello.setText("");

        binding.tvList.setOnClickListener(v -> openActivity(GoodsListActivity.class));
        binding.tvBase.setOnClickListener(v -> openActivity(TestBaseActivity.class));
        binding.tvList.setOnClickListener(v -> openActivity(TestMVPActivity.class));
        binding.tvSwipeback.setOnClickListener(v -> openActivity(SwipeBackActivity.class));
    }
}
