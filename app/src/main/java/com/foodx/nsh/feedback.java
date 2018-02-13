package com.foodx.nsh;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        SQLiteDatabase myDB =
                openOrCreateDatabase("my.db", MODE_PRIVATE, null);
        Cursor myCursor = myDB.rawQuery("select name, total, address, mobile from user", null);
        while(myCursor.moveToNext()) {
            String name = myCursor.getString(0);
            int age = myCursor.getInt(1);
            boolean isSingle = (myCursor.getInt(2)) == 1 ? true:false;
        }
        myCursor.close();
        myDB.close();
    }
}
