package com.foodx.nsh.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.foodx.nsh.R;
import com.foodx.nsh.model.Cart;
import com.foodx.nsh.model.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThisIsNSH on 1/5/2019.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    List<Item> itemList;
    Activity context;
    int max=10;
    String hotelid="";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson = new Gson();
    String key = "Key";
    int color;
    List<Cart> myOrders = new ArrayList<>();

    public ItemAdapter(List<Item> itemList, Activity context,String hotelid,int color) {
        this.color = color;
        this.itemList = itemList;
        this.context = context;
        this.hotelid = hotelid;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        sharedPreferences = context.getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String response=sharedPreferences.getString(key , "null");
        if (!response.equals("null"))
            myOrders = gson.fromJson(response,new TypeToken<List<Cart>>(){}.getType());
        final Item item = itemList.get(position);
        holder.name.setText(item.getName());
        holder.price.setText(item.getPrice());
//        Picasso.get().load(item.getImage()).into(holder.image);
        Picasso.get().load("https://i.dlpng.com/static/png/151888_preview.png").into(holder.image1);
        holder.button.setBackground(context.getResources().getDrawable(drawable(color)));
        holder.button1.setBackground(context.getResources().getDrawable(drawable(color)));
        holder.button2.setBackground(context.getResources().getDrawable(drawable(color)));

        if(item.getName().equals("Roti")) {
            max = 50;
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.quantity.getText().toString().equals(String.valueOf(max)))
                {
                    holder.button.setEnabled(false);
                    holder.button1.setEnabled(true);
                }
                else{
                    int quantity = Integer.parseInt(holder.quantity.getText().toString());
                    holder.quantity.setText(String.valueOf(quantity+1));
                    holder.button1.setEnabled(true);

                }
                if(!holder.quantity.getText().toString().equals("0")){
                    holder.button2.setVisibility(View.VISIBLE);
                }
                else {
                    holder.button2.setVisibility(View.GONE);
                }


            }
        });
        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.quantity.getText().toString().equals("0")){
                    holder.button.setEnabled(true);
                    holder.button1.setEnabled(false);
                }
                else{
                    int quantity = Integer.parseInt(holder.quantity.getText().toString());
                    holder.quantity.setText(String.valueOf(quantity-1));
                    holder.button.setEnabled(true);

                }
                if(!holder.quantity.getText().toString().equals("0")){
                    holder.button2.setVisibility(View.VISIBLE);
                }
                else {
                    holder.button2.setVisibility(View.GONE);
                }
            }
        });

        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = new Cart(item.getName(),holder.quantity.getText().toString(),hotelid);
                myOrders.add(cart);
                String json = gson.toJson(myOrders);
                editor.putString(key,json);
                editor.apply();
                Toast.makeText(context,"Your item has been added to the cart.",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,quantity;
        ImageView image,image1;
        Button button,button1,button2;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            image1 = itemView.findViewById(R.id.image1);
            image = itemView.findViewById(R.id.image);
            price = itemView.findViewById(R.id.price);
            button = itemView.findViewById(R.id.addition);
            button1 = itemView.findViewById(R.id.subtraction);
            button2 = itemView.findViewById(R.id.addtocart);
            quantity = itemView.findViewById(R.id.quantity);
        }
    }

    public int drawable(int color){
        switch (color){
            case R.color.color0 :
                return R.drawable.curvedback;
            case R.color.color1 :
                return R.drawable.curvedback1;
            case R.color.color2 :
                return R.drawable.curvedback2;
            case R.color.color3 :
                return R.drawable.curvedback3;
            case R.color.color4 :
                return R.drawable.curvedback4;
            case R.color.color5 :
                return R.drawable.curvedback5;
            case R.color.color6 :
                return R.drawable.curvedback6;
        }
        return R.drawable.curvedback6;
    }
}
