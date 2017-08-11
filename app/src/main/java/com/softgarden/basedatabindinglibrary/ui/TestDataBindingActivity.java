package com.softgarden.basedatabindinglibrary.ui;

import com.softgarden.basedatabindinglibrary.R;
import com.softgarden.basedatabindinglibrary.databinding.ActivityTestDataBindingBinding;
import com.softgarden.baselibrary.base.BaseContract;
import com.softgarden.baselibrary.base.BasePresenter;
import com.softgarden.baselibrary.base.databinding.DataBindingActivity;
import com.softgarden.baselibrary.widget.CommonToolbar;


public class TestDataBindingActivity extends DataBindingActivity<BasePresenter,ActivityTestDataBindingBinding> implements BaseContract.Display {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_data_binding;
    }

    @Override
    protected CommonToolbar setToolbar() {
        return new CommonToolbar.Builder()
                .setTitle("标题")//
                .showImageRight(R.mipmap.ic_launcher,v -> {})//显示右上角菜单
                .showTextRight("右上角",v -> {})//显示右上角菜单
                .showTextLeft("左上角",v -> {})//左上角文字  左上角图片 默认是返回 当然也可以设置其他的 看下面的
                .setBackButton(0)// 不行的时候默认显示返回按钮，当为0 时 隐藏返回按钮
                .setBackgroundColor(R.color.colorAccent)//设置背景色
                .build(this);
    }

    @Override
    protected void initialize() {
        //当然还可以这样
       // getToolbar().setToolbarTitle();//在这里记得要用setToolbarTitle   不要setTitle（）
       // getToolbar().setToolbarBackgroundColor();
//        getToolbar().showTextRight();
//        ....
       //如果这些还不满足 可以 这样 拿到你想要的控件自己设置
        getToolbar().getRightTextView();
        getToolbar().getLeftTextView();
        getToolbar().getLeftImgageView().setOnClickListener(v -> {});
        getToolbar().getRightImageView();

        //本toolbar只适用于一般的场景  特殊布局要自己写


    }
}
