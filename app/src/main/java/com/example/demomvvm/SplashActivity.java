package com.example.demomvvm;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;


public class SplashActivity extends AppCompatActivity {
    ImageView ivSplash;

    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    private final Handler mHideHandler = new Handler();
    private final Runnable mHideRunnable  = () -> {
        Intent mIntent = new Intent(SplashActivity.this, SignInActivity.class);
        startActivity(mIntent);
        finish();

        };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m001_splash);

        ivSplash = findViewById(R.id.iv_splash);

        delayedHide(AUTO_HIDE_DELAY_MILLIS);
        animationSplash();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }


    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
    private void animationSplash() {
        Animation animation = new TranslateAnimation(0, 0,-200, 0);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        ivSplash.setAnimation(animation);
    }
}