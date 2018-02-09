package com.foodx.nsh;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ThisIsNSH on 2/9/2018.
 */

public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
