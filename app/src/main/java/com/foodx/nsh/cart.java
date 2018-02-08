package com.foodx.nsh;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import jp.wasabeef.blurry.Blurry;

import static android.view.View.GONE;
import static com.foodx.nsh.MainActivity.q1;
import static com.foodx.nsh.MainActivity.q10;
import static com.foodx.nsh.MainActivity.q2;
import static com.foodx.nsh.MainActivity.q3;
import static com.foodx.nsh.MainActivity.q4;
import static com.foodx.nsh.MainActivity.q5;
import static com.foodx.nsh.MainActivity.q6;
import static com.foodx.nsh.MainActivity.q7;
import static com.foodx.nsh.MainActivity.q8;
import static com.foodx.nsh.MainActivity.q9;
import static com.foodx.nsh.MainActivity.t1;
import static com.foodx.nsh.MainActivity.t2;
import static com.foodx.nsh.MainActivity.t3;
import static com.foodx.nsh.MainActivity.t4;

public class cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        final RelativeLayout relativeLayout = findViewById(R.id.relativemain);

        final TextView one = findViewById(R.id.one);
        final TextView two = findViewById(R.id.two);
        final TextView three = findViewById(R.id.three);
        final TextView four = findViewById(R.id.four);
        final TextView five = findViewById(R.id.five);
        final TextView six = findViewById(R.id.six);
        final TextView seven = findViewById(R.id.seven);
        final TextView eight = findViewById(R.id.eight);
        final TextView nine = findViewById(R.id.nine);
        final TextView ten = findViewById(R.id.ten);

        final Button address = findViewById(R.id.address);

        one.setVisibility(GONE);
        two.setVisibility(GONE);
        three.setVisibility(GONE);
        four.setVisibility(GONE);
        five.setVisibility(GONE);
        six.setVisibility(GONE);
        seven.setVisibility(GONE);
        eight.setVisibility(GONE);
        nine.setVisibility(GONE);
        ten.setVisibility(GONE);


        if (q1>0){
            one.setText(t1+" : "+Integer.toString(q1));
            one.setVisibility(View.VISIBLE);
            }


        if (q2>0){
            two.setText(t2+" : "+Integer.toString(q2));
            two.setVisibility(View.VISIBLE);
        }

        if (q3>0){
            three.setText(t3+" : "+Integer.toString(q3));
            three.setVisibility(View.VISIBLE);
        }

        if (q4>0){
            four.setText(t4+" : "+Integer.toString(q4));
            four.setVisibility(View.VISIBLE);
        }

        if (q5>0){
            five.setText(getString(R.string.title5)+" : "+Integer.toString(q5));
            five.setVisibility(View.VISIBLE);
        }

        if (q6>0){
            six.setText(getString(R.string.title6)+" : "+Integer.toString(q6));
            six.setVisibility(View.VISIBLE);
        }

        if (q7>0){
            seven.setText(getString(R.string.title7)+" : "+Integer.toString(q7));
            seven.setVisibility(View.VISIBLE);
        }

        if (q8>0){
            eight.setText(getString(R.string.title8)+" : "+Integer.toString(q8));
            eight.setVisibility(View.VISIBLE);
        }

        if (q9>0){
            nine.setText(getString(R.string.title9)+" : "+Integer.toString(q9));
            nine.setVisibility(View.VISIBLE);
        }

        if (q10>0){
            ten.setText(getString(R.string.title10)+" : "+Integer.toString(q10));
            ten.setVisibility(View.VISIBLE);
        }

       address.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(cart.this,address.class);
               startActivity(intent);
           }
       });
    }
}
