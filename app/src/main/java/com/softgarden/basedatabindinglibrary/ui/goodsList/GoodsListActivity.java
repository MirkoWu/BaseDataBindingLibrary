package com.softgarden.basedatabindinglibrary.ui.goodsList;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.softgarden.basedatabindinglibrary.BR;
import com.softgarden.basedatabindinglibrary.R;
import com.softgarden.basedatabindinglibrary.bean.GoodsBean;
import com.softgarden.basedatabindinglibrary.refresh.RefreshActivity;
import com.softgarden.basedatabindinglibrary.ui.SwipeBackActivity;
import com.softgarden.baselibrary.base.databinding.DataBindingAdapter;
import com.softgarden.baselibrary.databinding.LayoutRecyclerviewBinding;
import com.softgarden.baselibrary.widget.ColorDividerDecoration;
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
        /**
         * 关于Adapter 的定义 内容较少 的可以直接new
         * 逻辑较复杂的建议单写一个Adapter 不要所有东西全放到xml中
         */
        goodsAdapter = new DataBindingAdapter<GoodsBean>(R.layout.item_goods, BR.goods);
        binding.mRecyclerView.addItemDecoration(new ColorDividerDecoration(getActivity()));
        binding.mRecyclerView.setAdapter(goodsAdapter);
        goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

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
        setLoadMore(binding.mRecyclerView, goodsAdapter, list);
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        loadData();
    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        loadData();
    }

}
