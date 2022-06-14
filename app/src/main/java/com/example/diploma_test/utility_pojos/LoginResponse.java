package com.example.diploma_test.utility_pojos;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("token")
    String token;

    @SerializedName("tokenType")
    String tokenType;
}
