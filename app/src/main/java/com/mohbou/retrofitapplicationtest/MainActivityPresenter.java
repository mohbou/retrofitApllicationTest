package com.mohbou.retrofitapplicationtest;

import android.support.annotation.Nullable;
import android.util.Log;

import com.mohbou.retrofitapplicationtest.model.GithubRepo;
import com.mohbou.retrofitapplicationtest.network.ApiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter implements MainActivityMVP.Presenter {

    @Nullable
    private MainActivityMVP.View view;
    private MainActivityMVP.Model model;

    CompositeDisposable mCompositeDisposable;

    public MainActivityPresenter(MainActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(MainActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void showAllRepos() {
        mCompositeDisposable.add(model.allRepos()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<List<GithubRepo>>() {
            @Override
            public void onSuccess(List<GithubRepo> githubRepos) {
             view.showAllRepos(githubRepos);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("test", "onFailure: test "+e.getMessage());
               view.showError();
            }
        }));
    }

    @Override
    public void showUserRepos(String username) {
        mCompositeDisposable.add(model.userRepos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<GithubRepo>>() {
                    @Override
                    public void onSuccess(List<GithubRepo> githubRepos) {
                        view.showUserRepos(githubRepos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", "onFailure: showUserRepos "+e.getMessage());
                        view.showError();
                    }
                }));

    }

    @Override
    public void dispose() {
       if(mCompositeDisposable !=null) {
           mCompositeDisposable.clear();
       }
    }

    @Override
    public void setDisposable(CompositeDisposable mCompositeDisposable) {
        this.mCompositeDisposable = mCompositeDisposable;
    }


}
