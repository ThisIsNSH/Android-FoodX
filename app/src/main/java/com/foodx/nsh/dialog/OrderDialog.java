package com.foodx.nsh.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.foodx.nsh.R;

public class OrderDialog extends Dialog implements
        android.view.View.OnClickListener {
    public Activity activity;
    public Button btnYes, btnNo;
    TextView totalB;
    int total;
    public OrderDialog(Activity activity,int totalBill) {
        super(activity);
        total = totalBill;
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_order);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        totalB = findViewById(R.id.total);
        totalB.setText("Total : "+String.valueOf(total));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}