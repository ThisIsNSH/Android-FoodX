package com.mangwalo.nsh.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mangwalo.nsh.R;
import com.mangwalo.nsh.activity.MenuActivity;
import com.mangwalo.nsh.model.Hotel;
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
    DatabaseReference reference;
    boolean hello = true;

    public HotelAdapter(List<Hotel> hotelsList, Activity context) {
        this.hotelsList = hotelsList;
        this.context = context;
        colors.clear();
        colors.add(R.color.color2);
        colors.add(R.color.color2);
        colors.add(R.color.color2);
        colors.add(R.color.color2);
        colors.add(R.color.color2);
        colors.add(R.color.color2);
        colors.add(R.color.color2);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        reference = FirebaseDatabase.getInstance().getReference("Hotels");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_hotel, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Hotel hotel = hotelsList.get(position);
//        holder.card.setCardBackgroundColor(context.getResources().getColor(colors.get(position%7)));
        holder.hotel_name.setText(hotel.getName());
        holder.hotel_phone.setText(hotel.getContact());
        holder.hotel_address.setText(hotel.getAddress());
        Picasso.get().load(hotel.getImage()).into(holder.hotel_image);

        holder.card.setClickable(false);
        holder.card.setEnabled(false);
//        holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.invalid));

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    try {
                        if (snapshot.getKey().equals(hotel.getName())) {
                            hello = (boolean) snapshot.getValue();
//                        Log.e("hello", String.valueOf(hello));
                            if (hello) {
                                holder.card.setClickable(true);
                                holder.card.setEnabled(true);
                                holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.color2));
                            } else {
                                holder.card.setClickable(false);
                                holder.card.setEnabled(false);
                                holder.hotel_address.setText("Now Closed");
                                holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.invalid));
                            }
                        }
                    }catch (NullPointerException e){
                        holder.card.setClickable(false);
                        holder.card.setEnabled(false);
                        holder.hotel_address.setText("Now Closed");
                        holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.invalid));
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });




        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(holder.card, "scaleX", 0.95f);
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(holder.card, "scaleX", 1f);
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(holder.card, "scaleY", 0.95f);
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
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        final Intent intent = new Intent(context, MenuActivity.class);
                        intent.putExtra("name",hotel.getName());
                        intent.putExtra("id",hotel.getId());
                        intent.putExtra("color",R.color.color2);
                        context.startActivity(intent);
                        context.overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotelsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView hotel_image,gradient;
        TextView hotel_name, hotel_phone, hotel_address;
        CardView card;

        public MyViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            gradient = itemView.findViewById(R.id.gradient);
            hotel_name = itemView.findViewById(R.id.hotel_name);
            hotel_phone = itemView.findViewById(R.id.hotel_phone);
            hotel_address = itemView.findViewById(R.id.hotel_address);
            hotel_image = itemView.findViewById(R.id.hotel_image);
            this.setIsRecyclable(false);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
