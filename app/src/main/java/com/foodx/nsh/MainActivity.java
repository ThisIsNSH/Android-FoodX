package com.foodx.nsh;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tzanou.PercentVisibleLayout.PercentVisibleLayout;

public class MainActivity extends AppCompatActivity {
    Animation animFadein, animFadein1;

    private int a = 1, cart = 0;
    private float x1,x2,y1,y2;
    static final int MIN_RIGHT = 50,MIN_LEFT=-50;
    private int q1=0,q2=0,q3=0,q4=0;

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

        final View item1 = findViewById(R.id.item1);
        final View item2 = findViewById(R.id.item2);
        final View item3 = findViewById(R.id.item3);
        final View item4 = findViewById(R.id.item4);

        final TextView quantity10 = findViewById(R.id.quantity1);
        final TextView quantity20 = findViewById(R.id.quantity2);
        final TextView quantity30 = findViewById(R.id.quantity3);
        final TextView quantity40 = findViewById(R.id.quantity4);


        /*    item1.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        y1 = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        y2 = event.getY();
                        float deltaX = x2 - x1;
                        float deltaY = y2 - y1;
                        Log.i("this", "onTouch: "+deltaX);
                        Log.i("this", "onTouch: "+deltaY);
                        if ((deltaX) > MIN_RIGHT ) {
                            q1+=1;
                            Log.i("this", "item1: " + q1);
                            if (q1>0)
                            {
                                quantity10.setText(Integer.toString(q1));
                                quantity10.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                quantity10.setVisibility(View.INVISIBLE);
                            }
                        } else if ((deltaX) < MIN_LEFT ) {
                            if (q1>0)
                            q1-=1;
                            Log.i("this", "item1: " + q1);
                            if (q1>0)
                            {
                                quantity10.setText(Integer.toString(q1));
                                quantity10.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                quantity10.setVisibility(View.INVISIBLE);
                            }
                        }else{

                        }

                        break;
                }

                return false;
            }
        });*/

/*
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                Sensor gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                SensorEventListener gyroscopeSensorListener = new SensorEventListener(){
                  @Override

                };
            }
        });
        item2.



*/
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

         Context context1 = item1.getContext();
          SensorManager sensorManager = (SensorManager) context1.getSystemService(SENSOR_SERVICE);



          Sensor rotationVectorSensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        // Create a listener
                 SensorEventListener rvListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float[] rotationMatrix = new float[16];
                SensorManager.getRotationMatrixFromVector(
                        rotationMatrix, sensorEvent.values);
                float[] remappedRotationMatrix = new float[16];
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_X,
                        SensorManager.AXIS_Z,
                        remappedRotationMatrix);

// Convert to orientations
                float[] orientations = new float[3];
                SensorManager.getOrientation(remappedRotationMatrix, orientations);

                for(int i = 0; i < 3; i++) {
                    orientations[i] = (float)(Math.toDegrees(orientations[i]));
                }

                if(orientations[2] > 30) {
                    q1+=1;
                    if (q1>0)
                    {
                        quantity10.setText(Integer.toString(q1));
                        quantity10.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        quantity10.setVisibility(View.INVISIBLE);
                    }                }

                    else if(orientations[2] < -30) {
                    if (q1>0)
                        q1-=1;
                    if (q1>0)
                    {
                        quantity10.setText(Integer.toString(q1));
                        quantity10.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        quantity10.setVisibility(View.INVISIBLE);
                    }                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

// Register it
        sensorManager.registerListener(rvListener   ,
                rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);



            }


        });

















/*        SensorManager sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        Sensor gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        SensorEventListener gyroscopeSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                Log.i("this", "onSensorChanged: "+sensorEvent.values[2]);
                if(sensorEvent.values[2] > 2f) { // anticlockwise
                    if (q1>0)
                        q1-=1;
                    Log.i("this", "item1: " + q1);
                    if (q1>0)
                    {
                        quantity10.setText(Integer.toString(q1));
                        quantity10.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        quantity10.setVisibility(View.INVISIBLE);
                    }
                } else if(sensorEvent.values[2] < -2f) { // clockwise
                    q1+=1;
                    Log.i("this", "item1: " + q1);
                    if (q1>0)
                    {
                        quantity10.setText(Integer.toString(q1));
                        quantity10.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        quantity10.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

// Register the listener
        sensorManager.registerListener(gyroscopeSensorListener,
                gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);*/






        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slideup);
        animFadein1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slideup1);


        mCustomLayout.setOnVisibilityPercentChangedListener(new PercentVisibleLayout.OnVisibilityPercentChanged() {
            @Override
            public void onVisibilityChange(int fromHeight, int fromWidth, int percentageHeight, int percentageWidth) {


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


