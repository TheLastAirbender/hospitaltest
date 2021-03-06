package com.example.diploma_test.api;

import com.squareup.moshi.Moshi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetroInstance {
    // *************** RETROFIT TEST *****************
    //public static String API_BASE_URL = "https://api.github.com/";
    public static String API_BASE_URL = "http://195.206.49.75:5432/messenger-system/";
    //public static String API_BASE_URL = "https://messenger-restful.herokuapp.com/messenger-system/";
    private static Retrofit retrofit;

    public static Retrofit getRetroClient() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(5, TimeUnit.SECONDS)
                    .readTimeout(5, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS);

            Retrofit.Builder builder =
                    new Retrofit.Builder()
                            .baseUrl(API_BASE_URL)
                            .addConverterFactory(
                                    GsonConverterFactory.create());
//                            );
//            Retrofit.Builder builder =
//                    new Retrofit.Builder()
//                            .baseUrl(API_BASE_URL)
//                            .addConverterFactory(JacksonConverterFactory.create());

            retrofit =
                    builder.client(httpClient.build()).build();
        }
        return retrofit;
    }
}
