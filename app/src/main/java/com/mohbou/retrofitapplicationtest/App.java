package com.mohbou.retrofitapplicationtest;

import android.app.Application;


public class App extends Application {

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                             .applicationModule(new ApplicationModule(this))
                             .build();
    }

    ApplicationComponent getComponent() {
        return component;
    }
}
