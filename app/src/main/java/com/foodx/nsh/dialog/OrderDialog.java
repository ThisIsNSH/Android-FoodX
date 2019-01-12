package com.foodx.nsh.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.foodx.nsh.R;

public class OrderDialog extends Dialog implements
        android.view.View.OnClickListener {
    public Activity activity;
    public Button btnYes, btnNo;

    public OrderDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_order);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}