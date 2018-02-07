package com.mohbou.retrofitapplicationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mohbou.retrofitapplicationtest.model.GithubRepo;
import com.mohbou.retrofitapplicationtest.network.ApiService;

import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainActivityMVP.View {

    @Inject
    ApiService mApiService;

    @Inject
    MainActivityMVP.Presenter presenter;

    @Inject
    CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getComponent().inject(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.setApi(mApiService);
        presenter.setDisposable(mCompositeDisposable);
        presenter.showAllRepos();
        presenter.showUserRepos("mohbou");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }

    @Override
    public void showAllRepos(List<GithubRepo> repos) {
        repos.stream().forEach((githubRepo) -> Log.d("result", "All repos: " + githubRepo.name));

    }

    @Override
    public void showUserRepos(List<GithubRepo> repos) {
        repos.stream().forEach((githubRepo) -> Log.d("result", "user repos: " + githubRepo.name));
    }

    @Override
    public void showError() {
 // future update
    }
}
