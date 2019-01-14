package com.foodx.nsh.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foodx.nsh.R;
import com.foodx.nsh.model.Cart;
import com.foodx.nsh.model.OrderArray;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    List<OrderArray> orderArrayList;
    Activity context;

    public OrderAdapter(List<OrderArray> orderArrayList, Activity context) {
        this.orderArrayList = orderArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_order, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        OrderArray orderArray = orderArrayList.get(position);
        OrderItemAdapter orderItemAdapter = new OrderItemAdapter(orderArray.getItemList(),context);
        holder.recyclerView.setAdapter(orderItemAdapter);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        orderItemAdapter.notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public MyViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerView);
        }
    }


}