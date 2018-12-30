package com.foodx.nsh;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.view.View.GONE;
import static com.foodx.nsh.cart.order;
import static com.foodx.nsh.MainActivity.total;


public class address extends AppCompatActivity {

    final public String order_temp=order;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        final TextView nameDisplay = findViewById(R.id.namedisplay);
        final TextView addDisplay = findViewById(R.id.adddisplay);
        final TextView mobiledisplay = findViewById(R.id.mobiledisplay);
        final EditText newname = findViewById(R.id.user_name);
        final EditText newaddress = findViewById(R.id.newaddress);
        final EditText newphone = findViewById(R.id.newphone);


        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyAdd", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        final String name_value = pref.getString("name","None");
        String add_value = pref.getString("add", "None");
        String mobile_value = pref.getString("mob","None");

        nameDisplay.setText(name_value);
        addDisplay.setText(add_value);
        mobiledisplay.setText(mobile_value);

        newname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                editor.putString("name",newname.getText().toString());
                editor.commit();
                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable s) {
                editor.putString("name",newname.getText().toString());
                editor.commit();
                editor.apply();
            }
        });

        newaddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                editor.putString("add", newaddress.getText().toString());
                editor.commit();
                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable s) {
                editor.putString("add", newaddress.getText().toString());
                editor.commit();
                editor.apply();
            }
        });


        newphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                editor.putString("mob", newphone.getText().toString());
                editor.commit();
                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable s) {
                editor.putString("mob", newphone.getText().toString());
                editor.commit();
                editor.apply();
            }
        });





        final String total_temp= "" + (total);





        mDatabase = FirebaseDatabase.getInstance().getReference();




        Button order1 = findViewById(R.id.final_order);
        order1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){


                    editor.apply();
                    String name_final = pref.getString("name","None");
                    String address_final = pref.getString("add","None");
                    String mobile_final = pref.getString("mob","None");

                    if (address_final=="None" || mobile_final=="None"||name_final=="None")
                    {
                        Toast.makeText(address.this, "Please give required details for the delivery!", Toast.LENGTH_LONG).show();
                    }
                    else if(mobile_final.length()!=10)
                    {
                        Toast.makeText(address.this, "Is your mobile number correct!", Toast.LENGTH_LONG).show();
                    }
                    else {
                            Intent in = new Intent(address.this,PaymentsActivity.class);
                            in.putExtra("order_temp",order_temp);
                            in.putExtra("total_temp",total_temp);
                            in.putExtra("address_final",address_final);
                            in.putExtra("mobile_final",mobile_final);
                            in.putExtra("name_final",name_final);
                            startActivity(in);
//                        Toast.makeText(address.this, "Order Placed, wait for the confirmation!", Toast.LENGTH_LONG).show();
//
//
//                        Log.i("this", order + " address-" + address_final + " mobile-" + mobile_final);
//
//                        placeOrder order1 = new placeOrder(order_temp, total_temp, address_final, mobile_final,name_final);
//
//                        mDatabase.child("order").push().setValue(order1);
                    }



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
