
package com.ytakagi.volleysample;

import android.app.Application;
import android.content.Context;

public class SampleApplication extends Application {
    private static SampleApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Context getContext() {
        return application.getApplicationContext();
    }

}
