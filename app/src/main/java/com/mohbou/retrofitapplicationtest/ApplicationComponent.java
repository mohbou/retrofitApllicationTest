package com.mohbou.retrofitapplicationtest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class,ApiModule.class,MainMVPModule.class})
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}
