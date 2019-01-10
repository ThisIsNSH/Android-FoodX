package com.foodx.nsh.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.DatabaseUtils;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.foodx.nsh.R;
import com.foodx.nsh.adapter.CartAdapter;
import com.foodx.nsh.model.Cart;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String key = "Key";
    private List<Cart> myOrders;
    private RecyclerView recyclerView;
    private Gson gson;
    private CartAdapter cartAdapter;
    private String address,name,mobile,hotelid;
    JsonObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        sharedPreferences = getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        myOrders = new ArrayList<>();
        String response=sharedPreferences.getString(key , "null");
        if (!response.equals("null"))
            myOrders = gson.fromJson(response,new TypeToken<List<Cart>>(){}.getType());
        recyclerView = findViewById(R.id.recyclerView1);
        cartAdapter = new CartAdapter(myOrders,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
        Set <String> set = new HashSet<>();
        for(int i=0;i<myOrders.size();i++){
            set.add(myOrders.get(i).getHotelId());
        }

        address = "Hello";
        name = "nishu";
        mobile="988799220";
        hotelid="Fjrivevjnvks";

        jsonObject = new JsonObject();
        jsonObject.addProperty("address",address);
        jsonObject.addProperty("name",name);
        jsonObject.addProperty("mobile",mobile);
        jsonObject.addProperty("hotel_id",hotelid);

        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("name",myOrders.get(0).getName());
        jsonObject1.addProperty("quantity",myOrders.get(0).getQuantity());
        jsonObject1.addProperty("extra","none");
        jsonArray.add(jsonObject1);
        jsonObject.add("items",jsonArray);
        Button button = findViewById(R.id.postorder);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("hi",jsonObject.toString());
            }
        });




    }

}
