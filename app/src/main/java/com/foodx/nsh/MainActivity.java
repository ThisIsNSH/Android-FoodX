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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tzanou.PercentVisibleLayout.PercentVisibleLayout;

public class MainActivity extends AppCompatActivity {

    public int q1=0,q2=0,q3=0,q4=0,q5=0,q6=0,q7=0,q8=0,q9=0,q10=0;

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

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q1<15)
                q1+=1;
                if (q1>0){
                    quantity1.setText(Integer.toString(q1));
                    quantity1.setVisibility(View.VISIBLE);
                    }
                else{
                    quantity1.setVisibility(View.INVISIBLE);
                }

            }
        });

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q2<15)
                    q2+=1;
                if (q2>0){
                    quantity2.setText(Integer.toString(q2));
                    quantity2.setVisibility(View.VISIBLE);
                }
                else{
                    quantity2.setVisibility(View.INVISIBLE);
                }

            }
        });

        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q3<15)
                    q3+=1;
                if (q3>0){
                    quantity3.setText(Integer.toString(q3));
                    quantity3.setVisibility(View.VISIBLE);
                }
                else{
                    quantity3.setVisibility(View.INVISIBLE);
                }

            }
        });

        add4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q4<15)
                    q4+=1;
                if (q4>0){
                    quantity4.setText(Integer.toString(q4));
                    quantity4.setVisibility(View.VISIBLE);
                }
                else{
                    quantity4.setVisibility(View.INVISIBLE);
                }

            }
        });

        add5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q5<15)
                    q5+=1;
                if (q5>0){
                    quantity5.setText(Integer.toString(q5));
                    quantity5.setVisibility(View.VISIBLE);
                }
                else{
                    quantity5.setVisibility(View.INVISIBLE);
                }

            }
        });

        add6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q6<15)
                    q6+=1;
                if (q6>0){
                    quantity6.setText(Integer.toString(q6));
                    quantity6.setVisibility(View.VISIBLE);
                }
                else{
                    quantity6.setVisibility(View.INVISIBLE);
                }

            }
        });

        add7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q7<15)
                    q7+=1;
                if (q7>0){
                    quantity7.setText(Integer.toString(q7));
                    quantity7.setVisibility(View.VISIBLE);
                }
                else{
                    quantity7.setVisibility(View.INVISIBLE);
                }

            }
        });

        add8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q8<15)
                    q8+=1;
                if (q8>0){
                    quantity8.setText(Integer.toString(q8));
                    quantity8.setVisibility(View.VISIBLE);
                }
                else{
                    quantity8.setVisibility(View.INVISIBLE);
                }

            }
        });

        add9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q9<15)
                    q9+=1;
                if (q9>0){
                    quantity9.setText(Integer.toString(q9));
                    quantity9.setVisibility(View.VISIBLE);
                }
                else{
                    quantity9.setVisibility(View.INVISIBLE);
                }

            }
        });

        add10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q10<15)
                    q10+=1;
                if (q10>0){
                    quantity10.setText(Integer.toString(q10));
                    quantity10.setVisibility(View.VISIBLE);
                }
                else{
                    quantity10.setVisibility(View.INVISIBLE);
                }

            }
        });

        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q1>0)
                {
                    q1-=1;
                }
                if (q1>0){
                    quantity1.setText(Integer.toString(q1));
                    quantity1.setVisibility(View.VISIBLE);
                }
                else{
                    quantity1.setVisibility(View.INVISIBLE);
                }
            }
        });

        sub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q2>0)
                {
                    q2-=1;
                }
                if (q2>0){
                    quantity2.setText(Integer.toString(q2));
                    quantity2.setVisibility(View.VISIBLE);
                }
                else{
                    quantity2.setVisibility(View.INVISIBLE);
                }
            }
        });
        sub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q3>0)
                {
                    q3-=1;
                }
                if (q3>0){
                    quantity3.setText(Integer.toString(q3));
                    quantity3.setVisibility(View.VISIBLE);
                }
                else{
                    quantity3.setVisibility(View.INVISIBLE);
                }
            }
        });
        sub4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q4>0)
                {
                    q4-=1;
                }
                if (q4>0){
                    quantity4.setText(Integer.toString(q4));
                    quantity4.setVisibility(View.VISIBLE);
                }
                else{
                    quantity4.setVisibility(View.INVISIBLE);
                }
            }
        });
        sub5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q5>0)
                {
                    q5-=1;
                }
                if (q5>0){
                    quantity5.setText(Integer.toString(q5));
                    quantity5.setVisibility(View.VISIBLE);
                }
                else{
                    quantity5.setVisibility(View.INVISIBLE);
                }
            }
        });
        sub6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q6>0)
                {
                    q6-=1;
                }
                if (q6>0){
                    quantity6.setText(Integer.toString(q6));
                    quantity6.setVisibility(View.VISIBLE);
                }
                else{
                    quantity6.setVisibility(View.INVISIBLE);
                }
            }
        });
        sub7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q7>0)
                {
                    q7-=1;
                }
                if (q7>0){
                    quantity7.setText(Integer.toString(q7));
                    quantity7.setVisibility(View.VISIBLE);
                }
                else{
                    quantity7.setVisibility(View.INVISIBLE);
                }
            }
        });
        sub8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q8>0)
                {
                    q8-=1;
                }
                if (q8>0){
                    quantity8.setText(Integer.toString(q8));
                    quantity8.setVisibility(View.VISIBLE);
                }
                else{
                    quantity8.setVisibility(View.INVISIBLE);
                }
            }
        });
        sub9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q9>0)
                {
                    q9-=1;
                }
                if (q9>0){
                    quantity9.setText(Integer.toString(q9));
                    quantity9.setVisibility(View.VISIBLE);
                }
                else{
                    quantity9.setVisibility(View.INVISIBLE);
                }
            }
        });
        sub10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q10>0)
                {
                    q10-=1;
                }
                if (q10>0){
                    quantity10.setText(Integer.toString(q10));
                    quantity10.setVisibility(View.VISIBLE);
                }
                else{
                    quantity10.setVisibility(View.INVISIBLE);
                }
            }
        });


    }
}

