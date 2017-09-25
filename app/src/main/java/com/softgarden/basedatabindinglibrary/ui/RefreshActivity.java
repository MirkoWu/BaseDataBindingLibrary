package com.softgarden.basedatabindinglibrary.ui;

import android.databinding.ViewDataBinding;

import com.mirkowu.library.BaseRVAdapter;
import com.mirkowu.library.listener.OnLoadMoreListener;
import com.softgarden.baselibrary.R;
import com.softgarden.baselibrary.base.BaseActivity;
import com.softgarden.baselibrary.base.IBasePresenter;
import com.softgarden.baselibrary.widget.RefreshDelegateLayout;

import java.util.List;

/**
 * Created by DELL on 2017/7/28.
 */

public abstract class RefreshActivity<T extends IBasePresenter, B extends ViewDataBinding> extends BaseActivity<T, B> implements OnLoadMoreListener {

    RefreshDelegateLayout mRefreshLayout;

    protected int mPage = 1;

    /**
     * initialize 已被实现  需要调用super()
     */
    @Override
    protected void initialize() {
        mRefreshLayout = (RefreshDelegateLayout) findViewById(R.id.mRefreshLayout);
        if (mRefreshLayout != null) {
            mRefreshLayout.setOnRefreshDelegateListener(new RefreshDelegateLayout.OnRefreshDelegateListener() {
                @Override
                public void onRefresh() {
                    RefreshActivity.this.onRefresh();
                }
            });
//            mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
//                @Override
//                public void onLoadmore(RefreshLayout refreshlayout) {
//
//                }
//            });
        }


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
    public void setLoadMore(BaseRVAdapter adapter, List<?> list) {
        finishRefresh();
        if (mPage == 1) adapter.setData(list);
        else adapter.addData(list);

        if (list == null || list.size() < 10) {
            adapter.loadMoreEnd();
        } else {
            adapter.setOnLoadMoreListener(this);
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void onLoadMore() {

    }
}
