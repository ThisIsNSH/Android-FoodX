package com.mangwalo.nsh.adapter;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mangwalo.nsh.R;
import com.mangwalo.nsh.model.OrderArray;

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
        holder.total.setText("Total: "+String.valueOf(orderArray.getTotal()));
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
        TextView total;

        public MyViewHolder(View itemView) {
            super(itemView);
            total = itemView.findViewById(R.id.total);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            this.setIsRecyclable(false);
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}