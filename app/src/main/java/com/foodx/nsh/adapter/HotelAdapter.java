package com.foodx.nsh.adapter;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodx.nsh.R;
import com.foodx.nsh.model.Hotel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThisIsNSH on 1/5/2019.
 */

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.MyViewHolder> {

    List<Hotel> hotelsList;
    Activity context;
    ArrayList<Integer> colors = new ArrayList<Integer>();

    public HotelAdapter(List<Hotel> hotelsList, Activity context) {
        this.hotelsList = hotelsList;
        this.context = context;
        colors.clear();
        colors.add(R.color.color0);
        colors.add(R.color.color1);
        colors.add(R.color.color2);
        colors.add(R.color.color3);
        colors.add(R.color.color4);
        colors.add(R.color.color5);
        colors.add(R.color.color6);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_hotel, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Hotel hotel = hotelsList.get(position);
        holder.card.setCardBackgroundColor(context.getResources().getColor(colors.get(position%7)));
        holder.hotel_name.setText(hotel.getName());
        holder.hotel_phone.setText(hotel.getContact());
        holder.hotel_address.setText(hotel.getAddress());
        Picasso.get().load(hotel.getImage()).into(holder.hotel_image);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            StateListAnimator stateListAnimator = AnimatorInflater
//                    .loadStateListAnimator(context, R.animator.lift_on_touch);
//            holder.card.setStateListAnimator(stateListAnimator);
//        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(holder.card, "scaleX", 1.05f);
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(holder.card, "scaleX", 1f);
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(holder.card, "scaleY", 1.05f);
                ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(holder.card, "scaleY", 1f);

                objectAnimator.setDuration(200);
                objectAnimator1.setDuration(100);
                objectAnimator2.setDuration(200);
                objectAnimator3.setDuration(100);

                objectAnimator1.setStartDelay(200);
                objectAnimator3.setStartDelay(200);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimator,objectAnimator1,objectAnimator2,objectAnimator3);
                animatorSet.start();

            }
        });
    }

    @Override
    public int getItemCount() {
        return hotelsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView hotel_image;
        TextView hotel_name, hotel_phone, hotel_address;
        CardView card;

        public MyViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            hotel_name = itemView.findViewById(R.id.hotel_name);
            hotel_phone = itemView.findViewById(R.id.hotel_phone);
            hotel_address = itemView.findViewById(R.id.hotel_address);
            hotel_image = itemView.findViewById(R.id.hotel_image);
        }
    }


}
