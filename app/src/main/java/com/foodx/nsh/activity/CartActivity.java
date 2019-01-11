package com.foodx.nsh.activity;

import android.content.Context;
import android.content.SharedPreferences;
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

import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String key = "Key";
    private List<Cart> myOrders;
    private RecyclerView recyclerView;
    private Gson gson;
    private CartAdapter cartAdapter;
    private String address,name,mobile,hotelid,extra;
    private JsonObject jsonObject;
    //    private HashSet<String> hashSet;
    private HashMap<String, ArrayList<Cart>> hashMap;
    private HashMap<String,Cart>hashMap1;
    //    private ArrayList<Cart>arrayList;
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
        hashMap1 = new HashMap<>();
        for(int i=0;i<myOrders.size();i++){
            hashMap1.put(myOrders.get(i).getHotelId(),myOrders.get(i));
        }
        List<Map<String,List<Cart>>> list = new ArrayList<Map<String,List<Cart>>>();
        Map<String, List<Cart>> map1 = new HashMap<String, List<Cart>>();
        List<Cart> arraylist1 = new ArrayList<Cart>();
        for (String key  : hashMap1.keySet()) {
            arraylist1.clear();
            map1.clear();
            for (int j = 0; j < myOrders.size(); j++) {
                if (key == myOrders.get(j).getHotelId()){
                    arraylist1.add(myOrders.get(j));
                }
                map1.put(key,arraylist1);
            }
            list.add(map1);
        }
        //HarCoded Elements input required;
        address = "Hello";
        name = "nishu";
        mobile="988799220";
        extra = "";
//
//        jsonObject = new JsonObject();
//        jsonObject.addProperty("address",address);
//        jsonObject.addProperty("name",name);
//        jsonObject.addProperty("mobile",mobile);
//        jsonObject.addProperty("hotel_id",hotelid);
//
//        JsonArray jsonArray = new JsonArray();
//        JsonObject jsonObject1 = new JsonObject();
//        jsonObject1.addProperty("name",myOrders.get(0).getName());
//        jsonObject1.addProperty("quantity",myOrders.get(0).getQuantity());
//        jsonObject1.addProperty("extra","none");
//        jsonArray.add(jsonObject1);
//        jsonObject.add("items",jsonArray);
        Button button = findViewById(R.id.postorder);
        jsonObject = new JsonObject();
        jsonObject.addProperty("address",address);
        jsonObject.addProperty("name",name);
        jsonObject.addProperty("mobile",mobile);
        jsonObject.addProperty("hotel_id",hotelid);
        final JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("name",myOrders.get(0).getName());
        jsonObject1.addProperty("quantity",myOrders.get(0).getQuantity());
        jsonObject1.addProperty("extra","none");
        jsonArray.add(jsonObject1);
        jsonObject.add("items",jsonArray);
//        Button button = findViewById(R.id.postorder);
//        final JsonArray jsonArray = new JsonArray();

        String Key1 = "";
        for(int i=0;i<list.size();i++){
            Map<String,List<Cart>> map = list.get(i);
            for (String key  : map.keySet())
            {
                Key1 = key;
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("address",address);
            jsonObject.addProperty("name",name);
            jsonObject.addProperty("mobile",mobile);
            jsonObject.addProperty("hotel_id",Key1);
            JsonArray jsonArray1 = new JsonArray();
            for (List<Cart> value  :map.values())
            {
                for(int k=0;k<value.size();k++){
                    Cart cart = value.get(k);
                    JsonObject jsonObject3 = new JsonObject();
                    jsonObject3.addProperty("name",cart.getName());
                    jsonObject3.addProperty("quantity",cart.getQuantity());
                    jsonObject3.addProperty("extra","none");
                    jsonArray1.add(jsonObject3);
                }
            }
            jsonObject.add("items",jsonArray1);
            jsonArray.add(jsonObject);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("hi",jsonArray.toString());
            }
        });


    }

}
