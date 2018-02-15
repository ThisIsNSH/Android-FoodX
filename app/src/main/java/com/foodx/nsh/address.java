package com.foodx.nsh;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.foodx.nsh.cart.order;
import static com.foodx.nsh.MainActivity.total;


public class address extends AppCompatActivity {

    final public String order_temp=order;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);



        mDatabase = FirebaseDatabase.getInstance().getReference();

        final String address_final,mobile_final;

        final String total_temp= "" + (total);


        Button order1 = findViewById(R.id.final_order);
        order1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //firebase database
                    Log.i("this", order);

                    placeOrder order1 = new placeOrder(order_temp,total_temp,address_final,mobile_final);


                    mDatabase.child("order").push().setValue(order1);
                    //Database
                    SQLiteDatabase myDB =
                            openOrCreateDatabase("my.db", MODE_PRIVATE, null);
                    myDB.execSQL(
                            "CREATE TABLE IF NOT EXISTS user (name VARCHAR(200), total INT, address VARCHAR(200), mobile VARCHAR(200))"
                    );
                    ContentValues row1 = new ContentValues();
                    row1.put("name", order_temp);
                    row1.put("total", total_temp);
                    row1.put("address", address_final);
                    row1.put("mobile", mobile_final);
                    myDB.insert("user", null, row1);

                    myDB.close();



                }
            });



    }

    @Override
    public void onBackPressed()
    {
        order=" ";
        super.onBackPressed();
    }


}
