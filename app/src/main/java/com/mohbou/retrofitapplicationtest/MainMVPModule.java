package com.mohbou.retrofitapplicationtest;


import com.mohbou.retrofitapplicationtest.network.ApiService;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module(includes = ApiModule.class)
public class MainMVPModule {

    @Provides
    public MainActivityMVP.Presenter providePresenter(MainActivityMVP.Model model) {
        return new MainActivityPresenter(model);
    }

    @Provides
    public MainActivityMVP.Model provideModel(ApiService apiService) {
        return new MainModel(apiService);
    }

    @Provides
    public CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }
}
