package com.softgarden.basedatabindinglibrary.ui.goodsList;

import android.view.View;

import com.mirkowu.library.listener.OnViewClickListener;
import com.softgarden.basedatabindinglibrary.R;
import com.softgarden.basedatabindinglibrary.bean.GoodsBean;
import com.softgarden.basedatabindinglibrary.ui.RefreshActivity;
import com.softgarden.basedatabindinglibrary.ui.SwipeBackActivity;
import com.softgarden.baselibrary.base.databinding.DataBindingAdapter;
import com.softgarden.baselibrary.databinding.LayoutRecyclerviewBinding;
import com.softgarden.baselibrary.widget.CommonToolbar;

import java.util.List;

public class GoodsListActivity extends RefreshActivity<GoodsListPresenter, LayoutRecyclerviewBinding> implements GoodsListContract.Display {

    DataBindingAdapter<GoodsBean> goodsAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recyclerview;
    }

    @Override
    protected CommonToolbar setToolbar() {
        return new CommonToolbar.Builder()
                .setTitle(R.string.show_list)
                .showImageRight(R.mipmap.ic_launcher, v -> openActivity(SwipeBackActivity.class))
                .build(this);
    }

    @Override
    protected void initialize() {
        super.initialize();
        goodsAdapter = new DataBindingAdapter<GoodsBean>(R.layout.item_goods, com.softgarden.basedatabindinglibrary.BR.goods);
        binding.mRecyclerView.setAdapter(goodsAdapter);
        goodsAdapter.setOnViewClickListener(new OnViewClickListener<GoodsBean>() {
            @Override
            public void onViewClick(View view, GoodsBean data, int position) {

            }
        });

        loadData();
    }

    private void loadData() {
        mPresenter.goodsList(mPage, 10);
    }

    @Override
    public void goodsList(List<GoodsBean> list) {
        // finishRefresh();
        setLoadMore(goodsAdapter, list);
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        loadData();
    }

    @Override
    public void onLoadMore() {
        mPage++;
        loadData();
    }
}
