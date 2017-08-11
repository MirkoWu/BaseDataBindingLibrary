package com.softgarden.baselibrary.base;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.trello.rxlifecycle2.components.support.RxAppCompatDialogFragment;

/**
 * Created by Lightwave on 2015/12/3.
 */
public abstract class BaseDialogFragment extends RxAppCompatDialogFragment {
    public final String TAG = getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(getLayoutId(), container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initContentView();
        initialize();
    }

    protected void initContentView() {
        if (!isAdded()) return;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable());
        setStyle(DialogFragment.STYLE_NO_INPUT, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
    }
//    @NonNull
//    public <T extends Display> T $(@IdRes int resId) {
//        return (T) getView().findViewById(resId);
//    }
//
//    public <T extends Display> T inflate(@LayoutRes int resId) {
//        return (T) Display.inflate(getActivity(), resId, null);
//    }

    public abstract int getLayoutId();

    public abstract void initialize();

}
