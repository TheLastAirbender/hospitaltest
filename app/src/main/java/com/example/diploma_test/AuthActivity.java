package com.example.diploma_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.diploma_test.api.ApiInterface;
import com.example.diploma_test.api.AppDatabase;
import com.example.diploma_test.api.GitHubRepo;
import com.example.diploma_test.api.GitHubRepoDao;
import com.example.diploma_test.entity.Token;
import com.example.diploma_test.entity.User;
import com.example.diploma_test.viewmodel.DashboardViewModel;
import com.example.diploma_test.viewmodel.LoginViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LiveData<User> lolita = loginViewModel.login(username.getText().toString(),password.getText().toString());
//                User popa = lolita.getValue();
//                System.out.+println(popa.getUsername());
                //System.out.println("After click:" + loginViewModel.login(username.getText().toString(),password.getText().toString()).getValue());
//                Token token = loginViewModel.getToken().getValue();
//                System.out.println(token);
                loginViewModel.login(username.getText().toString(),password.getText().toString());

                ///loginViewModel.getUsersRequestToRepo(" Bearer "+"eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJvcmcudXJ1bW92Iiwic3ViIjoiOSxzZXJlamtlZUBtYWlsLnJ1IiwiaWF0IjoxNjU0NzkyMDk1LCJleHAiOjE2NTczODQwOTV9.tuaZXzJFJd_R4YcSoZKTdGxRre6-wOGL0CJJo--vYUNWTu-h7Cj7wqkr9vDpRYyZ514tRLGtr8dNzIOrkxk1hw");
            }
        });

        loginViewModel.getToken().observe(this, new Observer<Token>() {
            @Override
            public void onChanged(Token token) {
                System.out.println("SUUKA");
                if(token != null) {
                    System.out.println(token.getToken());
                    Intent intent = new Intent(AuthActivity.this,MainActivity.class);
                    startActivity(intent);
                    //loginViewModel.getUsersRequestToRepo(" Bearer "+token.getToken());
                }
//                if (token != null) {
//                Intent intent = new Intent(AuthActivity.this,MainActivity.class);
//                startActivity(intent);}
            }
        });

        loginViewModel.observableUsersList().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
//                System.out.println("PASKUDA");
                for (User user : users) {
                    System.out.println(user.getUsername());
                }
            }
        });
        // Subscribe to
//        loginViewModel.login(username.getText().toString(),password.getText().toString()).observe(this, user -> {
//            if (user != null) {
//                System.out.println("************************** Че то **************************");
//            }
//            else {
//                System.out.println("************************** Че то не то **************************");
//            }
//        });

    }
}