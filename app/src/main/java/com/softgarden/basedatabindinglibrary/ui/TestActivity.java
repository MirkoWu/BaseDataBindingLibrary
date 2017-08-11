package com.softgarden.basedatabindinglibrary.ui;

import com.softgarden.basedatabindinglibrary.R;
import com.softgarden.basedatabindinglibrary.bean.GoodsBean;
import com.softgarden.basedatabindinglibrary.databinding.ActivityTestBinding;
import com.softgarden.basedatabindinglibrary.ui.goodsList.GoodsListContract;
import com.softgarden.basedatabindinglibrary.ui.goodsList.GoodsListPresenter;
import com.softgarden.baselibrary.base.databinding.DataBindingActivity;
import com.softgarden.baselibrary.widget.CommonToolbar;

import java.util.List;

public class TestActivity extends DataBindingActivity<GoodsListPresenter,ActivityTestBinding> implements GoodsListContract.Display{



    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected CommonToolbar setToolbar() {
        return null;
    }

    @Override
    protected void initialize() {
        binding.button.setText("");
        binding.textView.setText("");
        binding.textView.setOnClickListener(v -> {});

        mPresenter.goodsList(1,1);



    }

    @Override
    public void goodsList(List<GoodsBean> list) {

    }
}
