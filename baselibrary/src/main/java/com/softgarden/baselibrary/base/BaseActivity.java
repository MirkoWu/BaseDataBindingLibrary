package com.softgarden.baselibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.softgarden.baselibrary.BuildConfig;
import com.softgarden.baselibrary.R;
import com.softgarden.baselibrary.utils.InstanceUtil;
import com.softgarden.baselibrary.utils.ToastUtil;
import com.softgarden.baselibrary.widget.CommonToolbar;
import com.softgarden.baselibrary.widget.LoadingDialog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.reflect.ParameterizedType;


/**
 * MVP Activity 的基类
 */

public abstract class BaseActivity<T extends IBasePresenter> extends RxAppCompatActivity implements IBaseDisplay {

    public final String TAG = getClass().getSimpleName();
    public static final int RESULT_LOGIN = 0x00001234;

    private boolean mNightMode;
    protected T mPresenter;

    protected CommonToolbar commonToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        initPresenter();
        initialize();
//        mNightMode = (boolean) SPUtils.get(MODE_NIGHT, false);
//        if (mNightMode) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }


    protected void initContentView() {
        commonToolbar = setToolbar();
        if (commonToolbar != null) {
            //添加标题栏
            LinearLayout view = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(params);
            view.setOrientation(LinearLayout.VERTICAL);
            view.setFitsSystemWindows(true);
            view.addView(commonToolbar);
            view.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            view.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.toolbar_line));

            View contentView = getLayoutInflater().inflate(getLayoutId(), view, false);

            view.addView(contentView);
            setContentView(view);
            setSupportActionBar(commonToolbar);//这里将toolbar设置为actionbar
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } else {
            setContentView(getLayoutId());
        }
    }

    protected void initPresenter() {
        if (this instanceof IBaseDisplay &&
                this.getClass().getGenericSuperclass() instanceof ParameterizedType &&
                ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments().length > 0) {
            Class mPresenterClass = (Class) ((ParameterizedType) (this.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[0];//获取Presenter的class
            mPresenter = InstanceUtil.getInstance(mPresenterClass);
            if (mPresenter != null) mPresenter.attachView(this);
        }
    }

    public CommonToolbar getToolbar() {
        return commonToolbar;
    }

    @Override
    public void useNightMode(boolean isNight) {
        //       boolean night = (boolean) SPUtils.get(MODE_NIGHT, false);
//        if (night) {
//            SPUtils.put(MODE_NIGHT, false);
//            AppCompatDelegate.setDefaultNightMode(
//                    AppCompatDelegate.MODE_NIGHT_NO);
//        } else {
//            SPUtils.put(MODE_NIGHT, true);
//            AppCompatDelegate.setDefaultNightMode(
//                    AppCompatDelegate.MODE_NIGHT_YES);
//        }
//        // recreate();
//        reload();
    }


    @Override
    protected void onResume() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();

//        //检查日夜模式
//        boolean night = (boolean) SPUtils.get(MODE_NIGHT, false);
//        if (night != mNightMode) {
//            if (night) {
//                AppCompatDelegate.setDefaultNightMode(
//                        AppCompatDelegate.MODE_NIGHT_YES);
//            } else {
//                AppCompatDelegate.setDefaultNightMode(
//                        AppCompatDelegate.MODE_NIGHT_NO);
//            }
//            recreate();
//            // reload();
//        }
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void openActivity(Class<? extends Activity> cls) {
        this.startActivity(new Intent(this, cls));
    }


    public Activity getActivity() {
        return this;
    }

    private LoadingDialog mLoadingDialog;
    private int showLoadingDialog = 0;

    @Override
    public synchronized void showProgressDialog() {
        if (showLoadingDialog == 0) {
            if (mLoadingDialog == null) mLoadingDialog = new LoadingDialog(getActivity());
            if (!this.isFinishing()) {
                if (!this.isFinishing()) mLoadingDialog.show();
            }
        }
        showLoadingDialog++;
    }

    @Override
    public synchronized void hideProgressDialog() {
        showLoadingDialog--;
        if (mLoadingDialog != null && showLoadingDialog == 0) {
            mLoadingDialog.dismiss();
        }
    }


    @Override
    public void showError(Throwable throwable) {
        ToastUtil.s(throwable.getMessage());
        if (BuildConfig.DEBUG) throwable.printStackTrace();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
    }


    /**
     * 权限提示对话框
     */


    public void showPermissionDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle(R.string.tips_message)
                .setMessage(R.string.permission_lack)
                .setNegativeButton(R.string.cancel, (dialog, which) -> {
                })
                .setPositiveButton(R.string.ok, (dialog, which) -> startAppSettings()).show();
    }

    /**
     * 启动当前应用设置页面
     */


    public void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RESULT_LOGIN) {
            int eventId = 0;
            if (data != null) eventId = data.getIntExtra("eventId", 0);
            backFromLogin(eventId);//从登陆界面返回  登录成功
        }
    }

    /**
     * 登录返回
     *
     * @param eventId
     */
    protected void backFromLogin(int eventId) {

    }


    protected abstract int getLayoutId();

    protected abstract CommonToolbar setToolbar();

    protected abstract void initialize();
}
