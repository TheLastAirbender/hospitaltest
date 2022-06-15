package com.example.diploma_test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.diploma_test.entity.Token;
import com.example.diploma_test.entity.User;
import com.example.diploma_test.viewmodel.LoginViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class AuthActivity extends AppCompatActivity {
    LoginViewModel loginViewModel;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = (EditText) findViewById(R.id.loginEditText);
        EditText password = (EditText) findViewById(R.id.passEditText);
        Button login = (Button) findViewById(R.id.loginButton);

        prefs = this.getSharedPreferences(
                "com.example.app", Context.MODE_PRIVATE);

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
                System.out.println("Token onChanged in AuthActivity: "+token);
//                Toast toast = Toast.makeText(AuthActivity.this,"SALAM",Toast.LENGTH_SHORT );
                if(token != null) {
                    System.out.println("Putting " + username.getText().toString() + " in SharedPreferences");
                    prefs.edit().putString("token",token.getToken()).apply();
                    prefs.edit().putString("currentusername",username.getText().toString()).apply();
                    Intent intent = new Intent(AuthActivity.this,MainActivity.class);
                    startActivity(intent);
                    loginViewModel.getUsersRequestToRepo(" Bearer "+token.getToken());
                }
//                if (token != null) {
//                Intent intent = new Intent(AuthActivity.this,MainActivity.class);
//                startActivity(intent);}
            }
        });

        loginViewModel.observableUsersList().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User user : users) {
                    String username = prefs.getString("currentusername","nouser");
//                    System.out.println("We trying to find: "+ username + " in username "+user.getUsername() + " name " + user.getFirstname() + " id " + user.getId() + " roles "+user.getRoles());
                    if (username.equals(user.getUsername()) ) {
                        System.out.println("Found current user in database");
                        List<String> userRoles = user.getRoles();
                        if (userRoles.isEmpty()) {
                            System.out.println("THIS USER HAS NO RIGHTS");
                            prefs.edit().putInt("adminrole",0).apply();
                        }
                        for (String role : user.getRoles()) {
                            // ПЕРЕДЕЛАТЬ НА МЕНЕДЖЕРА
                            System.out.println(role);
                            if (role.toUpperCase().contains("USER")) {
                                System.out.println("THIS USER HAS ADMIN RIGHTS");
                                prefs.edit().putInt("adminrole",1).apply();
                                break;}
                            else {
                                System.out.println("THIS USER IS A REGULAR USER");
                                prefs.edit().putInt("adminrole",2).apply();}
                        }
                    }
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