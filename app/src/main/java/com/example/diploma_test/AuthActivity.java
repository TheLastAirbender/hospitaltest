package com.example.diploma_test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.diploma_test.entity.User;
import com.example.diploma_test.viewmodel.DashboardViewModel;
import com.example.diploma_test.viewmodel.LoginViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class AuthActivity extends AppCompatActivity {
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = (EditText) findViewById(R.id.loginEditText);
        EditText password = (EditText) findViewById(R.id.passEditText);
        Button login = (Button) findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveData<User> lolita = loginViewModel.login(username.getText().toString(),password.getText().toString());
                User popa = lolita.getValue();
                System.out.println(popa.getUsername());
            }
        });

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.login(username.getText().toString(),password.getText().toString()).observe(this, user -> {
            if (user != null) {
                System.out.println("************************** Че то **************************");
            }
            else {
                System.out.println("************************** Че то не то **************************");
            }
        });
    }
}