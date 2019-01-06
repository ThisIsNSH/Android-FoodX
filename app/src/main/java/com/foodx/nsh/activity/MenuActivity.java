package com.foodx.nsh.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foodx.nsh.R;
import com.foodx.nsh.adapter.HotelAdapter;
import com.foodx.nsh.adapter.MenuAdapter;
import com.foodx.nsh.model.Hotel;
import com.foodx.nsh.model.Item;
import com.foodx.nsh.model.Menu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private TextView hotelName;
    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private List<Menu> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        onInit();
    }

    public void onInit(){
        hotelName = findViewById(R.id.hotel_name);
        recyclerView = findViewById(R.id.recyclerView);
        Bundle bundle = getIntent().getExtras();
//        cardView.setCardBackgroundColor(getResources().getColor(bundle.getInt("color")));
        hotelName.setText(bundle.getString("name"));
        menuList = new ArrayList<>();
        menuAdapter = new MenuAdapter(bundle.getInt("color"),menuList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(menuAdapter);
        getData();
    }

    public void getData() {
        List<Item> itemList = new ArrayList<>(),itemList1 = new ArrayList<>(),itemList2 = new ArrayList<>();
        itemList.add(new Item("Item 1", "image", "100"));
        itemList.add(new Item("Item 1", "image", "100"));
        itemList.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList.add(new Item("Item 1", "image", "100"));
        itemList.add(new Item("Item 1", "image", "100"));
        itemList.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList.add(new Item("Item 1", "image", "100"));
        itemList.add(new Item("Item 1", "image", "100"));
        itemList.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList.add(new Item("Item 1", "image", "100"));
        itemList.add(new Item("Item 1", "image", "100"));
        itemList.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        menuList.add(new Menu("Pizza",itemList));
        menuList.add(new Menu("Pizza1",itemList1));
        menuList.add(new Menu("Pizza2",itemList2));
        menuAdapter.notifyDataSetChanged();
    }
}
