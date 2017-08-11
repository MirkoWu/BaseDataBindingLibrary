package com.softgarden.baselibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softgarden.baselibrary.utils.InstanceUtil;
import com.softgarden.baselibrary.widget.LoadingDialog;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.lang.reflect.ParameterizedType;

import static android.app.Activity.RESULT_OK;
import static com.softgarden.baselibrary.base.BaseActivity.RESULT_LOGIN;

/**
 * MVP fragment基类
 */
public abstract class BaseFragment<T extends IBasePresenter> extends RxFragment implements IBaseDisplay {
    protected T mPresenter;


    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private boolean isInited = false;//

    private int position;
    private String title;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        isInited = true;
        initialize();
    }

    /**
     * 初始化Presenter attachView
     */
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

    /**
     * detachView
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) mPresenter.detachView();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isInited && !hidden) {
            isInited = true;
            initialize();
        }
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    public void openActivity(Class<? extends Activity> cls) {
        this.startActivity(new Intent(getActivity(), cls));
    }


    private LoadingDialog mLoadingDialog;
    private int showLoadingDialog = 0;

    @Override
    public synchronized void showProgressDialog() {
        if (showLoadingDialog == 0) {
            if (mLoadingDialog == null)
                mLoadingDialog = new LoadingDialog(getActivity());
            if (!getActivity().isFinishing()) {
                mLoadingDialog.show();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RESULT_LOGIN) {
            int eventId = 0;
            if (data != null) eventId = data.getIntExtra("eventId", 0);
            backFromLogin(eventId);//从登陆界面返回  登录成功
        }
    }

    protected void backFromLogin(int eventId) {

    }

    protected abstract int getLayoutId();

    protected abstract void initialize();


}
