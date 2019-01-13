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
import android.widget.Toast;

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
        holder.textView1.setText(cart.getQuantity());
//        holder.textView2.setText(cart.getHotelId());
        totalPrice = String.valueOf((Integer.parseInt(holder.textView1.getText().toString()) * Integer.parseInt(cart.getPrice())));
        holder.textView3.setText(totalPrice);
        editor.apply();
//        Bill = context.getSharedPreferences("BILL",Context.MODE_PRIVATE);
//        editor2 = Bill.edit();
//        editor2.putString(String.valueOf(position),totalPrice);
//        editor2.apply();
//        Log.v("CHECKCHECK",cart.getPrice());
        if(cart.getName().equals("Roti")) {
            max = 50;
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.textView1.getText().toString().equals(String.valueOf(max)))
                {
                    holder.button.setEnabled(false);
                    holder.button1.setEnabled(true);
                }
                else{
                    int quantity = Integer.parseInt(holder.textView1.getText().toString());
                    holder.textView1.setText(String.valueOf(quantity+1));
                    holder.button1.setEnabled(true);
                }
                totalPrice = String.valueOf((Integer.parseInt(holder.textView1.getText().toString()) * Integer.parseInt(cart.getPrice())));
                holder.textView3.setText(totalPrice);
                myOrders.get(position).finalQuantity(holder.textView1.getText().toString());
                String json = gson.toJson(myOrders);
                Log.v("menulist", json);
                editor.putString(key, json);
                editor.apply();
//                Bill = context.getSharedPreferences("BILL",Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor2;
//                editor2 = Bill.edit();
//                editor2.putString(String.valueOf(position),totalPrice);
//                editor2.apply();
            }

        });
        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.textView1.getText().toString().equals("0")){
                    holder.button.setEnabled(true);
                    holder.button1.setEnabled(false);
                }
                else{
// <<<<<<< Fixing
                    int quantity = Integer.parseInt(holder.textView1.getText().toString());
                        holder.textView1.setText(String.valueOf(quantity - 1));
                        holder.button.setEnabled(true);
//                    else
//                        Toast.makeText(context, "At least one item is required", Toast.LENGTH_SHORT).show();
// =======
//                     int quantity = Integer.parseInt(textView1.getText().toString());
//                     if (quantity - 1 != 0) {
//                         textView1.setText(String.valueOf(quantity - 1));
//                         holder.button.setEnabled(true);
//                     }
//                     else
//                         Toast.makeText(context, "At least one item is required", Toast.LENGTH_SHORT).show();
// >>>>>>> v2
                }
                totalPrice = String.valueOf((Integer.parseInt(holder.textView1.getText().toString()) * Integer.parseInt(cart.getPrice())));
                holder.textView3.setText(totalPrice);
                myOrders.get(position).finalQuantity(holder.textView1.getText().toString());
                String json = gson.toJson(myOrders);
                Log.v("menulist", json);
                editor.putString(key, json);
                editor.apply();
//                Bill = context.getSharedPreferences("BILL",Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor2;
//                editor2 = Bill.edit();
//                editor2.putString(String.valueOf(position),totalPrice);
//                editor2.apply();
            }
        });
        holder.textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                itemList.remove(position);
                myOrders.remove(position);
                String json = gson.toJson(myOrders);
                Log.v("menulist", json);
                editor.putString(key, json);
                editor.apply();
//                Bill = context.getSharedPreferences("BILL",Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor2;
//                editor2 = Bill.edit();
// <<<<<<< Fixing
// =======
//                editor2.putString(String.valueOf(position),totalPrice);
//                editor2.apply();
            }
        });
        holder.textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                itemList.remove(position);
                myOrders.remove(position);
                String json = gson.toJson(myOrders);
                Log.v("menulist", json);
                editor.putString(key, json);
                editor.apply();
//                Bill = context.getSharedPreferences("BILL",Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor2;
//                editor2 = Bill.edit();
// >>>>>>> v2
//                editor2.remove(String.valueOf(position));
//                editor2.apply();
                notifyItemRemoved(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

// <<<<<<< Fixing
        TextView textView,textView2,textView3,textView4,textView1;

        Button button,button1;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_name);
            textView1 = itemView.findViewById(R.id.Quantity);
            textView2 = itemView.findViewById(R.id.hotelid);
            textView3 = itemView.findViewById(R.id.price);
            button = itemView.findViewById(R.id.addition1);
            button1 = itemView.findViewById(R.id.subtraction1);
            textView4 = itemView.findViewById(R.id.remove);
        }
    }


}