package com.foodx.nsh.fragments;


import android.annotation.SuppressLint;
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
import com.foodx.nsh.adapter.OrderAdapter;
import com.foodx.nsh.model.OrderArray;
import com.foodx.nsh.model.OrderItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private Activity activity;
    private RecyclerView recyclerView;

    public SettingsFragment() {
    }

    @SuppressLint("ValidFragment")
    public SettingsFragment(Activity activity){
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        SharedPreferences oldOrders = activity.getSharedPreferences("myOldOrders", Context.MODE_PRIVATE);
        SharedPreferences.Editor oldOrderEditor = oldOrders.edit();
        String strJson = oldOrders.getString("oldOrders","[]");
        try{
            JSONArray jsonArray = new JSONArray(strJson);
            List<OrderArray> orderArrayList = new ArrayList<>();

            for (int i=0;i<jsonArray.length();i++){
                List<OrderItem> orderItemList = new ArrayList<>();
                JSONArray innerArray = jsonArray.getJSONArray(i);
                for (int j=0;j<innerArray.length();j++){
                    String order_id = innerArray.getJSONObject(j).getString("_id");
                    String hotel_id = innerArray.getJSONObject(j).getString("hotel_id");
                    JSONArray innerInnerArray = innerArray.getJSONObject(j).getJSONArray("items");
                    for (int k=0;k<innerInnerArray.length();k++){
                        String name = innerInnerArray.getJSONObject(k).getString("name");
                        String quantity = innerInnerArray.getJSONObject(k).getString("quantity");
                        orderItemList.add(new OrderItem(name,quantity,order_id,hotel_id));
                    }
                }
                orderArrayList.add(new OrderArray(orderItemList));
            }

            OrderAdapter orderAdapter = new OrderAdapter(orderArrayList,activity);
            recyclerView.setAdapter(orderAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            orderAdapter.notifyDataSetChanged();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return view;
    }

}
