package com.softgarden.baselibrary.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.softgarden.baselibrary.R;


/**
 * Created by Administrator on 2016/3/15.
 */
public class LoadingDialog extends ProgressDialog {
    TextView tv_msg;
    View view;

    /**
     * 这里的Context 必须用actiivty 不能用applicationContext
     *
     * @param context
     */
    public LoadingDialog(Context context) {
        super(context, R.style.DialogStyle);//
        view = View.inflate(context, R.layout.view_dialog_loading, null);
        tv_msg = (TextView) view.findViewById(R.id.mProgressTextView);

        this.setCanceledOnTouchOutside(false);
        this.setCancelable(true);
    }

    public LoadingDialog(Context context, String msg) {
        super(context, R.style.DialogStyle);

        view = View.inflate(context, R.layout.view_dialog_loading, null);
        tv_msg = (TextView) view.findViewById(R.id.mProgressTextView);
        if (!TextUtils.isEmpty(msg)) {
            tv_msg.setText(msg);
            tv_msg.setVisibility(View.VISIBLE);
        } else tv_msg.setVisibility(View.GONE);
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(true);
    }


    @Override
    public void show() {
        try {
            if (this.isShowing()) this.dismiss();
            else super.show();
            //setContentView（）一定要在show之后调用
            this.setContentView(view);
        } catch (WindowManager.BadTokenException exception) {
        }
    }


    public void setMessage(String message) {
        if (!TextUtils.isEmpty(message)) tv_msg.setText(message);
    }

    public void setMessage(@StringRes int message) {
        tv_msg.setText(message);
    }

}
