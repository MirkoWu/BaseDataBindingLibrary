package com.softgarden.basedatabindinglibrary.ui;

import com.softgarden.basedatabindinglibrary.R;
import com.softgarden.basedatabindinglibrary.databinding.ActivityTestDataBindingBinding;
import com.softgarden.baselibrary.base.BaseContract;
import com.softgarden.baselibrary.base.BasePresenter;
import com.softgarden.baselibrary.base.BaseActivity;
import com.softgarden.baselibrary.widget.CommonToolbar;


public class TestMVPActivity extends BaseActivity<BasePresenter,ActivityTestDataBindingBinding> implements BaseContract.Display {

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
       // getToolbar().setToolbarTitle();//
       // getToolbar().setToolbarBackgroundColor();
//        getToolbar().showTextRight();
//        ....
       //如果这些还不满足 可以 这样 拿到你想要的控件自己设置
        getToolbar().getRightTextView();
        getToolbar().getLeftTextView();
        getToolbar().getLeftImageView().setOnClickListener(v -> {});
        getToolbar().getRightImageView();

        //本toolbar只适用于一般的场景  特殊布局要自己写

        binding.tvText.setText("这里做一个完整的 Databinding 和MVP 的演示步骤\n" +
                "\\n1.创建完Activity。\n" +
                "\\n2.在布局XML 跟布局写上 《layout》《/layout》 标签 即用此标签嵌套所有的布局。\n" +
                "\\n3.给Activity 创建 Contract 和 Presenter 。（2 ，3步骤随意）\n" +
                "\\n4.给Activity 添加泛型，即Activity《Presenter,ActivityBinding》 ，最后要注意 implments Contract.Display。\n" +
                "\\n其中的ActivityBinding命名规范是一定的 ，布局文件驼峰式，后面加上Binding,例：activity_main.xml  --》ActivityMainBinding.如果binding没有生成 需要编译一下\n" +
                "\\n5.实现各方法.\n" +
                "\\n6,有需要 加toolbar的，可以在setToolbar()方法 返回  new CommonToolbar.Builder().setTitle().showLeft().showRight()......build(this);\n");

    }
}
