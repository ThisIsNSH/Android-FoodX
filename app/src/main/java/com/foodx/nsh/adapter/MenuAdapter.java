package com.foodx.nsh.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodx.nsh.R;
import com.foodx.nsh.activity.MenuActivity;
import com.foodx.nsh.model.Hotel;
import com.foodx.nsh.model.Menu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThisIsNSH on 1/5/2019.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    List<Menu> menuList;
    Activity context;
    int color;
    ItemAdapter itemAdapter;

    public MenuAdapter(int color, List<Menu> menuList, Activity context) {
        this.menuList = menuList;
        this.context = context;
        this.color = color;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_menu, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Menu menu = menuList.get(position);
        holder.card.setCardBackgroundColor(context.getResources().getColor(color));
        holder.category_name.setText(menu.getCategory());
        itemAdapter = new ItemAdapter(menu.getItemList(),context);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(itemAdapter);
        Picasso.get().load("https://i.dlpng.com/static/png/151888_preview.png").into(holder.image);


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
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView category_name;
        RecyclerView recyclerView;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            card = itemView.findViewById(R.id.card);
            category_name = itemView.findViewById(R.id.category_name);
            recyclerView = itemView.findViewById(R.id.recyclerView);
        }
    }


}
