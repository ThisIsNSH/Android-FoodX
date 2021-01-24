package com.mangwalo.nsh.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mangwalo.nsh.R;
import com.mangwalo.nsh.activity.MainActivity;
import com.mangwalo.nsh.activity.PaymentActivity;
import com.mangwalo.nsh.adapter.CartAdapter;
import com.mangwalo.nsh.model.Cart;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import static android.content.Context.MODE_PRIVATE;
import static com.crashlytics.android.beta.Beta.TAG;

//import com.android.volley.Request;

public class OrderDialog extends Dialog implements
        android.view.View.OnClickListener {


    public Activity activity;
    public Button btnYes, btnNo;
    TextView totalB;
    int total;
    EditText editText, editText1, editText2;
    private Button button;
    private String key = "Key";
    private List<Cart> myOrders;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences, orderSharedPreferences, Bill;
    private SharedPreferences.Editor editor, editor2;
    private Gson gson;
    private CartAdapter cartAdapter;
    private String address, name, mobile, hotelid, extra;
    List<Map<String, List<Cart>>> list;
    Map<String, List<Cart>> mapl;
    //    private HashSet<String> hashSet;
    private HashMap<String, ArrayList<Cart>> hashMap;
    private HashMap<String, Cart> hashMap1;
    //    private ArrayList<Cart>arrayList;
    Volley volley;
    String Key1 = "";
    View view;
    String temp;

    public OrderDialog(Activity activity, int totalBill, List<Cart> myOrders) {
        super(activity);
        total = totalBill;
        this.activity = activity;
        this.myOrders = myOrders;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.dialog_order);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        totalB = findViewById(R.id.total);

        SharedPreferences prefs = getContext().getSharedPreferences("number", MODE_PRIVATE);
        SharedPreferences.Editor editor3 = prefs.edit();
        String restoredText = prefs.getString("mobile", null);
        editor3.commit();

//        Log.v("HALLO","ABC" + restoredText);

        totalB.setText("Total : " + String.valueOf(total));

        editText = findViewById(R.id.name);
        editText1 = findViewById(R.id.address);
        editText2 = findViewById(R.id.mobile);
        editText2.setText(restoredText);
        editText2.setEnabled(false);

        button = findViewById(R.id.postorder);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button.setEnabled(false);

                address = editText1.getText().toString();
                name = editText.getText().toString();
                mobile = editText2.getText().toString();
                extra = "n";

                if (TextUtils.isEmpty(address)) {
                    Toast.makeText(activity, "Please enter your address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(activity, "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mobile)) {
                    Toast.makeText(activity, "Please enter your phone no.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(activity, PaymentActivity.class);
                intent.putExtra("orders", (Serializable) myOrders);
                activity.startActivity(intent);

            }
        });


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }

}