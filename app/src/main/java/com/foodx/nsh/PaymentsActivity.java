package com.foodx.nsh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentsActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Intent in = getIntent();
        String order_temp,total_temp,address_final,mobile_final,name_final;
        order_temp = in.getStringExtra("order_temp");
        total_temp = in.getStringExtra("total_temp");
        address_final = in.getStringExtra("address_final");
        mobile_final=in.getStringExtra("mobile_final");
        name_final = in.getStringExtra("name_final");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());
        String completed = "NO";
        final placeOrder place = new placeOrder(order_temp,total_temp,address_final,mobile_final,name_final,currentDateandTime,completed);
        Button button = findViewById(R.id.payments);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("order").push().setValue(place);
                Toast.makeText(PaymentsActivity.this,"Your order has been placed,please wait for confirmation call.",Toast.LENGTH_LONG).show();
                startActivity(new Intent(PaymentsActivity.this,MainActivity.class));
                finish();
            }
        });
    }
}
