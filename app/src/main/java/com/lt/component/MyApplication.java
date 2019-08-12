package com.lt.component;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lt.lib_common.base.BaseApplication;

public class MyApplication extends BaseApplication {

    private static MyApplication myApplication;

    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        if (true) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);


    }
}
