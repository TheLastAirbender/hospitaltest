package com.example.diploma_test.pojos;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("token")
    String token;

    @SerializedName("tokenType")
    String tokenType;
}
