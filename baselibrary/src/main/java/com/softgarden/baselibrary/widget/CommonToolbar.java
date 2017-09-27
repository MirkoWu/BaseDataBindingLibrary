package com.softgarden.baselibrary.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.softgarden.baselibrary.R;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/10/10 0010.
 */

public class CommonToolbar extends Toolbar {
    private RelativeLayout layout_toolbar;
    private ImageView img_toolbar_back_button;
    private TextView tv_toolbar_menu_left;
    private AppCompatTextView tv_toolbar_title;
    private ImageView img_toolbar_menu_right;
    private TextView tv_toolbar_menu_right;
    private boolean showSplitLine = true;//是否显示分割线

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
        this.setContentInsetsAbsolute(0, 0);//toolbar默认marginleft ，所以定位到（0,0）

        View rootView = inflate(context, R.layout.layout_common_toolbar, this);

        layout_toolbar = (RelativeLayout) rootView.findViewById(R.id.layout_toolbar);
        tv_toolbar_title = (AppCompatTextView) rootView.findViewById(R.id.tv_toolbar_title);
        img_toolbar_back_button = (ImageView) rootView.findViewById(R.id.img_toolbar_back_button);
        img_toolbar_menu_right = (ImageView) rootView.findViewById(R.id.img_toolbar_menu_right);
        tv_toolbar_menu_left = (TextView) rootView.findViewById(R.id.tv_toolbar_menu_left);
        tv_toolbar_menu_right = (TextView) rootView.findViewById(R.id.tv_toolbar_menu_right);

    }

    public View getRootView() {
        return layout_toolbar;
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
     * 设置返回按钮,image
     */
    public void setBackButton(@DrawableRes int imageViewId) {
        if (imageViewId == 0) {
            img_toolbar_back_button.setVisibility(View.INVISIBLE);
            return;
        }
        img_toolbar_back_button.setVisibility(View.VISIBLE);
        img_toolbar_back_button.setImageResource(imageViewId);
        img_toolbar_back_button.setOnClickListener(v ->
                ((Activity) getContext()).onBackPressed());
    }

    /**
     * 显示图形菜单按钮,右边
     */
    public void showImageLeft(@DrawableRes int resId, OnClickListener listener) {
        img_toolbar_back_button.setVisibility(View.VISIBLE);
        img_toolbar_back_button.setImageResource(resId);
        img_toolbar_back_button.setOnClickListener(listener);
    }

    /**
     * 显示文本菜单按钮
     */
    public void showTextLeft(CharSequence content, OnClickListener listener) {
        tv_toolbar_menu_left.setVisibility(View.VISIBLE);
        tv_toolbar_menu_left.setText(content);
        tv_toolbar_menu_left.setOnClickListener(listener);
    }

    public void showTextLeft(@StringRes int resId, OnClickListener listener) {
        showTextLeft(getContext().getText(resId), listener);
    }

    /**
     * 显示文本菜单按钮
     *
     * @param content
     * @param listener
     */
    public void showTextRight(CharSequence content, OnClickListener listener) {
        tv_toolbar_menu_right.setVisibility(View.VISIBLE);
        tv_toolbar_menu_right.setText(content);
        tv_toolbar_menu_right.setOnClickListener(listener);
    }

    public void showTextRight(@StringRes int content, OnClickListener listener) {
        showTextRight(getContext().getText(content), listener);
    }

    /**
     * 显示图形菜单按钮,右边
     */
    public void showImageRight(@DrawableRes int resId, OnClickListener listener) {
        img_toolbar_menu_right.setVisibility(View.VISIBLE);
        img_toolbar_menu_right.setImageResource(resId);
        img_toolbar_menu_right.setOnClickListener(listener);
    }

    /**
     * 隐藏返回按钮
     */
    public void hideBackButton() {
        img_toolbar_back_button.setVisibility(View.GONE);
    }

    /**
     * 隐藏文本菜单按钮
     */
    public void hideTextLeft() {
        tv_toolbar_menu_left.setVisibility(View.GONE);
    }

    /**
     * 隐藏文本菜单按钮
     */
    public void hideTextRight() {
        tv_toolbar_menu_right.setVisibility(View.GONE);
    }

    /**
     * 隐藏图形菜单按钮
     */
    public void hideImageRight() {
        img_toolbar_menu_right.setVisibility(View.GONE);
    }


    /**
     * 设置 title
     *
     * @param title
     */
    @Override
    public void setTitle(CharSequence title) {
        tv_toolbar_title.setText(title);
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
        return tv_toolbar_title.getText();
    }

    /**
     * 设置title 颜色
     *
     * @param color
     */
    @Override
    public void setTitleTextColor(@ColorRes int color) {
        tv_toolbar_title.setTextColor(ContextCompat.getColor(getContext(), color));
    }

    /**
     * 设置整个toolbar背景色
     *
     * @param colorResId
     */
    @Override
    public void setBackgroundColor(@ColorRes int colorResId) {
        layout_toolbar.setBackgroundColor(ContextCompat.getColor(getContext(), colorResId));
    }

    public void setStatusBarPadding() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//android 19 4.4 以上才支持沉浸式
            int statusBarHeight = getStatusBarHeight();
            LayoutParams params = (LayoutParams) layout_toolbar.getLayoutParams();
            params.height = params.height + statusBarHeight;
            layout_toolbar.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    public TextView getTitleTextView() {
        return tv_toolbar_title;
    }

    public TextView getRightTextView() {
        return tv_toolbar_menu_right;
    }

    public ImageView getRightImageView() {
        return img_toolbar_menu_right;
    }

    public ImageView getLeftImageView() {
        return img_toolbar_back_button;
    }

    public TextView getLeftTextView() {
        return tv_toolbar_menu_left;
    }


    /**
     * builder 模式
     */
    public static class Builder {
        private CharSequence title, leftStr, rightStr;
        private int titleResId, backgroundColorResId, leftImgResId, leftStrResId, rightImgResId, rightStrResId;
        private OnClickListener leftOnClickListener, rightOnClickListener;

        public Builder setBackButton(@DrawableRes int leftImgResId) {
            this.leftImgResId = leftImgResId;
            return this;
        }

        public Builder showImageRight(@DrawableRes int rightImgResId, OnClickListener rightOnClickListener) {
            this.rightImgResId = rightImgResId;
            this.rightOnClickListener = rightOnClickListener;
            return this;
        }

        public Builder showTextLeft(CharSequence leftStr, OnClickListener leftOnClickListener) {
            this.leftStr = leftStr;
            this.leftOnClickListener = leftOnClickListener;
            return this;
        }

        public Builder showTextLeft(@StringRes int leftStrResId, OnClickListener leftOnClickListener) {
            this.leftStrResId = leftStrResId;
            this.leftOnClickListener = leftOnClickListener;
            return this;
        }

        public Builder showTextRight(CharSequence rightStr, OnClickListener rightOnClickListener) {
            this.rightStr = rightStr;
            this.rightOnClickListener = rightOnClickListener;
            return this;
        }

        public Builder showTextRight(@StringRes int rightStrResId, OnClickListener rightOnClickListener) {
            this.rightStrResId = rightStrResId;
            this.rightOnClickListener = rightOnClickListener;
            return this;
        }


        public Builder setTitle(CharSequence title) {
            this.title = title;
            return this;
        }

        public Builder setTitle(@StringRes int titleResId) {
            this.titleResId = titleResId;
            return this;
        }

        public Builder setBackgroundColor(@ColorRes int backgroundColorResId) {
            this.backgroundColorResId = backgroundColorResId;
            return this;
        }


        public CommonToolbar build(Activity activity) {
            CommonToolbar toolbar = new CommonToolbar(activity);
            //setBackgroundColor
            if (backgroundColorResId > 0) toolbar.setBackgroundColor(backgroundColorResId);
            //default backbuttton
            toolbar.setBackButton(R.mipmap.back);
            //left
            if (TextUtils.isEmpty(leftStr)) {
                if (leftStrResId > 0) toolbar.showTextLeft(leftStrResId, leftOnClickListener);
            } else toolbar.showTextLeft(leftStr, leftOnClickListener);
            if (leftImgResId > 0) toolbar.setBackButton(leftImgResId);
            //right
            if (TextUtils.isEmpty(rightStr)) {
                if (rightStrResId > 0) toolbar.showTextRight(rightStrResId, rightOnClickListener);
            } else toolbar.showTextRight(rightStr, rightOnClickListener);
            if (rightImgResId > 0) toolbar.showImageRight(rightImgResId, rightOnClickListener);
            //title
            if (TextUtils.isEmpty(title)) {
                if (titleResId > 0) toolbar.setTitle(titleResId);
                else toolbar.setTitle(null);
            } else toolbar.setTitle(title);

            System.out.println("CommonToolbar.Builder.build");
            return toolbar;
        }
    }

}
