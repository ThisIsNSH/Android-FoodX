package com.foodx.nsh;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class address extends AppCompatActivity {

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

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();


        old.setText(pref.getString("address","No Address"));
        oldp.setText(pref.getString("phone","No Mobile Number"));

        new1.setVisibility(View.GONE);
        newp.setVisibility(View.GONE);

        addclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                a.setVisibility(View.GONE);
                m.setVisibility(View.GONE);

                old.setVisibility(View.GONE);
                oldp.setVisibility(View.GONE);
                addclick.setVisibility(View.GONE);
                new1.setVisibility(View.VISIBLE);
                newp.setVisibility(View.VISIBLE);

                new1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        editor.putString("address", new1.getText().toString());
                        editor.commit();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        editor.putString("address", new1.getText().toString());
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
                        editor.commit();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        editor.putString("phone", newp.getText().toString());
                        editor.commit();
                    }
                });

                old.setText(pref.getString("address","No Address"));
                oldp.setText(pref.getString("phone","No Mobile Number"));

            }
        });









    }
}
