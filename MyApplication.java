package com.notify.notify;

import android.app.Application;
import android.content.Context;

/**
 * Created by Himanjan on 11-03-2017.
 */

public class MyApplication extends Application {
    private static Context aContext;

    public void onCreate() {
        super.onCreate();
        MyApplication.aContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.aContext;
    }
}
