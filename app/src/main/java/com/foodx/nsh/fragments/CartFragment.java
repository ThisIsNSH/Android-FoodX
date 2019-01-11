package com.foodx.nsh.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodx.nsh.R;
import com.foodx.nsh.adapter.CartAdapter;
import com.foodx.nsh.model.Cart;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String key = "Key";
    private List<Cart> myOrders;
    private RecyclerView recyclerView;
    private Gson gson;
    private Activity activity;
    private CartAdapter cartAdapter;
    private String address,name,mobile,hotelid;
    JsonObject jsonObject;

    public CartFragment() {
    }

    public CartFragment(Activity activity){
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferences = activity.getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        myOrders = new ArrayList<>();
        View view =inflater.inflate(R.layout.fragment_cart, container, false);
        String response=sharedPreferences.getString(key , "null");
        if (!response.equals("null"))
            myOrders = gson.fromJson(response,new TypeToken<List<Cart>>(){}.getType());
        recyclerView = view.findViewById(R.id.recyclerView1);
        cartAdapter = new CartAdapter(myOrders,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        return view;
    }

}
