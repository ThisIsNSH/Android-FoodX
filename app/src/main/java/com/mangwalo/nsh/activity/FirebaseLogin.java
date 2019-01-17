package com.mangwalo.nsh.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.mangwalo.nsh.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LENOVO on 12-01-2019.
 */

public class FirebaseLogin extends AppCompatActivity {

    private static final int RC_SIGN_IN = 138;

    private TextView loginBtn;
    private ProgressBar progressBar;
    EditText mobile;
    String number;
    ImageView login_display;
    ProgressBar pb;
    private List<AuthUI.IdpConfig> providers;
    SharedPreferences sharedPreferences;
//    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        mobile = findViewById(R.id.mobile);
//        number = mobile.getText().toString();
        pb = findViewById(R.id.pb);
        login_display = findViewById(R.id.image);
        Picasso.get().load("https://i.dlpng.com/static/png/151888_preview.png").into(login_display);
        loginBtn = findViewById(R.id.login);
//        progressBar = (ProgressBar) findViewById(R.id.login_progress);

        providers = Arrays.asList(
                new AuthUI.IdpConfig.PhoneBuilder().build());
        sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
        String Login = sharedPreferences.getString("Login", "gsbs");
        if (Login.equals("Complete")) {
//            Log.v("ABSSSSDD",sharedPreferences.getString("phone",null));
            startActivity(new Intent(this, Splash.class));
            finish();
        }
    }
    public void onLoginBtnClick(View v) {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.AppTheme)
                        .setLogo(R.drawable.ic_launcher_background)
                        .build(),
                RC_SIGN_IN);
//        loginBtn.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            pb.setVisibility(View.GONE);
            finish();
            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.d("pno.: ", user.getPhoneNumber().substring(3));
                SharedPreferences.Editor num = sharedPreferences.edit();
                num.putString("mobile",user.getPhoneNumber().substring(3));
                num.apply();
                Log.d("uid: ", user.getUid());
                checkUser(user.getPhoneNumber().substring(3));
//                checkUser("");

            } else {
                // Sign in failed, check response for error code
//                progressBar.setVisibility(View.GONE);
//                loginBtn.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Login Failed. Try Again!!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

    }


    private void checkUser(final String phone_no) {
        SharedPreferences prefs = getSharedPreferences("number", MODE_PRIVATE);
        String restoredText = prefs.getString("phone", null);
        if (restoredText != null) {
            saveLoginStatus();
            Intent intent = new Intent(FirebaseLogin.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor editor = getSharedPreferences("number", MODE_PRIVATE).edit();
            editor.putString("phone", number);
//            Log.v("KOOKO","ABSS"+number);
            editor.apply();
            saveLoginStatus();
            Intent intent = new Intent(FirebaseLogin.this, MainActivity.class);
            startActivity(intent);
        }
    }
    private void saveLoginStatus() {
        final SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Login", "Complete");
        editor.commit();
    }

}