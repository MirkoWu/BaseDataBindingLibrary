package com.softgarden.baselibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by MirkoWu on 2017/12/7 0007.
 */

public class ViewG extends ViewGroup{
    public ViewG(Context context) {
        super(context);
    }

    public ViewG(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewG(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }
}
