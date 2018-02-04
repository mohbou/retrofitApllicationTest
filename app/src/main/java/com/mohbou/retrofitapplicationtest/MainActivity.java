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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Inject
    ApiService mApiService;

    Call<List<GithubRepo>> reposCall;

    Call<List<GithubRepo>> reposmohbou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App)getApplication()).getComponent().inject(this);

        reposCall = mApiService.getAllRepos();

        reposCall.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                final List<GithubRepo> body = response.body();
                body.stream()
                    .forEach((githubRepo) ->Log.d("result", "onResponse: "+githubRepo.fullName));
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error getting repos " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        reposmohbou = mApiService.getReposForUser("mohbou");

        reposmohbou.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                final List<GithubRepo> body = response.body();
                body.stream()
                        .forEach((githubRepo) ->Log.d("result", "onResponse repo for mohbou: "+githubRepo.name));
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error getting repos for mohbou " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("result", "onFailure: "+t.getMessage());
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(reposCall != null) {
            reposCall.cancel();
        }
        if(reposmohbou != null) {
            reposmohbou.cancel();
        }
    }
}
