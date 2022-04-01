package com.example.demomvvm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SignUpActivity extends AppCompatActivity {
    EditText edUser, edAccount, edPassword, edEmail, edPhone;
    Button btSignUp;
    TextView tvCancel;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m003_signup);

        edUser = findViewById(R.id.ed_user);
        edAccount = findViewById(R.id.ed_account);
        edPassword = findViewById(R.id.ed_password);
        edEmail = findViewById(R.id.ed_email);
        edPhone = findViewById(R.id.ed_phone);
        btSignUp = findViewById(R.id.bt_signup);
        tvCancel = findViewById(R.id.tv_cancel);

        tvCancel.setPaintFlags(tvCancel.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);

        btSignUp.setOnClickListener(view -> {
            String USER = edUser.getText().toString().trim();
            String ACCOUNT = edAccount.getText().toString().trim();
            String PASSWORD = edPassword.getText().toString().trim();
            String EMAIL = edEmail.getText().toString().trim();
            String PHONE = edPhone.getText().toString().trim();

            if(USER.isEmpty()){
                edUser.setError("Username required");
                edUser.requestFocus();
                return;
            }

            if(ACCOUNT.isEmpty()){
                edAccount.setError("Account required");
                edAccount.requestFocus();
                return;
            }

            if(PASSWORD.isEmpty()){
                edPassword.setError("Password required");
                edPassword.requestFocus();
                return;
            }

            if(EMAIL.isEmpty()){
                edEmail.setError("Email required");
                edEmail.requestFocus();
                return;
            }

            if(PHONE.isEmpty()){
                edPhone.setError("Phone Number required");
                edPhone.requestFocus();
                return;
            }


            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Username",USER);
            editor.putString("Account",ACCOUNT);
            editor.putString("Password",PASSWORD);
            isValidPassword();
            editor.putString("Email",EMAIL);
            editor.putString("PhoneNumber",PHONE);
            isValidMobile();
            editor.apply();
            Toast.makeText(SignUpActivity.this,"Sign Up Success!",Toast.LENGTH_SHORT).show();


        });

        tvCancel.setOnClickListener(view -> {
            Intent mIntent = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(mIntent);
        });


    }

    public boolean isValidPassword(){
        if (edPassword.length()!=6)
        {
            Toast.makeText(getApplicationContext(),"Password must have 6 characters", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    public boolean isValidMobile(){
        if (edPhone.length()!=10)
        {
            Toast.makeText(getApplicationContext(),"Mobile Number must have 10 characters", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
