package com.foodx.nsh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tzanou.PercentVisibleLayout.PercentVisibleLayout;

public class MainActivity extends AppCompatActivity {
    private int a=1;
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

        mCustomLayout.setOnVisibilityPercentChangedListener(new PercentVisibleLayout.OnVisibilityPercentChanged() {
            @Override
            public void onVisibilityChange(int fromHeight, int fromWidth, int percentageHeight, int percentageWidth) {

                Log.d("this", "onVisibilityChange: "+percentageHeight);

                if (percentageHeight <50 && a==1)
                {   a=0;
                    title1.setVisibility(View.GONE);
                    title2.setVisibility(View.GONE);
                    menu1.setVisibility(View.VISIBLE);
                    menu2.setVisibility(View.VISIBLE);

                }

            }
        });

        mCustomLayout1.setOnVisibilityPercentChangedListener(new PercentVisibleLayout.OnVisibilityPercentChanged() {
            @Override
            public void onVisibilityChange(int fromHeight, int fromWidth, int percentageHeight, int percentageWidth) {

                Log.d("this", "onVisibilityChange: "+percentageHeight);

                if (percentageHeight <50 && a==1)
                {
                    title1.setVisibility(View.GONE);
                    title2.setVisibility(View.GONE);
                    menu1.setVisibility(View.VISIBLE);
                    menu2.setVisibility(View.VISIBLE);


                }

            }
        });
    }
}