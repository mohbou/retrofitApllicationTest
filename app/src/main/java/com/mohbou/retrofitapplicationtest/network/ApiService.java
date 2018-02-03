package com.mohbou.retrofitapplicationtest.network;

import com.mohbou.retrofitapplicationtest.model.GithubRepo;
import com.mohbou.retrofitapplicationtest.model.GithubUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Path;
import retrofit2.http.GET;

public interface ApiService {

    @GET("users/{username}/repos")
    Call<List<GithubRepo>> getReposForUser(@Path("username") String username);

    @GET("repositories")
    Call<List<GithubRepo>> getAllRepos();

    @GET("users/{username}")
    Call<GithubUser> getUser(@Path("username") String username);
}
