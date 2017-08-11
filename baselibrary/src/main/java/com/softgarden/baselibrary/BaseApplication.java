package com.softgarden.baselibrary;

import android.app.Application;


/**
 * Created by MirkoWu on 2017/4/26 0026.
 */

public class BaseApplication extends Application {
    private static BaseApplication instance;

    public static synchronized BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
