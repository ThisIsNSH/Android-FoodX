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

        final SQLiteDatabase myDB =
                openOrCreateDatabase("my.db", MODE_PRIVATE, null);

        myDB.execSQL(
                "CREATE TABLE IF NOT EXISTS addTable (address VARCHAR(200), mobile VARCHAR(200))"
        );

        final EditText newaddress = findViewById(R.id.newaddress);
        final EditText newphone = findViewById(R.id.newphone);


        final ContentValues row1 = new ContentValues();

        row1.put("address", newaddress.getText().toString());
        row1.put("mobile", newphone.getText().toString());

        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.insert("addTable", null, row1);

            }
        });


        final String address_final=" ",mobile_final=" ";

        final String total_temp= "" + (total);


        Cursor myCursor = myDB.rawQuery("select address from addTable", null );

        ArrayList<String> user_address= new ArrayList<String>();


        while(myCursor.moveToNext()) {

            String address = myCursor.getString(0);

            user_address.add(address);



        }


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                 android.R.layout.simple_spinner_item,user_address);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        myCursor.close();
        myDB.close();




        mDatabase = FirebaseDatabase.getInstance().getReference();




        Button order1 = findViewById(R.id.final_order);
        order1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

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
