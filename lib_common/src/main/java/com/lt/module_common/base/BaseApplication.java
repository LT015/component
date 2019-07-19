package com.lt.module_common.base;

import android.app.Application;

import java.util.List;
import java.util.logging.Logger;

public class BaseApplication extends Application {
    public static final String ROOT_PACKAGE = "com.lt";

    private static BaseApplication sInstance;

    private List<IApplicationDelegate> mAppDelegateList;


    public static BaseApplication getIns() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }


}
