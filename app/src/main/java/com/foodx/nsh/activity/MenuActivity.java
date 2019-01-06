package com.foodx.nsh.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.foodx.nsh.R;
import com.squareup.picasso.Picasso;

public class MenuActivity extends AppCompatActivity {

    private ImageView hotelImage;
    private CardView cardView;
    private TextView hotelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        onInit();
    }

    public void onInit(){
        hotelImage = findViewById(R.id.hotel_image);
        hotelName = findViewById(R.id.hotel_name);
        cardView = findViewById(R.id.card);

        Bundle bundle = getIntent().getExtras();

        cardView.setCardBackgroundColor(getResources().getColor(bundle.getInt("color")));
        hotelName.setText(bundle.getString("name"));

        Picasso.get().load("https://www.foxrc.com/wp-content/themes/frc/static/images/plate2.png").into(hotelImage);

//        hotelImage.setVisibility(View.VISIBLE);
//        hotelName.setVisibility(View.VISIBLE);
//        hotelImage.setAlpha(0f);
//        hotelName.setAlpha(0f);
//
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(hotelImage,"alpha", 0,1);
//        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(hotelName,"alpha", 0,1);
//
//        objectAnimator.setDuration(1000);
//        objectAnimator1.setDuration(1000);
//
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(objectAnimator,objectAnimator1);
//        animatorSet.start();
//        animatorSet.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//
//            }
//
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//                hotelImage.setVisibility(View.VISIBLE);
//                hotelImage.setVisibility(View.VISIBLE);
//                hotelName.setAlpha(1f);
//                hotelImage.setAlpha(1f);
//            }
//        });
    }
}
