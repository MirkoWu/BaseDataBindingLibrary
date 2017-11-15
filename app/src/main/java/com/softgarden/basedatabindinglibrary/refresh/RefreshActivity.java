package com.softgarden.basedatabindinglibrary.refresh;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.softgarden.basedatabindinglibrary.app.Constants;
import com.softgarden.baselibrary.base.BaseActivity;
import com.softgarden.baselibrary.base.IBasePresenter;
import com.softgarden.baselibrary.widget.RefreshDelegateLayout;

import java.util.List;

/**
 * @author by DELL
 * @date on 2017/9/27
 * @describe 通用的列表刷新Activity
 */
public abstract class RefreshActivity<T extends IBasePresenter, B extends ViewDataBinding>
        extends BaseActivity<T, B> implements BaseQuickAdapter.RequestLoadMoreListener {

    RefreshDelegateLayout mRefreshLayout;
    protected int mPage = 1;

    /**
     * initialize 已被实现  需要调用super()
     */
    @Override
    protected void initialize() {
        mRefreshLayout = (RefreshDelegateLayout) findViewById(com.softgarden.baselibrary.R.id.mRefreshLayout);
        if (mRefreshLayout != null)
            mRefreshLayout.setOnRefreshDelegateListener(new RefreshDelegateLayout.OnRefreshDelegateListener() {
                @Override
                public void onRefresh() {
                    RefreshActivity.this.onRefresh();
                }
            });

    }

    public abstract void onRefresh();

    /**
     * 结束刷新
     */
    public void finishRefresh() {
        if (mRefreshLayout != null) mRefreshLayout.finishRefresh(0);
    }

    @Override
    public void showError(Throwable throwable) {
        super.showError(throwable);
        finishRefresh();
    }

    public void disableRefresh() {
        if (mRefreshLayout != null) mRefreshLayout.setEnableRefresh(false);
    }

    /**
     * 结束刷新
     * 上拉更多
     *
     * @param adapter
     * @param list
     */
    public void setLoadMore(RecyclerView recyclerView, BaseQuickAdapter adapter, List<?> list) {
        finishRefresh();
        if (mPage == 1) {
            adapter.setNewData(list);
        } else {
            adapter.addData(list);
        }

        if (list == null || list.size() < Constants.PAGE_COUNT) {
            adapter.loadMoreEnd();
        } else {
            adapter.setOnLoadMoreListener(this,recyclerView);

            adapter.loadMoreComplete();
        }
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
