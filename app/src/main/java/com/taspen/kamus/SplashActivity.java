package com.taspen.kamus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Author :)
 */
public class SplashActivity extends Activity {

    private static final Integer SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(in);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }


}
