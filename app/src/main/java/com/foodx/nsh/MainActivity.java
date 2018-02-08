package com.foodx.nsh;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.tzanou.PercentVisibleLayout.PercentVisibleLayout;

import jp.wasabeef.blurry.Blurry;

public class MainActivity extends AppCompatActivity {

    public static int q1 = 0, q2 = 0, q3 = 0, q4 = 0, q5 = 0, q6 = 0, q7 = 0, q8 = 0, q9 = 0, q10 = 0;
    public static int p1 = 10, p2 = 10, p3 = 10, p4 = 10, p5 = 10, p6 = 10, p7 = 10, p8 = 10, p9 = 10, p10 = 10;
    public static int total = 0;
    public int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        final TextView quantity1 = findViewById(R.id.quantity1);
        final TextView quantity2 = findViewById(R.id.quantity2);
        final TextView quantity3 = findViewById(R.id.quantity3);
        final TextView quantity4 = findViewById(R.id.quantity4);
        final TextView quantity5 = findViewById(R.id.quantity5);
        final TextView quantity6 = findViewById(R.id.quantity6);
        final TextView quantity7 = findViewById(R.id.quantity7);
        final TextView quantity8 = findViewById(R.id.quantity8);
        final TextView quantity9 = findViewById(R.id.quantity9);
        final TextView quantity10 = findViewById(R.id.quantity10);

        final TextView amount1 = findViewById(R.id.amount1);
        final TextView amount2 = findViewById(R.id.amount2);
        final TextView amount3 = findViewById(R.id.amount3);
        final TextView amount4 = findViewById(R.id.amount4);
        final TextView amount5 = findViewById(R.id.amount5);
        final TextView amount6 = findViewById(R.id.amount6);
        final TextView amount7 = findViewById(R.id.amount7);
        final TextView amount8 = findViewById(R.id.amount8);
        final TextView amount9 = findViewById(R.id.amount9);
        final TextView amount10 = findViewById(R.id.amount10);

        final ImageButton add1 = findViewById(R.id.add1);
        final ImageButton add2 = findViewById(R.id.add2);
        final ImageButton add3 = findViewById(R.id.add3);
        final ImageButton add4 = findViewById(R.id.add4);
        final ImageButton add5 = findViewById(R.id.add5);
        final ImageButton add6 = findViewById(R.id.add6);
        final ImageButton add7 = findViewById(R.id.add7);
        final ImageButton add8 = findViewById(R.id.add8);
        final ImageButton add9 = findViewById(R.id.add9);
        final ImageButton add10 = findViewById(R.id.add10);

        final ImageButton sub1 = findViewById(R.id.sub1);
        final ImageButton sub2 = findViewById(R.id.sub2);
        final ImageButton sub3 = findViewById(R.id.sub3);
        final ImageButton sub4 = findViewById(R.id.sub4);
        final ImageButton sub5 = findViewById(R.id.sub5);
        final ImageButton sub6 = findViewById(R.id.sub6);
        final ImageButton sub7 = findViewById(R.id.sub7);
        final ImageButton sub8 = findViewById(R.id.sub8);
        final ImageButton sub9 = findViewById(R.id.sub9);
        final ImageButton sub10 = findViewById(R.id.sub10);

        final TextView real1 = findViewById(R.id.real1);
        final TextView real2 = findViewById(R.id.real2);
        final TextView real3 = findViewById(R.id.real3);
        final TextView real4 = findViewById(R.id.real4);
        final TextView real5 = findViewById(R.id.real5);
        final TextView real6 = findViewById(R.id.real6);
        final TextView real7 = findViewById(R.id.real7);
        final TextView real8 = findViewById(R.id.real8);
        final TextView real9 = findViewById(R.id.real9);
        final TextView real10 = findViewById(R.id.real10);



  /*      final ImageView image1a = findViewById(R.id.image1a);
        final ImageView image2a = findViewById(R.id.image2a);
        final ImageView image3a = findViewById(R.id.image3a);
        final ImageView image4a = findViewById(R.id.image4a);
        final ImageView image5a = findViewById(R.id.image5a);
        final ImageView image6a = findViewById(R.id.image6a);
        final ImageView image7a = findViewById(R.id.image7a);
        final ImageView image8a = findViewById(R.id.image8a);
        final ImageView image9a = findViewById(R.id.image9a);
        final ImageView image10a = findViewById(R.id.image10a);*/

       /* final TextView menu1 = findViewById(R.id.menu1);
        final TextView menu2 = findViewById(R.id.menu2);*/

        final LottieAnimationView anim = findViewById(R.id.menu_anim);

        final TextView total1 = findViewById(R.id.total1);
        final TextView total2 = findViewById(R.id.total2);
        final TextView total3 = findViewById(R.id.total3);

        final ImageButton cart = findViewById(R.id.cart);
        final ImageButton info = findViewById(R.id.info);

        final LinearLayout main = findViewById(R.id.main);
        final LinearLayout g1 = findViewById(R.id.group1);
        final LinearLayout g2 = findViewById(R.id.group2);
        final LinearLayout g3 = findViewById(R.id.group3);
        final LinearLayout g4 = findViewById(R.id.group4);
        final LinearLayout g5 = findViewById(R.id.group5);

        final ImageView image1 = findViewById(R.id.image1);
        final ImageView image2 = findViewById(R.id.image2);
        final ImageView image3 = findViewById(R.id.image3);
        final ImageView image4 = findViewById(R.id.image4);
        final ImageView image5 = findViewById(R.id.image5);
        final ImageView image6 = findViewById(R.id.image6);
        final ImageView image7 = findViewById(R.id.image7);
        final ImageView image8 = findViewById(R.id.image8);
        final ImageView image9 = findViewById(R.id.image9);
        final ImageView image10 = findViewById(R.id.image10);

        final TextView title1 = findViewById(R.id.title1);
        final TextView title2 = findViewById(R.id.title2);
        final TextView title3 = findViewById(R.id.title3);
        final TextView title4 = findViewById(R.id.title4);
        final TextView title5 = findViewById(R.id.title5);
        final TextView title6 = findViewById(R.id.title6);
        final TextView title7 = findViewById(R.id.title7);
        final TextView title8 = findViewById(R.id.title8);
        final TextView title9 = findViewById(R.id.title9);
        final TextView title10 = findViewById(R.id.title10);

        title1.setText("Loading");
        title2.setText("Loading");
        title3.setText("Loading");
        title4.setText("Loading");
        title5.setText("Loading");
        title6.setText("Loading");
        title7.setText("Loading");
        title8.setText("Loading");
        title9.setText("Loading");
        title10.setText("Loading");

        final TextView price1 = findViewById(R.id.price1);
        final TextView price2 = findViewById(R.id.price2);
        final TextView price3 = findViewById(R.id.price3);
        final TextView price4 = findViewById(R.id.price4);
        final TextView price5 = findViewById(R.id.price5);
        final TextView price6 = findViewById(R.id.price6);
        final TextView price7 = findViewById(R.id.price7);
        final TextView price8 = findViewById(R.id.price8);
        final TextView price9 = findViewById(R.id.price9);
        final TextView price10 = findViewById(R.id.price10);

        price1.setText("Loading");
        price2.setText("Loading");
        price3.setText("Loading");
        price4.setText("Loading");
        price5.setText("Loading");
        price6.setText("Loading");
        price7.setText("Loading");
        price8.setText("Loading");
        price9.setText("Loading");
        price10.setText("Loading");


        //firebase database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference("title1");
        DatabaseReference myRef2 = database.getReference("title2");
        DatabaseReference myRef3 = database.getReference("title3");
        DatabaseReference myRef4 = database.getReference("title4");



        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                title1.setBackgroundColor(getResources().getColor(R.color.transparent));
                title1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                title2.setBackgroundColor(getResources().getColor(R.color.transparent));
                title2.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });

        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                title3.setBackgroundColor(getResources().getColor(R.color.transparent));
                title3.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });

        myRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                title4.setBackgroundColor(getResources().getColor(R.color.transparent));
                title4.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("this", "Failed to read value.", error.toException());
            }
        });





        //firebase storage
        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
        final StorageReference imgRef1 = mStorageRef.child("images/img1.jpg");
        final StorageReference imgRef2 = mStorageRef.child("images/img2.jpg");
        final StorageReference imgRef3 = mStorageRef.child("images/img3.jpg");
        final StorageReference imgRef4 = mStorageRef.child("images/img4.jpg");


        GlideApp.with(this )
                .load(imgRef1)
                .centerCrop()
                .placeholder(R.drawable.base)
                .error(R.drawable.base)
                .fallback(R.drawable.base)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(image1);
        GlideApp.with(this )
                .load(imgRef2)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.base)
                .error(R.drawable.base)
                .fallback(R.drawable.base)
                .into(image2);
        GlideApp.with(this )
                .load(imgRef3)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.base)
                .error(R.drawable.base)
                .fallback(R.drawable.base)
                .into(image3);
        GlideApp.with(this )
                .load(imgRef4)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.base)
                .error(R.drawable.base)
                .fallback(R.drawable.base)
                .into(image4);



        //animation
        Animation main_anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slideup_main);
        main_anim.setStartOffset(200);
        g1.startAnimation(main_anim);

        Animation main_anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slideup_main);
        main_anim1.setStartOffset(400);
        g2.startAnimation(main_anim1);

        Animation main_anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slideup_main);
        main_anim2.setStartOffset(600);
        g3.startAnimation(main_anim2);

        Animation main_anim3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slideup_main);
        main_anim3.setStartOffset(800);
        g4.startAnimation(main_anim3);

        Animation main_anim4 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.slideup_main);
        main_anim4.setStartOffset(1000);
        g5.startAnimation(main_anim4);


        //Clicking Listeners begin here

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q1 < 15)
                    q1 += 1;
                if (q1 > 0) {
                    quantity1.setText(Integer.toString(q1 * p1));
                    total += (p1);
                    quantity1.setVisibility(View.VISIBLE);
                    real1.setText(Integer.toString(q1));
                    real1.setVisibility(View.VISIBLE);
                    amount1.setVisibility(View.VISIBLE);
                } else {
                    quantity1.setVisibility(View.INVISIBLE);
                    real1.setVisibility(View.INVISIBLE);
                    amount1.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q2 < 15)
                    q2 += 1;
                if (q2 > 0) {
                    quantity2.setText(Integer.toString(q2 * p2));
                    total += (p2);
                    quantity2.setVisibility(View.VISIBLE);
                    real2.setText(Integer.toString(q2));
                    real2.setVisibility(View.VISIBLE);
                    amount2.setVisibility(View.VISIBLE);
                } else {
                    quantity2.setVisibility(View.INVISIBLE);
                    real2.setVisibility(View.INVISIBLE);
                    amount2.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q3 < 15)
                    q3 += 1;
                if (q3 > 0) {
                    quantity3.setText(Integer.toString(q3 * p3));
                    total += (p3);
                    quantity3.setVisibility(View.VISIBLE);
                    real3.setText(Integer.toString(q3));
                    real3.setVisibility(View.VISIBLE);
                    amount3.setVisibility(View.VISIBLE);
                } else {
                    quantity3.setVisibility(View.INVISIBLE);
                    real3.setVisibility(View.INVISIBLE);
                    amount3.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q4 < 15)
                    q4 += 1;
                if (q4 > 0) {
                    quantity4.setText(Integer.toString(q4 * p4));
                    total += (p4);
                    quantity4.setVisibility(View.VISIBLE);
                    real4.setText(Integer.toString(q4));

                    real4.setVisibility(View.VISIBLE);
                    amount4.setVisibility(View.VISIBLE);
                } else {
                    quantity4.setVisibility(View.INVISIBLE);
                    real4.setVisibility(View.INVISIBLE);
                    amount4.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q5 < 15)
                    q5 += 1;
                if (q5 > 0) {
                    quantity5.setText(Integer.toString(q5 * p5));
                    total += (p5);
                    quantity5.setVisibility(View.VISIBLE);
                    real5.setText(Integer.toString(q5));
                    real5.setVisibility(View.VISIBLE);
                    amount5.setVisibility(View.VISIBLE);
                } else {
                    quantity5.setVisibility(View.INVISIBLE);
                    real5.setVisibility(View.INVISIBLE);
                    amount5.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q6 < 15)
                    q6 += 1;
                if (q6 > 0) {
                    quantity6.setText(Integer.toString(q6 * p6));
                    total += (p6);
                    quantity6.setVisibility(View.VISIBLE);
                    real6.setText(Integer.toString(q6));
                    real6.setVisibility(View.VISIBLE);
                    amount6.setVisibility(View.VISIBLE);
                } else {
                    quantity6.setVisibility(View.INVISIBLE);
                    real6.setVisibility(View.INVISIBLE);
                    amount6.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q7 < 15)
                    q7 += 1;
                if (q7 > 0) {
                    quantity7.setText(Integer.toString(q7 * p7));
                    total += (p7);
                    quantity7.setVisibility(View.VISIBLE);
                    real7.setText(Integer.toString(q7));
                    real7.setVisibility(View.VISIBLE);
                    amount7.setVisibility(View.VISIBLE);
                } else {
                    quantity7.setVisibility(View.INVISIBLE);
                    real7.setVisibility(View.INVISIBLE);
                    amount7.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q8 < 15)
                    q8 += 1;
                if (q8 > 0) {
                    quantity8.setText(Integer.toString(q8 * p8));
                    total += (p8);
                    quantity8.setVisibility(View.VISIBLE);
                    real8.setText(Integer.toString(q8));
                    real8.setVisibility(View.VISIBLE);
                    amount8.setVisibility(View.VISIBLE);
                } else {
                    quantity8.setVisibility(View.INVISIBLE);
                    real8.setVisibility(View.INVISIBLE);
                    amount8.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q9 < 15)
                    q9 += 1;
                if (q9 > 0) {
                    quantity9.setText(Integer.toString(q9 * p9));
                    total += (p9);
                    quantity9.setVisibility(View.VISIBLE);
                    real9.setText(Integer.toString(q9));
                    real9.setVisibility(View.VISIBLE);
                    amount9.setVisibility(View.VISIBLE);
                } else {
                    quantity9.setVisibility(View.INVISIBLE);
                    real9.setVisibility(View.INVISIBLE);
                    amount9.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        add10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q10 < 15)
                    q10 += 1;
                if (q10 > 0) {
                    quantity10.setText(Integer.toString(q10 * p10));
                    total += (p10);
                    quantity10.setVisibility(View.VISIBLE);
                    real10.setText(Integer.toString(q10));
                    real10.setVisibility(View.VISIBLE);
                    amount10.setVisibility(View.VISIBLE);
                } else {
                    quantity10.setVisibility(View.INVISIBLE);
                    real10.setVisibility(View.INVISIBLE);
                    amount10.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                }

            }
        });

        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q1 > 0) {
                    q1 -= 1;
                    total -= (p1);
                }
                if (q1 > 0) {
                    quantity1.setText(Integer.toString(q1 * p1));

                    quantity1.setVisibility(View.VISIBLE);
                    real1.setText(Integer.toString(q1));
                    real1.setVisibility(View.VISIBLE);
                    amount1.setVisibility(View.VISIBLE);
                } else {
                    quantity1.setVisibility(View.INVISIBLE);
                    real1.setVisibility(View.INVISIBLE);
                    amount1.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });

        sub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q2 > 0) {
                    total -= (p2);
                    q2 -= 1;
                }
                if (q2 > 0) {
                    quantity2.setText(Integer.toString(q2 * p2));

                    quantity2.setVisibility(View.VISIBLE);
                    real2.setText(Integer.toString(q2));
                    real2.setVisibility(View.VISIBLE);
                    amount2.setVisibility(View.VISIBLE);
                } else {
                    quantity2.setVisibility(View.INVISIBLE);
                    real2.setVisibility(View.INVISIBLE);
                    amount2.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });
        sub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q3 > 0) {
                    total -= (p3);
                    q3 -= 1;
                }
                if (q3 > 0) {
                    quantity3.setText(Integer.toString(q3 * p3));

                    quantity3.setVisibility(View.VISIBLE);
                    real3.setText(Integer.toString(q3));
                    real3.setVisibility(View.VISIBLE);
                    amount3.setVisibility(View.VISIBLE);
                } else {
                    quantity3.setVisibility(View.INVISIBLE);
                    real3.setVisibility(View.INVISIBLE);
                    amount3.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });
        sub4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q4 > 0) {
                    total -= (p4);
                    q4 -= 1;
                }
                if (q4 > 0) {
                    quantity4.setText(Integer.toString(q4 * p4));

                    quantity4.setVisibility(View.VISIBLE);
                    real4.setText(Integer.toString(q4));
                    real4.setVisibility(View.VISIBLE);
                    amount4.setVisibility(View.VISIBLE);
                } else {
                    quantity4.setVisibility(View.INVISIBLE);
                    real4.setVisibility(View.INVISIBLE);
                    amount4.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });
        sub5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q5 > 0) {
                    total -= (p5);
                    q5 -= 1;
                }
                if (q5 > 0) {
                    quantity5.setText(Integer.toString(q5 * p5));

                    quantity5.setVisibility(View.VISIBLE);
                    real5.setText(Integer.toString(q5));
                    real5.setVisibility(View.VISIBLE);
                    amount5.setVisibility(View.VISIBLE);
                } else {
                    quantity5.setVisibility(View.INVISIBLE);
                    real5.setVisibility(View.INVISIBLE);
                    amount5.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });
        sub6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q6 > 0) {
                    total -= (p6);
                    q6 -= 1;
                }
                if (q6 > 0) {
                    quantity6.setText(Integer.toString(q6 * p6));

                    quantity6.setVisibility(View.VISIBLE);
                    real6.setText(Integer.toString(q6));
                    real6.setVisibility(View.VISIBLE);
                    amount6.setVisibility(View.VISIBLE);
                } else {
                    quantity6.setVisibility(View.INVISIBLE);
                    real6.setVisibility(View.INVISIBLE);
                    amount6.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });
        sub7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q7 > 0) {
                    total -= (p7);
                    q7 -= 1;
                }
                if (q7 > 0) {
                    quantity7.setText(Integer.toString(q7 * p7));

                    quantity7.setVisibility(View.VISIBLE);
                    real7.setText(Integer.toString(q7));
                    real7.setVisibility(View.VISIBLE);
                    amount7.setVisibility(View.VISIBLE);
                } else {
                    quantity7.setVisibility(View.INVISIBLE);
                    real7.setVisibility(View.INVISIBLE);
                    amount7.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });
        sub8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q8 > 0) {
                    total -= (p8);
                    q8 -= 1;
                }
                if (q8 > 0) {
                    quantity8.setText(Integer.toString(q8 * p8));

                    quantity8.setVisibility(View.VISIBLE);
                    real8.setText(Integer.toString(q8));
                    real8.setVisibility(View.VISIBLE);
                    amount8.setVisibility(View.VISIBLE);
                } else {
                    quantity8.setVisibility(View.INVISIBLE);
                    real8.setVisibility(View.INVISIBLE);
                    amount8.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });
        sub9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q9 > 0) {
                    total -= (p9);
                    q9 -= 1;
                }
                if (q9 > 0) {
                    quantity9.setText(Integer.toString(q9 * p9));

                    quantity9.setVisibility(View.VISIBLE);
                    real9.setText(Integer.toString(q9));
                    real9.setVisibility(View.VISIBLE);
                    amount9.setVisibility(View.VISIBLE);
                } else {
                    quantity9.setVisibility(View.INVISIBLE);
                    real9.setVisibility(View.INVISIBLE);
                    amount9.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));
                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }

            }
        });
        sub10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q10 > 0) {
                    total -= (p10);
                    q10 -= 1;
                }
                if (q10 > 0) {
                    quantity10.setText(Integer.toString(q10 * p10));

                    quantity10.setVisibility(View.VISIBLE);
                    real10.setText(Integer.toString(q10));
                    amount10.setVisibility(View.VISIBLE);
                    real10.setVisibility(View.VISIBLE);
                } else {
                    quantity10.setVisibility(View.INVISIBLE);
                    real10.setVisibility(View.INVISIBLE);
                    amount10.setVisibility(View.INVISIBLE);
                }
                if (total > 0 && check == 0) {
                    appBarAnim(total);
                } else if (total > 0 && check == 1) {
                    total3.setText(Integer.toString(total));

                } else if (total == 0 && check == 1) {
                    appBarAnim1();
                }
            }
        });


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, cart.class);
                startActivity(intent);

            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, info.class);
                startActivity(intent);

            }
        });

    }

    public void appBarAnim(int total) {

       /* final TextView menu1 = findViewById(R.id.menu1);
        final TextView menu2 = findViewById(R.id.menu2);*/
        final TextView total1 = findViewById(R.id.total1);
        final TextView total2 = findViewById(R.id.total2);
        final TextView total3 = findViewById(R.id.total3);
        final LottieAnimationView anim = findViewById(R.id.menu_anim);


        Animation zero_up = AnimationUtils.loadAnimation(this, R.anim.slideup);
        Animation down_zero = AnimationUtils.loadAnimation(this, R.anim.slideup1);

        total3.setText(Integer.toString(total));
        /*menu1.startAnimation(zero_up);
        menu2.startAnimation(zero_up);
        menu1.setVisibility(View.INVISIBLE);
        menu2.setVisibility(View.INVISIBLE);
*/
        anim.startAnimation(zero_up);
        anim.setVisibility(View.INVISIBLE);

        total1.startAnimation(down_zero);
        total2.startAnimation(down_zero);
        total3.startAnimation(down_zero);
        total1.setVisibility(View.VISIBLE);
        total2.setVisibility(View.VISIBLE);
        total3.setVisibility(View.VISIBLE);

        check = 1;

    }

    public void appBarAnim1() {
/*    final TextView menu1 = findViewById(R.id.menu1);
    final TextView menu2 = findViewById(R.id.menu2);*/
        final TextView total1 = findViewById(R.id.total1);
        final TextView total2 = findViewById(R.id.total2);
        final TextView total3 = findViewById(R.id.total3);
        final LottieAnimationView anim = findViewById(R.id.menu_anim);


        Animation up_zero = AnimationUtils.loadAnimation(this, R.anim.slideup2);
        Animation zero_down = AnimationUtils.loadAnimation(this, R.anim.slideup3);

 /*   menu1.startAnimation(up_zero);
    menu2.startAnimation(up_zero);
    menu1.setVisibility(View.VISIBLE);
    menu2.setVisibility(View.VISIBLE);
*/

        anim.startAnimation(up_zero);
        anim.setVisibility(View.VISIBLE);

        total1.startAnimation(zero_down);
        total2.startAnimation(zero_down);
        total3.startAnimation(zero_down);
        total1.setVisibility(View.INVISIBLE);
        total2.setVisibility(View.INVISIBLE);
        total3.setVisibility(View.INVISIBLE);


        check = 0;

    }

}

