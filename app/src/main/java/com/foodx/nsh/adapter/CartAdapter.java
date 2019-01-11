package com.foodx.nsh.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.foodx.nsh.R;
import com.foodx.nsh.model.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

        List<Cart> itemList;
        Activity context;
        int max=10;
public CartAdapter(List<Cart> itemList, Activity context) {
        this.itemList = itemList;
        this.context = context;
        }

@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new MyViewHolder(itemView);
        }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
    final Cart cart = itemList.get(position);
    holder.textView.setText(cart.getName());
    holder.textView1.setText(cart.getQuantity());
    holder.textView2.setText(cart.getHotelId());
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
                    int quantity = Integer.parseInt(holder.textView1.getText().toString());
                    holder.textView1.setText(String.valueOf(quantity-1));
                    holder.button.setEnabled(true);

                }
            }
        });

    }


@Override
public int getItemCount() {
        return itemList.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView textView,textView1,textView2;
    Button button,button1;
    public MyViewHolder(View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.item_name);
        textView1 = itemView.findViewById(R.id.Quantity);
        textView2 = itemView.findViewById(R.id.hotelid);
        button = itemView.findViewById(R.id.addition1);
        button1 = itemView.findViewById(R.id.subtraction1);
    }
}


}