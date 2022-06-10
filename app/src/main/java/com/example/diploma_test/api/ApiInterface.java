package com.example.diploma_test.api;

import com.example.diploma_test.entity.News;
import com.example.diploma_test.entity.Token;
import com.example.diploma_test.entity.User;
import com.example.diploma_test.pojos.LoginRequest;
import com.example.diploma_test.pojos.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    // test
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> reposForUser(
            @Path("user") String user);

    // ******************* REGISTER, LOGIN, USERS ***********************
    @POST("api/v1/auth/register")
    Call<Token> register (@Body User user);

    @POST("api/v1/auth/login")
    Call<Token> login (@Body LoginRequest loginRequest);
    //
    @GET("api/v1/users")
    @Headers("accept: application/json")
    Call<List<User>> getAllUsers(@Header("Authorization") String token);

    // ******************* NEWSFEED ***********************
    @GET("api/v1/newsfeed/1")
    @Headers("accept: application/json")
    Call<List<News>> getAllNews(@Header("Authorization") String token);
}
