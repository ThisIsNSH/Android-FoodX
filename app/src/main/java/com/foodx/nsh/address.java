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

    public String address_temp1="null",mobile1="null";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        final TextView a = findViewById(R.id.a);
        final TextView m = findViewById(R.id.m);

        final TextView old = findViewById(R.id.oldaddress);
        final TextView oldp = findViewById(R.id.oldphone);
        final EditText new1 = findViewById(R.id.newaddress);
        final EditText newp = findViewById(R.id.newphone);
        final TextView addclick = findViewById(R.id.addclick);

        final TextInputLayout inputa = findViewById(R.id.input_layout_address);
        final TextInputLayout inputm = findViewById(R.id.input_layout_phone);

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();


        old.setText(pref.getString("address","No Address"));
        oldp.setText(pref.getString("phone","No Mobile Number"));



        String address_temp=old.getText().toString();
        String mobile=oldp.getText().toString();

/*
        inputa.setVisibility(View.GONE);
    inputm.setVisibility(View.GONE);*/

        /*addclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {*/
/*
                a.setVisibility(View.GONE);
                m.setVisibility(View.GONE);

                old.setVisibility(View.GONE);
                oldp.setVisibility(View.GONE);
                addclick.setVisibility(View.GONE);
                inputa.setVisibility(View.VISIBLE);
                inputm.setVisibility(View.VISIBLE);*/

                new1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        editor.putString("address", new1.getText().toString());
                        old.setText(new1.getText().toString());
                        address_temp1=old.getText().toString();
                        editor.commit();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        editor.putString("address", new1.getText().toString());
                        old.setText(new1.getText().toString());
                        address_temp1=old.getText().toString();
                        editor.commit();

                    }
                });



                newp.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        editor.putString("phone", newp.getText().toString());
                        oldp.setText(newp.getText().toString());
                        mobile1=oldp.getText().toString();

                        editor.commit();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        editor.putString("phone", newp.getText().toString());
                        oldp.setText(newp.getText().toString());
                        mobile1=oldp.getText().toString();

                        editor.commit();
                    }
                });


          /*  }
        });*/

        mDatabase = FirebaseDatabase.getInstance().getReference();

        final String address_final,mobile_final;

        final String total_temp= "" + (total);
        if (mobile1=="null")
        {
            mobile_final=mobile;
        }
        else{
            mobile_final=mobile1;
        }

        if (address_temp1=="null")
        {
            address_final=address_temp;
        }
        else{
            address_final=address_temp1;
        }

        Button order1 = findViewById(R.id.final_order);
        order1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //firebase database
                    Log.i("this", order);

                    placeOrder order1 = new placeOrder(order_temp,total_temp,address_final,mobile_final);


                    mDatabase.child("order").push().setValue(order1);


                }
            });

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

    }

    @Override
    public void onBackPressed()
    {
        order=" ";
        super.onBackPressed();
    }


}
