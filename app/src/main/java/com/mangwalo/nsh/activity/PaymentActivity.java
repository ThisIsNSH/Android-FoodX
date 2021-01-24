package com.mangwalo.nsh.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.mangwalo.nsh.adapter.CartAdapter;
import com.mangwalo.nsh.dialog.OrderDialog;
import com.mangwalo.nsh.model.Cart;
import com.squareup.picasso.Picasso;

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

import static com.crashlytics.android.beta.Beta.TAG;

public class PaymentActivity extends AppCompatActivity {

    final int UPI_PAYMENT = 0;
    DatabaseReference reference;
    int total;
    private String key = "Key";
    private List<Cart> myOrders;
    private SharedPreferences sharedPreferences, orderSharedPreferences, Bill;
    private SharedPreferences.Editor editor, editor2;
    private Gson gson;
    private String address, name, mobile, hotelid, extra;
    List<Map<String, List<Cart>>> list;
    Map<String, List<Cart>> mapl;
    private HashMap<String, ArrayList<Cart>> hashMap;
    private HashMap<String, Cart> hashMap1;
    Volley volley;
    String Key1 = "";
    View view;
    String temp;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        SharedPreferences prefs = getSharedPreferences("number", MODE_PRIVATE);
        mobile = prefs.getString("mobile", null);

        reference = FirebaseDatabase.getInstance().getReference("Mobiles");

        ImageView image = findViewById(R.id.image);
        Picasso.get().load("https://pluspng.com/img-png/grocery-items-png-supermarket-coupons-png-image-12852-366.png").into(image);

        Intent i = getIntent();
        myOrders = (List<Cart>) i.getSerializableExtra("orders");
        pay();
    }

    void payUsingUpi(String amount, String upiId, String name, String note) {


        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tr", mobile+System.currentTimeMillis())     // optional
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount+".00")
                .appendQueryParameter("cu", "INR")
                .build();

//        Uri uri =
//                new Uri.Builder()
//                        .scheme("upi")
//                        .authority("pay")
//                        .appendQueryParameter("pa", upiId)       // virtual ID
//                        .appendQueryParameter("pn", name)          // name
//                        .appendQueryParameter("mc", "r@jn335h")          // optional
//                        .appendQueryParameter("tn", "your-transaction-note")       // any note about payment
//                        .appendQueryParameter("am", "your-order-amount")           // amount
//                        .appendQueryParameter("cu", "INR")                         // currency
//                        .build();


        Intent upiPayIntent = new Intent();
        upiPayIntent.setData(uri);

        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");

        // check if intent resolves
        if(null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(PaymentActivity.this, MainActivity.class));
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList, data);
                    } else {
                        Log.d("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList, data);
                    }
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null"); //when user simply back without payment
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList, data);
                }
                break;
        }
    }

    private void upiPaymentDataOperation(ArrayList<String> data, Intent intent) {
        if (isConnectionAvailable(this)) {
            String str = data.get(0);
            Log.d("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }

            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
                Log.d("UPI", "responseStr: "+approvalRefNo);
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(PaymentActivity.this, MainActivity.class));
    }

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }


    public void pay() {
        hashMap1 = new HashMap<>();
        for (int i = 0; i < myOrders.size(); i++) {
            hashMap1.put(myOrders.get(i).getHotelId(), myOrders.get(i));
        }

        Log.e("Keysinhashmap", hashMap1.keySet().toString());

        list = new ArrayList<>();
        mapl = new HashMap<>();

        for (String key : hashMap1.keySet()) {

            List<Cart> arraylist1 = new ArrayList<>();
//                    arraylist1.clear();

            for (int i = 0; i < myOrders.size(); i++) {
                if (key.equals(myOrders.get(i).getHotelId())) {
                    arraylist1.add(myOrders.get(i));
                }
            }
            mapl.put(key, arraylist1);

        }
        list.add(mapl);

        final JsonArray jsonArray = new JsonArray();

        Log.e(TAG, "onClick: ");
        System.out.println(mapl);

        for (String key : mapl.keySet()) {
            Key1 = key;

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("address", address);
            jsonObject.addProperty("name", name);
            jsonObject.addProperty("mobile", mobile);
            jsonObject.addProperty("hotel_id", Key1);
            JsonArray jsonArray1 = new JsonArray();

            for (Cart cart : mapl.get(Key1)) {

                Log.e(TAG, "cart cart: .");
                System.out.println(Key1);

                JsonObject jsonObject3 = new JsonObject();
                jsonObject3.addProperty("name", cart.getName());
                jsonObject3.addProperty("quantity", cart.getQuantity());
                jsonObject3.addProperty("extra", String.valueOf(Integer.parseInt(cart.getQuantity()) * Integer.parseInt(cart.getPrice())));
                jsonArray1.add(jsonObject3);

            }
            jsonObject.add("items", jsonArray1);
            jsonArray.add(jsonObject);
        }

        final String URL = getString(R.string.base_url) + "/order";

        final String requestBody = jsonArray.toString();

        Log.e(TAG, "onClick: .");
        System.out.println(requestBody);


        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("VOLLEY", "Ass");

                try {
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                                        System.out.println(snapshot.getKey());
                                try {
                                    for (String keynsh : hashMap1.keySet()) {
                                        if (keynsh.equals(snapshot.getKey())) {
//                                                    System.out.println(snapshot.getValue());
                                            new RetrieveFeedTask(String.valueOf(snapshot.getValue())).execute();
                                        }
                                    }

                                } catch (NullPointerException e) {
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });


                    JSONArray jsonArray = new JSONArray(response);
                    JSONArray newArray = new JSONArray();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        newArray.put(jsonArray.getJSONObject(i).getJSONArray("_pipeline").getJSONObject(0).getJSONObject("$match").getJSONObject("order"));
                    }

                    SharedPreferences oldOrders = getSharedPreferences("myOldOrders", MODE_PRIVATE);
                    SharedPreferences.Editor oldOrderEditor = oldOrders.edit();

                    String strJson = oldOrders.getString("oldOrders", "[]");
                    JSONArray oldOrderArray = new JSONArray(strJson);
                    oldOrderArray.put(newArray);
                    oldOrderEditor.putString("oldOrders", oldOrderArray.toString());
                    oldOrderEditor.apply();

                    System.out.println(oldOrders.getString("oldOrders", "[]"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(PaymentActivity.this, "Your order has been successfully placed", Toast.LENGTH_LONG).show();

                myOrders.clear();
                sharedPreferences = getSharedPreferences("Myprefs", MODE_PRIVATE);
                editor = sharedPreferences.edit();
                gson = new Gson();
                String json = gson.toJson(myOrders);
                editor.putString(key, json);
                editor.apply();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PaymentActivity.this, "Error in placing order", Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }
        };

        payUsingUpi(String.valueOf(total), "hadanis.singh@okicici", "Nishant Singh Hada", "Kota Dept Order by " + mobile);

    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        okhttp3.Response response1;
        String number;

        public RetrieveFeedTask(String number) {
            this.number = number;
        }

        protected String doInBackground(Void... urls) {
            try {

                OkHttpClient client = new OkHttpClient();
                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, "Url=http%3A%2F%2Fdemo.twilio.com%2Fdocs%2Fvoice.xml&To=%2B91" + number + "&From=%2B16012029776&undefined=");
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url("https://api.twilio.com/2010-04-01/Accounts/AC67908b31996687c2360d064ad635256e/Calls.json")
                        .post(body)
                        .addHeader("Authorization", "Basic QUM2NzkwOGIzMTk5NjY4N2MyMzYwZDA2NGFkNjM1MjU2ZTo3NjY1ZWYyOTU5OWI5YjZkNmMyZGFmODU4OWZmODQ2MA==")
                        .addHeader("cache-control", "no-cache")
                        .build();

                response1 = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return (response1.message());

        }

        protected void onPostExecute(String feed) {
        }
    }


}
