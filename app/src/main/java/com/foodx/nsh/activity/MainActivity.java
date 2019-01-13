package com.foodx.nsh.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.foodx.nsh.R;
import com.foodx.nsh.fragments.CartFragment;
import com.foodx.nsh.fragments.HotelsFragment;
import com.foodx.nsh.fragments.SettingsFragment;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView btmView;
    boolean doubleBackToExitPressedOnce = false;
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
        }
        else
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HotelsFragment(MainActivity.this)).commit();

        int[] colors = new int[] {
                Color.parseColor("#dadada"),
                Color.parseColor("#cddc39")
        };

        int [][] states = new int [][]{
                new int[] { android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[] {android.R.attr.state_enabled, android.R.attr.state_checked}
        };
        btmView.setItemTextColor(new ColorStateList(states, colors));
        btmView.setItemIconTintList(new ColorStateList(states, colors));
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemReselectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("ResourceAsColor")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            int[] colors = new int[] {
                    Color.parseColor("#dadada"),
                    Color.parseColor("#cddc39")
            };

            int [][] states = new int [][]{
                    new int[] { android.R.attr.state_enabled, -android.R.attr.state_checked},
                    new int[] {android.R.attr.state_enabled, android.R.attr.state_checked}
            };

            switch (item.getItemId())
            {
                case R.id.hotels:
                    btmView.setItemTextColor(new ColorStateList(states, colors));
                    btmView.setItemIconTintList(new ColorStateList(states, colors));
                    fragment = new HotelsFragment(MainActivity.this);
                    break;
                case R.id.carts:
                    btmView.setItemTextColor(new ColorStateList(states,colors));
                    btmView.setItemIconTintList(new ColorStateList(states, colors));
                    fragment = new CartFragment(MainActivity.this);
                    break;
                case R.id.settings:
                    btmView.setItemTextColor(new ColorStateList(states,colors));
                    btmView.setItemIconTintList(new ColorStateList(states, colors));
                    fragment = new SettingsFragment(MainActivity.this);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
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
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}