package com.xema.lamp.common;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //벡터 이미지 활성화
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

    }
}
