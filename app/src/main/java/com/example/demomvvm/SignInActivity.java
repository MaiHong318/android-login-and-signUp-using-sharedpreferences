package com.example.demomvvm;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SignInActivity extends AppCompatActivity {
    EditText edAccount, edPassword;
    Button btSignIn;
    TextView btSignUp;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m002_login);

        edAccount = findViewById(R.id.ed_account);
        edPassword = findViewById(R.id.ed_password);
        btSignIn = findViewById(R.id.bt_signin);
        btSignUp = findViewById(R.id.bt_signup);

        btSignUp.setPaintFlags(btSignUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);

        btSignIn.setOnClickListener(view -> {
            String USER = edAccount.getText().toString().trim();
            String PASS = edPassword.getText().toString().trim();

            if(USER.isEmpty()){
                edAccount.setError("Account required");
                edAccount.requestFocus();
                return;
            }

            if(PASS.isEmpty()){
                edPassword.setError("Password required");
                edPassword.requestFocus();
                return;
            }

            String signUpAccount = sharedPreferences.getString("Account","");
            String signUpPassWord = sharedPreferences.getString("Password","");

            if (USER.equals(signUpAccount) == false) {
                Toast.makeText(SignInActivity.this, "Incorrect Account!", Toast.LENGTH_SHORT).show();
            } else {
                if (PASS.equals(signUpPassWord) == false) {
                    Toast.makeText(SignInActivity.this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent mIntent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(mIntent);
                }
            }
        });


        btSignUp.setOnClickListener(view -> {
            Intent mIntent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(mIntent);
        });

    }

}
