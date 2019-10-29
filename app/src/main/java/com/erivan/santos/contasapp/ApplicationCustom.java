package com.erivan.santos.contasapp;

import android.app.Application;

public class ApplicationCustom extends Application {

    private static ApplicationCustom app = null;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
    }

    public synchronized static ApplicationCustom getInstance() {
        if (app == null)
            app = new ApplicationCustom();

        return app;
    }
}
