package com.foodx.nsh;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tzanou.PercentVisibleLayout.PercentVisibleLayout;

public class MainActivity extends AppCompatActivity {
    Animation animFadein, animFadein1;

    private int a = 1, cart = 0;
    private float x1,x2;
    static final int MIN_DISTANCE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);


        final TextView title1 = findViewById(R.id.title1);
        final TextView title2 = findViewById(R.id.title2);
        final TextView menu1 = findViewById(R.id.menu1);
        final TextView menu2 = findViewById(R.id.menu2);
        final PercentVisibleLayout mCustomLayout = (PercentVisibleLayout) findViewById(R.id.custom_layout);
        final PercentVisibleLayout mCustomLayout1 = (PercentVisibleLayout) findViewById(R.id.custom_layout1);





        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slideup);
        animFadein1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slideup1);


        mCustomLayout.setOnVisibilityPercentChangedListener(new PercentVisibleLayout.OnVisibilityPercentChanged() {
            @Override
            public void onVisibilityChange(int fromHeight, int fromWidth, int percentageHeight, int percentageWidth) {

                Log.d("this", "onVisibilityChange: " + percentageHeight);

                if (percentageHeight < 50 && a == 1) {
                    a = 0;
                    title1.startAnimation(animFadein);
                    title2.startAnimation(animFadein);
                    menu1.startAnimation(animFadein1);
                    menu2.startAnimation(animFadein1);
                    menu1.setVisibility(View.VISIBLE);
                    menu2.setVisibility(View.VISIBLE);


                }

            }
        });

        mCustomLayout1.setOnVisibilityPercentChangedListener(new PercentVisibleLayout.OnVisibilityPercentChanged() {
            @Override
            public void onVisibilityChange(int fromHeight, int fromWidth, int percentageHeight, int percentageWidth) {

                Log.d("this", "onVisibilityChange: " + percentageHeight);

                if (percentageHeight < 50 && a == 1)

                {
                    title1.startAnimation(animFadein);
                    title2.startAnimation(animFadein);
                    menu1.startAnimation(animFadein1);
                    menu2.startAnimation(animFadein1);
                    menu1.setVisibility(View.VISIBLE);
                    menu2.setVisibility(View.VISIBLE);


                }


            }
        });



    }

/* public void cart(View view)
   {
       final LinearLayout main = findViewById(R.id.main);
       animFadein2 = AnimationUtils.loadAnimation(getApplicationContext(),
               R.anim.cartenter);
        final TextView back = findViewById(R.id.back);
        main.startAnimation(animFadein2);

       final TextView cart1 = findViewById(R.id.cart1);
       final TextView cart2 = findViewById(R.id.cart2);
       final TextView title1 = findViewById(R.id.title1);
       final TextView title2 = findViewById(R.id.title2);
       final TextView menu1 = findViewById(R.id.menu1);
       final TextView menu2 = findViewById(R.id.menu2);

       Animation animFadein4 = AnimationUtils.loadAnimation(getApplicationContext(),
               R.anim.slideup);
       Animation animFadein5 = AnimationUtils.loadAnimation(getApplicationContext(),
               R.anim.slideright);
       menu1.startAnimation(animFadein4);
       menu2.startAnimation(animFadein4);

       cart1.startAnimation(animFadein5);
       cart2.startAnimation(animFadein5);
        title1.setVisibility(View.INVISIBLE);
        title2.setVisibility(View.INVISIBLE);
        menu1.setVisibility(View.INVISIBLE);
        menu2.setVisibility(View.INVISIBLE);


       animFadein2.setAnimationListener(new Animation.AnimationListener() {
           @Override
           public void onAnimationStart(Animation animation) {
           }

           @Override
           public void onAnimationEnd(Animation animation) {
back.setVisibility(View.VISIBLE);
           }

           @Override
           public void onAnimationRepeat(Animation animation) {

           }
       });



   }

   public void backtomenu(View view)
   {
       LinearLayout main = findViewById(R.id.main);
       animFadein3 = AnimationUtils.loadAnimation(getApplicationContext(),
               R.anim.cartexit);
       TextView back = findViewById(R.id.back);

       main.startAnimation(animFadein3);
       back.setVisibility(View.INVISIBLE);

       final TextView cart1 = findViewById(R.id.cart1);
       final TextView cart2 = findViewById(R.id.cart2);
       final TextView title1 = findViewById(R.id.title1);
       final TextView title2 = findViewById(R.id.title2);
       final TextView menu1 = findViewById(R.id.menu1);
       final TextView menu2 = findViewById(R.id.menu2);

       Animation animFadein4 = AnimationUtils.loadAnimation(getApplicationContext(),
               R.anim.slideleft);
       cart1.startAnimation(animFadein4);
       cart2.startAnimation(animFadein4);
       cart1.setVisibility(View.INVISIBLE);
       cart2.setVisibility(View.INVISIBLE);


       Animation animFadein6 = AnimationUtils.loadAnimation(getApplicationContext(),
               R.anim.slidedown);
       title1.setAnimation(animFadein6);
       title2.setAnimation(animFadein6);
       animFadein6.setAnimationListener(new Animation.AnimationListener() {
           @Override
           public void onAnimationStart(Animation animation) {

           }

           @Override
           public void onAnimationEnd(Animation animation) {
               title1.setVisibility(View.VISIBLE);
               title2.setVisibility(View.VISIBLE);
               menu1.setVisibility(View.INVISIBLE);
               menu2.setVisibility(View.INVISIBLE);
           }

           @Override
           public void onAnimationRepeat(Animation animation) {

           }
       });


   }*/
}


