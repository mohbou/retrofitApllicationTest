package com.mohbou.retrofitapplicationtest;


import com.mohbou.retrofitapplicationtest.model.GithubRepo;
import com.mohbou.retrofitapplicationtest.network.ApiService;

import java.util.List;

import io.reactivex.Single;


public class MainModel implements MainActivityMVP.Model {

    ApiService mApiService;

    List<GithubRepo> reposCall;

    public MainModel(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public Single<List<GithubRepo>> allRepos() {
        reposCall = null;
        return Single.fromCallable(() -> {

            reposCall = mApiService.getAllRepos().execute().body();
            return reposCall;
        });

    }

    @Override
    public Single<List<GithubRepo>> userRepos(final String username) {
        reposCall = null;
        return Single.fromCallable(() -> {

            reposCall = mApiService.getReposForUser(username).execute().body();
            return reposCall;
        });
    }


}
