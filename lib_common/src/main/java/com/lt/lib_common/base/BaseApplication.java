package com.lt.lib_common.base;

import android.app.Application;

import java.util.List;

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
