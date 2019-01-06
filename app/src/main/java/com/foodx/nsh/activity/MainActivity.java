package com.foodx.nsh.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foodx.nsh.R;
import com.foodx.nsh.adapter.HotelAdapter;
import com.foodx.nsh.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private RelativeLayout linearLayout;
    private HotelAdapter hotelAdapter;
    private List<Hotel> hotelList;
    private TextView foodx;
    private int up=0,down=0;
    private float count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        onInit();
    }

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
                up+=dy;
                foodx.setAlpha(1-((float)up/100 >=0 && (float)up/100 <=1 ? (float)up/100 : ((float)up/100 >1 ? 1 : 0)));

            }
        });
        hotelList = new ArrayList<>();
        hotelAdapter = new HotelAdapter(hotelList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(hotelAdapter);
        getData();
    }

    public void getData() {
        hotelList.add(new Hotel("1","Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("1","Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("1","Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("1","Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("1","Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("1","Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("1","Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("1","Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("1","Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("1","Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("1","Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelAdapter.notifyDataSetChanged();
    }
}
