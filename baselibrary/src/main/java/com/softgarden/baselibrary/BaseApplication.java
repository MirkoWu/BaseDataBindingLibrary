package com.softgarden.baselibrary;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

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
        ARouter.openLog();     // 打印日志
        //ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)

        ARouter.init(this);
    }
}
