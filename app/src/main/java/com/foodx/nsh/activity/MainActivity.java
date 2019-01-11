package com.foodx.nsh.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.foodx.nsh.R;
import com.foodx.nsh.fragments.CartFragment;
import com.foodx.nsh.fragments.HotelsFragment;
import com.foodx.nsh.model.Hotel;
import com.foodx.nsh.model.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import com.foodx.nsh.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView btmView;

    @SuppressLint("ResourceType")
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
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HotelsFragment()).commit();

        int[] colors = new int[] {
                Color.parseColor("#afb42b"),
                Color.parseColor("#9e9d24")
        };

        int [][] states = new int [][]{
                new int[] { android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[] {android.R.attr.state_enabled, android.R.attr.state_checked}
        };
        btmView.setItemTextColor(new ColorStateList(states, colors));
//       btmView.setBackground(ActivityCompat.getDrawable(getApplicationContext(),R.drawable.white_gradient));

//        onInit();
       // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HotelsFragment(MainActivity.this)).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemReselectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("ResourceAsColor")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;

            int[] colors = new int[] {
                      Color.parseColor("#afb42b"),
                    Color.parseColor("#9e9d24")
            };

            int [][] states = new int [][]{
                    new int[] { android.R.attr.state_enabled, -android.R.attr.state_checked},
                    new int[] {android.R.attr.state_enabled, android.R.attr.state_checked}
            };

            switch (item.getItemId())
            {
                case R.id.hotels:
                    btmView.setItemTextColor(new ColorStateList(states, colors));
//                    btmView.setItemIconTintList(new ColorStateList(states, colors));
                    fragment = new HotelsFragment();
                    break;
                case R.id.carts:
                    btmView.setItemTextColor(new ColorStateList(states,colors));
                    fragment = new CartFragment();
                    break;
                case R.id.settings:
                    btmView.setItemTextColor(new ColorStateList(states,colors));
                    fragment = new Fragment();
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
        });
        hotelList = new ArrayList<>();
        hotelAdapter = new HotelAdapter(hotelList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(hotelAdapter);
        getData();
    }
    public void getData() {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonArrayRequest request = new JsonArrayRequest("https://fodo1.herokuapp.com/hotel",
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray jsonArray) {
                            for(int i = 0; i < jsonArray.length(); i++) {
                                try {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                    mEntries.add(jsonObject.toString());
                                    String name, location, mobile, id;
                                id = jsonObject.getString("_id");
                                name = jsonObject.getString("name");
                                location = jsonObject.getString("location");
                                mobile = jsonObject.getString("mobile");
                                hotelList.add(new Hotel(id, name, "https://i.dlpng.com/static/png/151888_preview.png", mobile, location));
                                }
                                catch(JSONException e) {
                                }
                            }
                            hotelAdapter.notifyDataSetChanged();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(MainActivity.this, "Unable to fetch data: " + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            requestQueue.add(request);
    }
//    public void setBackground(Drawable background) {
//        //noinspection deprecation
//        setBackgroundDrawable(background);
//    }
//
//    @Deprecated
//    public void setBackgroundDrawable(Drawable background) {}
=======
}
