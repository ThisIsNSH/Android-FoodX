package com.foodx.nsh.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.foodx.nsh.R;
import com.foodx.nsh.fragments.CartFragment;
import com.foodx.nsh.model.Cart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private List<Cart> itemList;
    private Activity context;
    TextView textView1;
    private int max=10;
    private SharedPreferences sharedPreferences,Bill;
    String key = "Key";
    SharedPreferences.Editor editor,editor2;
    Gson gson;
    List<Cart> myOrders;
    String totalPrice;
    public CartAdapter(List<Cart> itemList, Activity context) {
        this.itemList = itemList;
        this.context = context;
    }
    public Cart getItem(int position)
    {
        return itemList.get(position);
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cart, parent, false);
        return new MyViewHolder(itemView);
    }

    public Cart getCart(int position) {
        return itemList.get(position);
    }
    public void delete(int position)
    {
        itemList.remove(position);
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        myOrders = new ArrayList<>();
        sharedPreferences = context.getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        String response=sharedPreferences.getString(key , "null");
        Log.v("FUCKYOUTODAY",response);
        if (!response.equals("null"))
            myOrders = gson.fromJson(response,new TypeToken<List<Cart>>(){}.getType());
//        Log.v("FOOKERY", String.valueOf(myOrders));
        final Cart cart = itemList.get(position);
        holder.textView.setText(cart.getName());
        textView1.setText(cart.getQuantity());
//        holder.textView2.setText(cart.getHotelId());
        totalPrice = String.valueOf((Integer.parseInt(textView1.getText().toString()) * Integer.parseInt(cart.getPrice())));
        holder.textView3.setText(totalPrice);
        Bill = context.getSharedPreferences("BILL",Context.MODE_PRIVATE);
        editor2 = Bill.edit();
        editor2.putString(String.valueOf(position),totalPrice);
        editor2.apply();
//        Log.v("CHECKCHECK",cart.getPrice());
        if(cart.getName().equals("Roti")) {
            max = 50;
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView1.getText().toString().equals(String.valueOf(max)))
                {
                    holder.button.setEnabled(false);
                    holder.button1.setEnabled(true);
                }
                else{
                    int quantity = Integer.parseInt(textView1.getText().toString());
                    textView1.setText(String.valueOf(quantity+1));
                    holder.button1.setEnabled(true);
                }
                totalPrice = String.valueOf((Integer.parseInt(textView1.getText().toString()) * Integer.parseInt(cart.getPrice())));
                holder.textView3.setText(totalPrice);
                myOrders.get(position).finalQuantity(textView1.getText().toString());
                String json = gson.toJson(myOrders);
                Log.v("menulist", json);
                editor.putString(key, json);
                editor.apply();
                Bill = context.getSharedPreferences("BILL",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2;
                editor2 = Bill.edit();
                editor2.putString(String.valueOf(position),totalPrice);
                editor2.apply();
            }

        });
        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(textView1.getText().toString().equals("0")){
                    holder.button.setEnabled(true);
                    holder.button1.setEnabled(false);
                }
                else{
                    int quantity = Integer.parseInt(textView1.getText().toString());
                    textView1.setText(String.valueOf(quantity-1));
                    holder.button.setEnabled(true);

                }
                totalPrice = String.valueOf((Integer.parseInt(textView1.getText().toString()) * Integer.parseInt(cart.getPrice())));
                holder.textView3.setText(totalPrice);
                myOrders.get(position).finalQuantity(textView1.getText().toString());
                String json = gson.toJson(myOrders);
                Log.v("menulist", json);
                editor.putString(key, json);
                editor.apply();
                Bill = context.getSharedPreferences("BILL",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2;
                editor2 = Bill.edit();
                editor2.putString(String.valueOf(position),totalPrice);
                editor2.apply();
            }
        });
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView,textView2,textView3;
        Button button,button1;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_name);
            textView1 = itemView.findViewById(R.id.Quantity);
            textView2 = itemView.findViewById(R.id.hotelid);
            textView3 = itemView.findViewById(R.id.price);
            button = itemView.findViewById(R.id.addition1);
            button1 = itemView.findViewById(R.id.subtraction1);
        }
    }


}