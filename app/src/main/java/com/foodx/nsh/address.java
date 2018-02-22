package com.foodx.nsh;

import android.content.ContentValues;
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

        final TextView addDisplay = findViewById(R.id.adddisplay);
        final TextView mobiledisplay = findViewById(R.id.mobiledisplay);
        final EditText newaddress = findViewById(R.id.newaddress);
        final EditText newphone = findViewById(R.id.newphone);


        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyAdd", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        String add_value = pref.getString("add", "None");
        String mobile_value = pref.getString("mob","None");

        addDisplay.setText(add_value);
        mobiledisplay.setText(mobile_value);

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
                public void onClick(View v) {


                    editor.apply();
                    String address_final = pref.getString("add","None");
                    String mobile_final = pref.getString("mob","None");

                    if (address_final=="None" || mobile_final=="None" )
                    {
                        Toast.makeText(address.this, "Please give required details for the delivery!", Toast.LENGTH_LONG).show();
                    }
                    else if(mobile_final.length()>10)
                    {
                        Toast.makeText(address.this, "Is your mobile number correct!", Toast.LENGTH_LONG).show();
                    }
                    else {

                        Toast.makeText(address.this, "Order Placed, wait for the confirmation!", Toast.LENGTH_LONG).show();


                        Log.i("this", order + " address-" + address_final + " mobile-" + mobile_final);

                        placeOrder order1 = new placeOrder(order_temp, total_temp, address_final, mobile_final);


                        mDatabase.child("order").push().setValue(order1);
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
