package com.softgarden.baselibrary.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * Created by DELL on 2017/7/27.
 */

public class RefreshDelegateLayout extends SmartRefreshLayout {

    public RefreshDelegateLayout(Context context) {
        super(context);
        initView(context);
    }

    public RefreshDelegateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshDelegateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RefreshDelegateLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }


    private void initView(Context context) {
        this.setEnableAutoLoadmore(false);
        this.setDisableContentWhenLoading(true);
        this.setDisableContentWhenRefresh(true);
        this.setEnableLoadmore(false);

        this.setOnRefreshListener(new com.scwang.smartrefresh.layout.listener.OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (onRefreshDelegateListener != null)
                    onRefreshDelegateListener.onRefresh();
            }
        });
    }

    public void setRefreshDelegateComplete() {
        this.finishRefresh();
    }

    public OnRefreshDelegateListener onRefreshDelegateListener;

    public interface OnRefreshDelegateListener {
        void onRefresh();
    }

    public void setOnRefreshDelegateListener(OnRefreshDelegateListener onRefreshDelegateListener) {
        this.onRefreshDelegateListener = onRefreshDelegateListener;
    }
}
