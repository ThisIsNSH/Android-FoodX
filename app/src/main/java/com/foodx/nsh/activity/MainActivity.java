package com.foodx.nsh.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.foodx.nsh.R;
import com.foodx.nsh.fragments.CartFragment;
import com.foodx.nsh.fragments.HotelsFragment;
import com.foodx.nsh.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView btmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        btmView = findViewById(R.id.nav_bar);
        btmView.setOnNavigationItemSelectedListener(navigationItemReselectedListener);
        Intent i = getIntent();
        String data = i.getStringExtra("FromReservation");

        if (data != null && data.contentEquals("1")) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new CartFragment(MainActivity.this));
            fragmentTransaction.commitNow();
        }
        else
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HotelsFragment(MainActivity.this)).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemReselectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId())
            {
                case R.id.hotels:
                    fragment = new HotelsFragment(MainActivity.this);
                    break;
                case R.id.carts:
                    fragment = new CartFragment(MainActivity.this);
                    break;
                case R.id.settings:
                    fragment = new SettingsFragment(MainActivity.this);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }

    };

}
