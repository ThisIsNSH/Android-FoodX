package com.mangwalo.nsh.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mangwalo.nsh.R;
import com.mangwalo.nsh.fragments.CartFragment;
import com.mangwalo.nsh.fragments.DetailsFragment;
import com.mangwalo.nsh.fragments.HotelsFragment;
import com.mangwalo.nsh.fragments.SettingsFragment;
import com.wooplr.spotlight.SpotlightView;

import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private SpotlightView spotLight;
    private BottomNavigationView btmView;
    boolean doubleBackToExitPressedOnce = false;
    final int UPI_PAYMENT = 0;
    TextView play;

    //    public static HashMap<<String,String>,String> orderList;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btmView = findViewById(R.id.nav_bar);
        btmView.setOnNavigationItemSelectedListener(navigationItemReselectedListener);
        btmView.setItemBackgroundResource(R.drawable.transparent);
        SharedPreferences preferences = getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        Intent i = getIntent();
        String data = i.getStringExtra("FromReservation");
        if (data != null && data.contentEquals("1")) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new CartFragment(MainActivity.this));
            fragmentTransaction.commitNow();
        } else
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HotelsFragment(MainActivity.this)).commit();

        int[] colors = new int[]{
                Color.parseColor("#dadada"),
                Color.parseColor("#cddc39")
        };

        int[][] states = new int[][]{
                new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[]{android.R.attr.state_enabled, android.R.attr.state_checked}
        };
        btmView.setItemTextColor(new ColorStateList(states, colors));
        btmView.setItemIconTintList(new ColorStateList(states, colors));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemReselectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("ResourceAsColor")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            int[] colors = new int[]{
                    Color.parseColor("#dadada"),
                    Color.parseColor("#cddc39")
            };

            int[][] states = new int[][]{
                    new int[]{android.R.attr.state_enabled, -android.R.attr.state_checked},
                    new int[]{android.R.attr.state_enabled, android.R.attr.state_checked}
            };

            switch (item.getItemId()) {
                case R.id.hotels:
                    btmView.setItemTextColor(new ColorStateList(states, colors));
                    btmView.setItemIconTintList(new ColorStateList(states, colors));
                    fragment = new HotelsFragment(MainActivity.this);
                    break;
                case R.id.carts:
                    btmView.setItemTextColor(new ColorStateList(states, colors));
                    btmView.setItemIconTintList(new ColorStateList(states, colors));
                    fragment = new CartFragment(MainActivity.this);
                    break;
                case R.id.settings:
                    btmView.setItemTextColor(new ColorStateList(states, colors));
                    btmView.setItemIconTintList(new ColorStateList(states, colors));
                    fragment = new SettingsFragment(MainActivity.this);
                    break;
                case R.id.details:
                    btmView.setItemTextColor(new ColorStateList(states, colors));
                    btmView.setItemIconTintList(new ColorStateList(states, colors));
                    fragment = new DetailsFragment(MainActivity.this);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }

    };

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


}