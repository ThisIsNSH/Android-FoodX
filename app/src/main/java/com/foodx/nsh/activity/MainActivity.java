package com.foodx.nsh.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.foodx.nsh.R;
import com.foodx.nsh.adapter.HotelAdapter;
import com.foodx.nsh.fragments.CartFragment;
import com.foodx.nsh.fragments.HotelsFragment;
import com.foodx.nsh.model.Hotel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RelativeLayout linearLayout;
    private HotelAdapter hotelAdapter;
    private List<Hotel> hotelList;
    private TextView foodx;
    private int up = 0, down = 0;
    private BottomNavigationView btmView;
//    private float count = 0;

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
            fragmentTransaction.replace(R.id.fragment_container, new CartFragment());
            fragmentTransaction.commitNow();

        }
        else
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HotelsFragment()).commit();

//        onInit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemReselectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId())
            {
                case R.id.hotels:
                    fragment = new HotelsFragment();
                    break;
                case R.id.carts:
                    fragment = new CartFragment();
                    break;
                case R.id.settings:
                    fragment = new Fragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }

    };
    public void onInit() {
        linearLayout = findViewById(R.id.mainsurface);
        foodx = findViewById(R.id.foodx);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                up += dy;
                foodx.setAlpha(1 - ((float) up / 100 >= 0 && (float) up / 100 <= 1 ? (float) up / 100 : ((float) up / 100 > 1 ? 1 : 0)));

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
}
