package com.mangwalo.nsh.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mangwalo.nsh.R;
import com.mangwalo.nsh.adapter.CartAdapter;
import com.mangwalo.nsh.model.Cart;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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

public class OrderDialog extends Dialog implements
        android.view.View.OnClickListener {

    private SharedPreferences sharedPreferences,orderSharedPreferences,Bill;
    private SharedPreferences.Editor editor,editor2;
    public Activity activity;
    public Button btnYes, btnNo;
    TextView totalB;
    int total;
    EditText editText,editText1,editText2;
    private Button button;
    private String key = "Key";
    private List<Cart> myOrders;
    private RecyclerView recyclerView;
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
    DatabaseReference reference;
    String temp;

    public OrderDialog(Activity activity,int totalBill,List<Cart>myOrders) {
        super(activity);
        total = totalBill;
        this.activity = activity;
        this.myOrders = myOrders;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        reference = FirebaseDatabase.getInstance().getReference("Mobiles");

        setContentView(R.layout.dialog_order);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        SharedPreferences sharedPreferences1 = activity.getSharedPreferences("number", Context.MODE_PRIVATE);
        totalB = findViewById(R.id.total);
        SharedPreferences prefs = getContext().getSharedPreferences("number", MODE_PRIVATE);
        SharedPreferences.Editor editor3 = prefs.edit();
        String restoredText = prefs.getString("mobile", null);
        editor3.commit();
//        Log.v("HALLO","ABC" + restoredText);

        totalB.setText("Total : "+String.valueOf(total));

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
                if(TextUtils.isEmpty(address)){
                    Toast.makeText(activity,"Please enter your address",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(activity,"Please enter your name",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mobile)){
                    Toast.makeText(activity,"Please enter your phone no.",Toast.LENGTH_SHORT).show();
                    return;
                }

                hashMap1 = new HashMap<>();
                for (int i = 0; i < myOrders.size(); i++) {
                    hashMap1.put(myOrders.get(i).getHotelId(),myOrders.get(i));
                }

                Log.e("Keysinhashmap",hashMap1.keySet().toString());

                list = new ArrayList<>();
                mapl = new HashMap<>();

                for (String key : hashMap1.keySet()) {

                    List<Cart> arraylist1 = new ArrayList<>();
//                    arraylist1.clear();

                    for(int i=0;i<myOrders.size();i++)
                    {
                        if(key.equals(myOrders.get(i).getHotelId()))
                        {
                            arraylist1.add(myOrders.get(i));
                        }
                    }
                    mapl.put(key,arraylist1);

                }
                list.add(mapl);

                final JsonArray jsonArray = new JsonArray();

                Log.e(TAG, "onClick: " );
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

                        Log.e(TAG, "cart cart: ." );
                        System.out.println(Key1);

                        JsonObject jsonObject3 = new JsonObject();
                        jsonObject3.addProperty("name", cart.getName());
                        jsonObject3.addProperty("quantity", cart.getQuantity());
                        jsonObject3.addProperty("extra", String.valueOf(Integer.parseInt(cart.getQuantity())*Integer.parseInt(cart.getPrice())));
                        jsonArray1.add(jsonObject3);

                    }
                    jsonObject.add("items", jsonArray1);
                    jsonArray.add(jsonObject);
                }

                RequestQueue requestQueue = Volley.newRequestQueue(activity);
                String URL = activity.getString(R.string.base_url) + "/order";

                final String requestBody = jsonArray.toString();

                Log.e(TAG, "onClick: ." );
                System.out.println(requestBody);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("VOLLEY","Ass");

                        try {
                            reference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
//                                        System.out.println(snapshot.getKey());
                                        try {
                                            for (String keynsh : hashMap1.keySet()) {
                                                if (keynsh.equals(snapshot.getKey())) {
//                                                    System.out.println(snapshot.getValue());
                                                    new RetrieveFeedTask(String.valueOf(snapshot.getValue())).execute();
                                                }
                                            }

                                        }
                                        catch (NullPointerException e){ }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });



                            JSONArray jsonArray = new JSONArray(response);
                            JSONArray newArray = new JSONArray();

                            for(int i=0;i<jsonArray.length();i++){
                                newArray.put(jsonArray.getJSONObject(i).getJSONArray("_pipeline").getJSONObject(0).getJSONObject("$match").getJSONObject("order"));
                            }

                            SharedPreferences oldOrders = activity.getSharedPreferences("myOldOrders", MODE_PRIVATE);
                            SharedPreferences.Editor oldOrderEditor = oldOrders.edit();

                            String strJson = oldOrders.getString("oldOrders","[]");
                            JSONArray oldOrderArray = new JSONArray(strJson);
                            oldOrderArray.put(newArray);
                            oldOrderEditor.putString("oldOrders", oldOrderArray.toString());
                            oldOrderEditor.apply();

                            System.out.println(oldOrders.getString("oldOrders","[]"));

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        Toast.makeText(activity,"Your order has been successfully placed",Toast.LENGTH_LONG).show();

                        dismiss();
                        button.setEnabled(true);

                        myOrders.clear();
                        sharedPreferences = activity.getSharedPreferences("Myprefs", MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        gson = new Gson();
                        String json = gson.toJson(myOrders);
                        editor.putString(key,json);
                        editor.apply();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity,"Error in placing order",Toast.LENGTH_LONG).show();
                        dismiss();
                        button.setEnabled(true);

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

                requestQueue.add(stringRequest);

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

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        okhttp3.Response response1;
        String number;

        public RetrieveFeedTask(String number){
            this.number = number;
        }

        protected String doInBackground(Void... urls) {
            try{

                OkHttpClient client = new OkHttpClient();
                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                RequestBody body = RequestBody.create(mediaType, "Url=http%3A%2F%2Fdemo.twilio.com%2Fdocs%2Fvoice.xml&To=%2B91"+number+"&From=%2B16012029776&undefined=");
                okhttp3.Request request = new okhttp3.Request.Builder()
                        .url("https://api.twilio.com/2010-04-01/Accounts/AC67908b31996687c2360d064ad635256e/Calls.json")
                        .post(body)
                        .addHeader("Authorization", "Basic QUM2NzkwOGIzMTk5NjY4N2MyMzYwZDA2NGFkNjM1MjU2ZTo3NjY1ZWYyOTU5OWI5YjZkNmMyZGFmODU4OWZmODQ2MA==")
                        .addHeader("cache-control", "no-cache")
                        .build();

                response1 = client.newCall(request).execute();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return (response1.message());

        }

        protected void onPostExecute(String feed) {
        }
    }
}