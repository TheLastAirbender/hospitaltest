package com.example.diploma_test.utility_pojos;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("username")
    String username;

    @SerializedName("password")
    String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
