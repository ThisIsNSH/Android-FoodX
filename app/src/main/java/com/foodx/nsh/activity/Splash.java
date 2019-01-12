package com.foodx.nsh.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.foodx.nsh.R;

/**
 * Created by LENOVO on 11-01-2019.
 */

public class Splash extends AppCompatActivity {
    private int SPLASH_TIME = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(SPLASH_TIME);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }
}
