package com.foodx.nsh.fragments;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.foodx.nsh.R;
import com.foodx.nsh.activity.CartActivity;
import com.foodx.nsh.adapter.CartAdapter;
import com.foodx.nsh.model.Cart;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String key = "Key";
    private List<Cart> myOrders;
    private RecyclerView recyclerView;
    private Gson gson;
    private Activity activity;
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
    public CartFragment() {
    }

    @SuppressLint("ValidFragment")
    public CartFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferences = activity.getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        myOrders = new ArrayList<>();
       view = inflater.inflate(R.layout.fragment_cart, container, false);
        String response = sharedPreferences.getString(key, "null");
        if (!response.equals("null"))
            myOrders = gson.fromJson(response, new TypeToken<List<Cart>>() {
            }.getType());
        recyclerView = view.findViewById(R.id.recyclerView1);
        cartAdapter = new CartAdapter(myOrders, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
//        hashMap1 = new HashMap<>();
//        for (int i = 0; i < myOrders.size(); i++) {
//            hashMap1.put(myOrders.get(i).getHotelId(), myOrders.get(i));
//        }
//        list = new ArrayList<Map<String, List<Cart>>>();
//        map1 = new HashMap<String, List<Cart>>();
//        List<Cart> arraylist1 = new ArrayList<Cart>();
//        for (String key : hashMap1.keySet()) {
//            arraylist1.clear();
//            map1.clear();
//            for (int j = 0; j < myOrders.size(); j++) {
//                if (key == myOrders.get(j).getHotelId()) {
//                    arraylist1.add(myOrders.get(j));
//                }
//                map1.put(key, arraylist1);
//            }
//            list.add(map1);
//        }

//        Button button = view.findViewById(R.id.postorder);
//
//        final JsonArray jsonArray = new JsonArray();
//
//
//        for (int i = 0; i < list.size(); i++) {
//            Map<String, List<Cart>> map = list.get(i);
//            for (String key : map.keySet()) {
//                Key1 = key;
//            }
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("address", address);
//            jsonObject.addProperty("name", name);
//            jsonObject.addProperty("mobile", mobile);
//            jsonObject.addProperty("hotel_id", Key1);
//            JsonArray jsonArray1 = new JsonArray();
//            for (List<Cart> value : map.values()) {
//                for (int k = 0; k < value.size(); k++) {
//                    Cart cart = value.get(k);
//                    JsonObject jsonObject3 = new JsonObject();
//                    jsonObject3.addProperty("name", cart.getName());
//                    jsonObject3.addProperty("quantity", cart.getQuantity());
//                    jsonObject3.addProperty("extra", "none");
//                    jsonArray1.add(jsonObject3);
//                }
//            }
//            jsonObject.add("items", jsonArray1);
//            jsonArray.add(jsonObject);
//        }
        Button button = view.findViewById(R.id.postorder);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hashMap1 = new HashMap<>();
                for (int i = 0; i < myOrders.size(); i++) {
                    hashMap1.put(myOrders.get(i).getHotelId(),myOrders.get(i));
                }
                Log.e("Keysinhashmap",hashMap1.keySet().toString());
                list = new ArrayList<>();
                mapl = new HashMap<>();
                List<Cart> arraylist1 = new ArrayList<>();
                for (String key : hashMap1.keySet()) {
//                    Log.e("Key",key);
                    arraylist1.clear();
                    for(int i=0;i<myOrders.size();i++)
                    {

                        if(key.equals(myOrders.get(i).getHotelId()))
                        {
                            arraylist1.add(myOrders.get(i));

                        }
                    }
//                    Log.e("Arraylist",arraylist1.toString());
                   mapl.put(key,arraylist1);

                }
                list.add(mapl);
                //HarCoded Elements input required;
                address = "Hello";
                name = "nishu";
                mobile = "988799220";
                extra = "n";
//                Log.e("finalmap",mapl.toString());
//                Log.e("finallist",list.toString());




                final JsonArray jsonArray = new JsonArray();


                    for (String key : mapl.keySet()) {
                        Key1 = key;

                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("address", address);
                    jsonObject.addProperty("name", name);
                    jsonObject.addProperty("mobile", mobile);
                    jsonObject.addProperty("hotel_id", Key1);
                    JsonArray jsonArray1 = new JsonArray();
                    for (Cart cart : mapl.get(key)) {

                            JsonObject jsonObject3 = new JsonObject();
                            jsonObject3.addProperty("name", cart.getName());
                            jsonObject3.addProperty("quantity", cart.getQuantity());
                            jsonObject3.addProperty("extra", "none");
                            jsonArray1.add(jsonObject3);

                    }
                    jsonObject.add("items", jsonArray1);
                    jsonArray.add(jsonObject);
                }
                Log.e("hi",jsonArray.toString());
//                Log.e("myOrders",myOrders.toString());
//                Log.e("hashmap1",hashMap1.toString());
//                Log.e("list",list.toString());
//                Log.e("hotelid",Key1);
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                String URL = getString(R.string.base_url) + "/order";
                Log.e("finalarr",jsonArray.toString());
                final String requestBody = jsonArray.toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("VOLLEY", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", error.toString());
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
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                            return null;
                        }
                    }

                    @Override
                    protected Response<String> parseNetworkResponse(NetworkResponse response) {
                        String responseString = "";
                        if (response != null) {
                            responseString = String.valueOf(response.statusCode);
                            // can get more details such as response.headers
                        }
                        return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                    }
                };

                requestQueue.add(stringRequest);
            }
        });

        return view;
    }


}


