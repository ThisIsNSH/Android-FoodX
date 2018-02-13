package com.foodx.nsh;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ListView list;


        SQLiteDatabase myDB =
                openOrCreateDatabase("my.db", MODE_PRIVATE, null);
        Cursor myCursor = myDB.rawQuery("select name, total, address, mobile from user", null);

        ArrayList<String> order_name = new ArrayList<String>();
        ArrayList<Integer> total_name = new ArrayList<Integer>();
        ArrayList<String> address_name = new ArrayList<String>();
        ArrayList<String> mobile_name = new ArrayList<String>();


        while(myCursor.moveToNext()) {
            String name = myCursor.getString(0);
            int total = myCursor.getInt(1);
            String address = myCursor.getString(2);
            String mobile = myCursor.getString(3);
            order_name.add(name);
            total_name.add(total);
            address_name.add(address);
            mobile_name.add(mobile);


        }




        FragmentList adapter = new
                FragmentList(feedback.this, order_name, total_name, address_name, mobile_name);
        list=(ListView) findViewById(R.id.list1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


            }
        });






        myCursor.close();
        myDB.close();
    }
}
