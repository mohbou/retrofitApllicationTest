package com.mohbou.retrofitapplicationtest;

import com.mohbou.retrofitapplicationtest.model.GithubRepo;
import com.mohbou.retrofitapplicationtest.network.ApiService;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

public interface MainActivityMVP {

    interface View {
       void showAllRepos(List<GithubRepo> repos);
       void showUserRepos(List<GithubRepo> repos);
       void showError();
    }

    interface Presenter {
        void setView(MainActivityMVP.View view);
        void showAllRepos();
        void showUserRepos(String name);
        void dispose();
        void setDisposable(CompositeDisposable mCompositeDisposable);

    }

    interface Model {
        Single<List<GithubRepo>> allRepos();
        Single<List<GithubRepo>> userRepos(String username);

    }
}
