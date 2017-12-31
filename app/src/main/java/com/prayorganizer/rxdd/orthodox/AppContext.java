package com.prayorganizer.rxdd.orthodox;

import android.app.Application;
import android.content.Context;

/**
 * Created by Rexedead on 31.12.2017.
 *
 */

public class AppContext extends Application{
    private static Context sAppContext;


    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = getApplicationContext();

    }

    public static Context getAppContext() {
        return sAppContext;
    }
}
