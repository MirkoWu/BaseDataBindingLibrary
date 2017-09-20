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


    public static class Builder {
        private CharSequence title, leftStr, rightStr;
        private int titleResId, backgroundColorResId, leftImgResId = -1, leftStrResId, rightImgResId, rightStrResId;
        private OnClickListener leftOnClickListener;
        private OnClickListener rightOnClickListener;

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
            if (backgroundColorResId > 0) toolbar.setToolbarBackgroundColor(backgroundColorResId);
            //left
            if (TextUtils.isEmpty(leftStr)) {
                if (leftStrResId > 0) toolbar.showTextLeft(leftStrResId, leftOnClickListener);
            } else toolbar.showTextLeft(leftStr, leftOnClickListener);

            //default backbuttton
            toolbar.setBackButton(R.mipmap.back);
            if (leftImgResId >= 0) toolbar.setBackButton(leftImgResId);

            //right
            if (TextUtils.isEmpty(rightStr)) {
                if (rightStrResId > 0) toolbar.showTextRight(rightStrResId, rightOnClickListener);
            } else toolbar.showTextRight(rightStr, rightOnClickListener);
            if (rightImgResId > 0) toolbar.showImageRight(rightImgResId, rightOnClickListener);
            //title
            if (TextUtils.isEmpty(title)) {
                if (titleResId > 0) toolbar.setToolbarTitle(titleResId);
                else toolbar.setToolbarTitle(null);
            } else toolbar.setToolbarTitle(title);

            System.out.println("CommonToolbar.Builder.build");
            return toolbar;
        }
    }


    /**
     * 设置返回按钮,image
     */
    public void setBackButton(@DrawableRes int imageViewId) {
        if (imageViewId == 0) {
            img_toolbar_back_button.setVisibility(View.GONE);
            return;
        }
        img_toolbar_back_button.setVisibility(View.VISIBLE);
        img_toolbar_back_button.setImageResource(imageViewId);
        img_toolbar_back_button.setOnClickListener(v ->
                ((Activity) getContext()).onBackPressed());
    }

    /**
     * 显示文本菜单按钮
     */
    public void showTextLeft(CharSequence content, OnClickListener listener) {
        tv_toolbar_menu_left.setVisibility(View.VISIBLE);
        tv_toolbar_menu_left.setText(content);
        tv_toolbar_menu_left.setOnClickListener(listener);
    }

    public void showTextLeft(@StringRes int content, OnClickListener listener) {
        tv_toolbar_menu_left.setVisibility(View.VISIBLE);
        tv_toolbar_menu_left.setText(content);
        tv_toolbar_menu_left.setOnClickListener(listener);
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
        tv_toolbar_menu_right.setVisibility(View.VISIBLE);
        tv_toolbar_menu_right.setText(content);
        tv_toolbar_menu_right.setOnClickListener(listener);
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
        img_toolbar_back_button.setVisibility(View.INVISIBLE);
    }

    /**
     * 隐藏文本菜单按钮
     */
    public void hideTextMenu_left() {
        tv_toolbar_menu_left.setVisibility(View.INVISIBLE);
    }

    /**
     * 隐藏文本菜单按钮
     */
    public void hideTextMenu_right() {
        tv_toolbar_menu_right.setVisibility(View.INVISIBLE);
    }

    /**
     * 隐藏图形菜单按钮
     */
    public void hideImageMenu_right() {
        img_toolbar_menu_right.setVisibility(View.INVISIBLE);
    }


    /**
     * 设置标题
     * 在使用Toolbar时，如果需要修改标题必须在onCreate()方法执行完成之后修改。
     * 因为在onCreate()方法中设置任何标题值都会被重置为AndroidManifest中android:lable的值。
     * 为了抵消这种行为，我们可以在onCreate()执行之后执行的onPostCreate()方法中执行修改标题的。
     * <p>
     * 不过我直接修改了setTitle()方法名 感觉是和toolbar的setTitle冲突了
     *
     * @param title
     */

    @Override
    public void setTitle(CharSequence title) {
        tv_toolbar_title.setText(title);
    }

    @Override
    public void setTitle(int title) {
        tv_toolbar_title.setText(title);
    }

    @Override
    public CharSequence getTitle() {
        return tv_toolbar_title.getText().toString();
    }

    public void setToolbarTitle(CharSequence title) {
        tv_toolbar_title.setText(title);
    }


    public void setToolbarTitle(@StringRes int title) {
        tv_toolbar_title.setText(title);
    }

    public void setToolbarTitleColor(@ColorRes int color) {
        tv_toolbar_title.setTextColor(ContextCompat.getColor(getContext(), color));
    }

    public void setToolbarBackgroundColor(@ColorRes int colorResId) {
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


    //开放get方法
    public CharSequence getToolbarTitle() {
        return tv_toolbar_title.getText().toString();
    }


    public TextView getRightTextView() {
        return tv_toolbar_menu_right;
    }

    public ImageView getRightImageView() {
        return img_toolbar_menu_right;
    }

    public ImageView getLeftImgageView() {
        return img_toolbar_back_button;
    }

    public TextView getLeftTextView() {
        return tv_toolbar_menu_left;
    }

}
