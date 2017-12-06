package com.softgarden.baselibrary.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.softgarden.baselibrary.R;

import java.lang.reflect.Field;

/**
 * 通用的toolbar
 */

public class CommonToolbar extends Toolbar {
    private View mStatusBar;//状态栏
    private View mSplitLine;//状态栏分割线
    private LinearLayout mRootView;//根部局
    private AppCompatTextView mTitleTextView;//标题
    private AppCompatTextView mLeftTextView, mRightTextView;//左右文字菜单
    private AppCompatImageView mLeftImageView, mRightImageView;//左右图片菜单

    public CommonToolbar(Context context) {
        this(context, null);
    }

    public CommonToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {

        //toolbar默认marginLeft ，所以定位到(0,0) 防止偏移
        this.setContentInsetsAbsolute(0, 0);

        View view = inflate(context, R.layout.layout_common_toolbar, this);
        mStatusBar = view.findViewById(R.id.mStatusBar);
        mSplitLine = view.findViewById(R.id.mSplitLine);
        mRootView = (LinearLayout) view.findViewById(R.id.layout_toolbar);
        mTitleTextView = (AppCompatTextView) view.findViewById(R.id.mTitleTextView);
        mLeftTextView = (AppCompatTextView) view.findViewById(R.id.mLeftTextView);
        mRightTextView = (AppCompatTextView) view.findViewById(R.id.mRightTextView);
        mLeftImageView = (AppCompatImageView) view.findViewById(R.id.mLeftImageView);
        mRightImageView = (AppCompatImageView) view.findViewById(R.id.mRightImageView);
    }

    /**
     * 根部局
     *
     * @return
     */
    public ViewGroup getRootView() {
        return mRootView;
    }


    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    public int getStatusBarHeight() {
        try {
            Class c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * 显示状态栏 ((此功能需配合沉浸式API>=19才会生效))
     * 默认为透明，保持和toolbar一样的颜色
     */
    public void showStatusBar() {
        showStatusBar(Color.TRANSPARENT);
    }


    /**
     * 显示状态栏 (此功能需配合沉浸式API>=19才会生效)
     *
     * @param colorId
     */
    public void showStatusBar(@ColorInt int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//android 19 4.4 以上才支持沉浸式
            if (getContext() instanceof Activity) {//设置 沉浸式 flag
                ((Activity) getContext()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            mStatusBar.setVisibility(VISIBLE);
            mStatusBar.setBackgroundColor(colorId);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mStatusBar.getLayoutParams();
            params.height = getStatusBarHeight();
        }
    }

    /**
     * 隐藏 statusBar
     */
    public void hideStatusBar() {
        mStatusBar.setVisibility(GONE);
    }

    /**
     * toolbar 和布局的分割线
     *
     * @param colorId
     * @param height
     */
    public void showSplitLine(@ColorInt int colorId, int height) {
        mSplitLine.setVisibility(VISIBLE);
        mSplitLine.setBackgroundColor(colorId);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mSplitLine.getLayoutParams();
        params.height = height;
    }

    /**
     * 隐藏 statusBar
     */
    public void hideSplitLine() {
        mSplitLine.setVisibility(GONE);
    }


    /**
     * 设置返回按钮,
     * image 将资源id设置 <= 0 的值就可以隐藏返回键
     */
    public void showBackButton(@DrawableRes int resId) {
        if (resId <= 0) {
            mLeftImageView.setVisibility(GONE);
            return;
        }
        showImageLeft(resId, new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getContext() instanceof Activity)
                    ((Activity) getContext()).onBackPressed();//调用activity的返回键
            }
        });
    }


    /**
     * 显示图形菜单按钮,右边
     */
    public void showImageLeft(@DrawableRes int resId, OnClickListener listener) {
        mLeftImageView.setImageResource(resId);
        mLeftImageView.setVisibility(VISIBLE);
        mLeftImageView.setOnClickListener(listener);
    }

    /**
     * 显示文本菜单按钮
     */
    public void showTextLeft(CharSequence text, OnClickListener listener) {
        mLeftTextView.setText(text);
        mLeftTextView.setVisibility(VISIBLE);
        mLeftTextView.setOnClickListener(listener);
    }

    public void showTextLeft(@StringRes int resId, OnClickListener listener) {
        showTextLeft(getContext().getText(resId), listener);
    }

    /**
     * 显示文本菜单按钮
     *
     * @param text
     * @param listener
     */
    public void showTextRight(CharSequence text, OnClickListener listener) {
        mRightTextView.setText(text);
        mRightTextView.setVisibility(VISIBLE);
        mRightTextView.setOnClickListener(listener);
    }

    public void showTextRight(@StringRes int text, OnClickListener listener) {
        showTextRight(getContext().getText(text), listener);
    }

    /**
     * 显示图形菜单按钮,右边
     */
    public void showImageRight(@DrawableRes int resId, OnClickListener listener) {
        mRightImageView.setImageResource(resId);
        mRightImageView.setVisibility(VISIBLE);
        mRightImageView.setOnClickListener(listener);
    }

    /**
     * 隐藏返回按钮
     */
    public void hideBackButton() {
        mLeftImageView.setVisibility(GONE);
    }

    /**
     * 隐藏文本菜单按钮
     */
    public void hideTextLeft() {
        mLeftTextView.setVisibility(GONE);
    }

    /**
     * 隐藏文本菜单按钮
     */
    public void hideTextRight() {
        mRightTextView.setVisibility(GONE);
    }

    /**
     * 隐藏图形菜单按钮
     */
    public void hideImageRight() {
        mRightImageView.setVisibility(GONE);
    }


    /**
     * 设置 titleText
     *
     * @param titleText
     */
    @Override
    public void setTitle(CharSequence titleText) {
        mTitleTextView.setText(titleText);
    }

    @Override
    public void setTitle(@StringRes int resId) {
        setTitle(getContext().getText(resId));
    }

    /**
     * 获取title
     *
     * @return
     */
    @Override
    public CharSequence getTitle() {
        return mTitleTextView.getText();
    }

    /**
     * 设置标题 颜色
     *
     * @param colorId
     */
    @Override
    public void setTitleTextColor(@ColorInt int colorId) {
        mTitleTextView.setTextColor(colorId);
    }

    /**
     * 设置文本菜单的颜色
     *
     * @param colorId
     */
    public void setMenuTextColor(@ColorInt int colorId) {
        mLeftTextView.setTextColor(colorId);
        mRightTextView.setTextColor(colorId);
    }

    /**
     * 统一设置文字的颜色
     *
     * @param colorId
     */
    public void setAllTextColor(@ColorInt int colorId) {
        mLeftTextView.setTextColor(colorId);
        mRightTextView.setTextColor(colorId);
        mTitleTextView.setTextColor(colorId);
    }


    /**
     * 设置整个toolbar背景颜色
     *
     * @param colorId
     */
    @Override
    public void setBackgroundColor(@ColorInt int colorId) {
        mRootView.setBackgroundColor(colorId);
    }

    @Override
    public void setBackgroundResource(@DrawableRes int resId) {
        mRootView.setBackgroundResource(resId);
    }


    public TextView getTitleTextView() {
        return mTitleTextView;
    }

    public TextView getLeftTextView() {
        return mLeftTextView;
    }

    public TextView getRightTextView() {
        return mRightTextView;
    }

    public ImageView getLeftImageView() {
        return mLeftImageView;
    }

    public ImageView getRightImageView() {
        return mRightImageView;
    }


    /**
     * builder 模式
     */
    public static class Builder {
        private CharSequence titleText, leftText, rightText;
        private int titleTextResId, leftImgResId, leftTextResId, rightImgResId, rightTextResId;
        private int backResId;
        private int statusBarColorId = Color.TRANSPARENT, backgroundColorId = Color.BLUE,
                allTextColorId = Color.BLACK, titleColorId = Color.BLACK;//均设置默认值
        private OnClickListener leftOnClickListener, rightOnClickListener;

        /**
         * 优先级比 showImageLeft 低
         *
         * @param backResId
         * @return
         */
        public Builder setBackButton(@DrawableRes int backResId) {
            this.backResId = backResId;
            return this;
        }

        public Builder showImageLeft(@DrawableRes int leftImgResId, OnClickListener leftOnClickListener) {
            this.leftImgResId = leftImgResId;
            this.leftOnClickListener = leftOnClickListener;
            return this;
        }

        public Builder showImageRight(@DrawableRes int rightImgResId, OnClickListener rightOnClickListener) {
            this.rightImgResId = rightImgResId;
            this.rightOnClickListener = rightOnClickListener;
            return this;
        }

        public Builder showTextLeft(CharSequence leftText, OnClickListener leftOnClickListener) {
            this.leftText = leftText;
            this.leftOnClickListener = leftOnClickListener;
            return this;
        }

        public Builder showTextLeft(@StringRes int leftTextResId, OnClickListener leftOnClickListener) {
            this.leftTextResId = leftTextResId;
            this.leftOnClickListener = leftOnClickListener;
            return this;
        }

        public Builder showTextRight(CharSequence rightText, OnClickListener rightOnClickListener) {
            this.rightText = rightText;
            this.rightOnClickListener = rightOnClickListener;
            return this;
        }

        public Builder showTextRight(@StringRes int rightTextResId, OnClickListener rightOnClickListener) {
            this.rightTextResId = rightTextResId;
            this.rightOnClickListener = rightOnClickListener;
            return this;
        }


        public Builder setTitle(CharSequence titleText) {
            this.titleText = titleText;
            return this;
        }

        public Builder setTitle(@StringRes int titleTextResId) {
            this.titleTextResId = titleTextResId;
            return this;
        }

        /**
         * setTitleTextColor  和 setAllTextColor 顺序 按最新设置的那个算
         *
         * @param titleColorId
         * @return
         */
        public Builder setTitleTextColor(@ColorInt int titleColorId) {
            this.titleColorId = titleColorId;
            return this;
        }

        public Builder setAllTextColor(@ColorInt int allTextColorId) {
            this.allTextColorId = allTextColorId;
            this.titleColorId = allTextColorId;
            return this;
        }

        public Builder setBackgroundColor(@ColorInt int backgroundColorId) {
            this.backgroundColorId = backgroundColorId;
            return this;
        }

        public Builder showStatusBar(@ColorInt int statusBarColorId) {
            this.statusBarColorId = statusBarColorId;
            return this;
        }


        public CommonToolbar build(Context context) {
            CommonToolbar toolbar = new CommonToolbar(context);

            /*** 默认隐藏 也可以将backResId 设置一个默认值*/
            toolbar.showBackButton(backResId);

            /*** leftMenu */
            if (TextUtils.isEmpty(leftText)) {
                if (leftTextResId > 0) toolbar.showTextLeft(leftTextResId, leftOnClickListener);
            } else toolbar.showTextLeft(leftText, leftOnClickListener);

            if (leftImgResId > 0) toolbar.showImageRight(leftImgResId, leftOnClickListener);

            /*** rightMenu */
            if (TextUtils.isEmpty(rightText)) {
                if (rightTextResId > 0) toolbar.showTextRight(rightTextResId, rightOnClickListener);
            } else toolbar.showTextRight(rightText, rightOnClickListener);

            if (rightImgResId > 0) toolbar.showImageRight(rightImgResId, rightOnClickListener);

            /*** titleText */
            if (TextUtils.isEmpty(titleText)) {
                if (titleTextResId > 0) toolbar.setTitle(titleTextResId);
                else toolbar.setTitle(null);
            } else toolbar.setTitle(titleText);


            toolbar.showStatusBar(statusBarColorId);

            toolbar.setBackgroundColor(backgroundColorId);

            toolbar.setAllTextColor(allTextColorId);

            toolbar.setTitleTextColor(titleColorId);

            return toolbar;
        }
    }

}
