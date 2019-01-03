package com.foodx.nsh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.foodx.nsh.R;
import com.foodx.nsh.adapter.HotelAdapter;
import com.foodx.nsh.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HotelAdapter hotelAdapter;
    private List<Hotel> hotelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onInit();
    }

    public void onInit() {
        recyclerView = findViewById(R.id.recyclerView);
        hotelList = new ArrayList<>();
        hotelAdapter = new HotelAdapter(hotelList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(hotelAdapter);
        getData();
    }

    public void getData() {
        hotelList.add(new Hotel("Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelList.add(new Hotel("Chopra Residency", "https://i.dlpng.com/static/png/151888_preview.png", "10:00 - 22:00", "abra ka dabra gili gili chhu abra ka dabra gili gili chhu"));
        hotelAdapter.notifyDataSetChanged();
    }
}
