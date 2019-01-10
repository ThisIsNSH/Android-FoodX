package com.foodx.nsh.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foodx.nsh.R;
import com.foodx.nsh.activity.MenuActivity;
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Hotel hotel = hotelsList.get(position);
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
//                        ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(linearLayout,"translationX", 0, -linearLayout.getWidth());
//                        objectAnimator5.setDuration(300);
//                        objectAnimator5.start();
                        final Intent intent = new Intent(context, MenuActivity.class);
                        intent.putExtra("name",hotel.getName());
                        intent.putExtra("id",hotel.getId());
                        intent.putExtra("color",colors.get(position%7));
                        context.startActivity(intent);
//                        Pair<View, String> p1 = Pair.create((View)holder.card, "card");
//                        Pair<View, String> p2 = Pair.create((View)holder.hotel_name, "name");
//                        Pair<View, String> p3 = Pair.create((View)holder.hotel_image, "image");
//                        Pair<View, String> p4 = Pair.create((View)holder.gradient, "gradient");

//                        final ActivityOptionsCompat options = ActivityOptionsCompat.
//                                makeSceneTransitionAnimation(context, p1,  p4);

//                        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(holder.hotel_name,"alpha", 1,0);
//                        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(holder.hotel_image,"alpha", 1,0);
//                        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(holder.hotel_address,"alpha", 1,0);
//                        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(holder.hotel_phone,"alpha", 1,0);
//
//                        objectAnimator.setDuration(300);
//                        objectAnimator1.setDuration(300);
//                        objectAnimator2.setDuration(300);
//                        objectAnimator3.setDuration(300);
//
//                        AnimatorSet animatorSet = new AnimatorSet();
//                        animatorSet.playTogether(objectAnimator,objectAnimator1,objectAnimator2,objectAnimator3);
//                        animatorSet.start();
//
//                        context.startActivity(intent, options.toBundle());
//                        context.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
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
        }
    }


}
