package com.softgarden.basedatabindinglibrary.ui;

import android.app.Activity;
import android.view.MotionEvent;

import com.aitangba.swipeback.SwipeBackHelper;
import com.softgarden.basedatabindinglibrary.R;
import com.softgarden.basedatabindinglibrary.databinding.ActivitySwipeBackBinding;
import com.softgarden.baselibrary.base.databinding.DataBindingActivity;
import com.softgarden.baselibrary.widget.CommonToolbar;

/**
 * 可侧滑退出的activity
 * 继承该activity 或者实现具体方法即可方法
 */
public class SwipeBackActivity extends DataBindingActivity<ActivitySwipeBackBinding> implements SwipeBackHelper.SlideBackManager {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_swipe_back;
    }

    @Override
    protected CommonToolbar setToolbar() {
        return null;
    }

    @Override
    protected void initialize() {
        binding.tvText.setText("可侧滑退出的activity\n" +
                "  继承该activity 或者实现具体方法即可");
    }

    protected SwipeBackHelper mSwipeBackHelper;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mSwipeBackHelper == null) {
            mSwipeBackHelper = new SwipeBackHelper(this);
        }
        return mSwipeBackHelper.processTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    @Override
    public Activity getSlideActivity() {
        return this;
    }

    @Override
    public boolean supportSlideBack() {
        return true;
    }

    @Override
    public boolean canBeSlideBack() {
        return true;
    }

    @Override
    public void finish() {
        if (mSwipeBackHelper != null) {
            mSwipeBackHelper.finishSwipeImmediately();
            mSwipeBackHelper = null;
        }
        super.finish();
    }

    @Override
    public void showError(Throwable throwable) {

    }
}
