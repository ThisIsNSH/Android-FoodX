package com.mangwalo.nsh.fragments;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.mangwalo.nsh.R;
import com.mangwalo.nsh.adapter.CartAdapter;
import com.mangwalo.nsh.dialog.OrderDialog;
import com.mangwalo.nsh.model.Cart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    private SharedPreferences sharedPreferences,orderSharedPreferences,Bill;
    private SharedPreferences.Editor editor,editor2;
    private String key = "Key";
    private List<Cart> myOrders;
    private RecyclerView recyclerView;
    private Gson gson;
    private Activity activity;
    private CartAdapter cartAdapter;
    private String address, name, mobile, hotelid, extra;
    List<Map<String, List<Cart>>> list;
    Map<String, List<Cart>> mapl;
    private HashMap<String, ArrayList<Cart>> hashMap;
    private HashMap<String, Cart> hashMap1;
    Volley volley;
    String Key1 = "";
    SpotlightView spotLight;
    View view;
    private int up = 0;
    TextView foodx,nilText;
    String response;
    TextView play;
    public CartFragment() {
    }


    @SuppressLint("ValidFragment")
    public CartFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        //        orderSharedPreferences = getContext().getSharedPreferences("orderList",);
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        play = view.findViewById(R.id.play);
        SharedPreferences sharedPreferences2;
        SharedPreferences.Editor editor3;
        sharedPreferences2 = getContext().getSharedPreferences("time", Context.MODE_PRIVATE);
        String check = sharedPreferences2.getString("cartf",null);
        if (check != null)
        {}
        else
        {
            editor3 = sharedPreferences2.edit();
            editor3.putString("cartf","abc");
            editor3.apply();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    showIntro(play, "fab_intro");
                }
            }, 0);

            PreferencesManager mPreferencesManager = new PreferencesManager(getActivity());
            mPreferencesManager.resetAll();
        }
        sharedPreferences = activity.getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        myOrders = new ArrayList<>();
        nilText = view.findViewById(R.id.nilText);
        foodx = view.findViewById(R.id.foodx);
        response = sharedPreferences.getString(key, "null");

        if (!response.equals("null"))
            myOrders = gson.fromJson(response, new TypeToken<List<Cart>>() {
            }.getType());

//        ArrayList<Integer> remove = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerView1);
        final HashMap<Integer,Integer> remove = new HashMap<>();
        ArrayList<Cart> neone = new ArrayList<>();
        for (int p = 0;p<myOrders.size();p++) {
            if (remove.containsKey(p))
                continue;
            Cart existCart = myOrders.get(p);
            int Quantity = Integer.parseInt(existCart.getQuantity());
            for (int i = p + 1; i < myOrders.size(); i++) {
                Cart cart = myOrders.get(i);
                String HotelName = existCart.getName();
                String HotelId = existCart.getHotelId();
                String checkN = cart.getName();
                int quantity = Integer.parseInt(cart.getQuantity());
                String checkId = cart.getHotelId();
                if (HotelName.equals(checkN) && HotelId.equals(checkId)) {
                    remove.put(i, 1);
                    Quantity += quantity;
                }
            }
            String op = String.valueOf(Quantity);
            neone.add(new Cart(existCart.getName(), op, existCart.getHotelId(),existCart.getPrice()));
        }

        myOrders.clear();

        for (int i = 0;i<neone.size();i++)
            myOrders.add(neone.get(i));

        String json = gson.toJson(myOrders);
        editor.putString(key,json);
        editor.apply();
        cartAdapter = new CartAdapter(myOrders, activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        if (myOrders.size()>0) {
            nilText.setVisibility(View.GONE);
//            foodx.setVisibility(View.INVISIBLE);
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                up += dy;
                foodx.setAlpha(1 - ((float) up / 100 >= 0 && (float) up / 100 <= 1 ? (float) up / 100 : ((float) up / 100 > 1 ? 1 : 0)));
            }
        });

        Button button = view.findViewById(R.id.postorder);

        button.setOnClickListener(new View.OnClickListener() {
            String keys;
            @Override
            public void onClick(View v) {
                int totalBill = 0;
                editor2 = sharedPreferences.edit();
                response = sharedPreferences.getString("Key" ,null);
                if (response != null)
                    myOrders = gson.fromJson(response, new TypeToken<List<Cart>>() {
                    }.getType());
                editor2.apply();
                Log.v("calcc",response);

                for (int i = 0;i<myOrders.size();i++)
                {
                    Cart calc = myOrders.get(i);
                    Integer p = Integer.parseInt(calc.getPrice());
                    Integer q = Integer.parseInt(calc.getQuantity());
                    totalBill = totalBill + p*q;
                }
                if(myOrders.size()==0){
                    Toast.makeText(activity,"Cart is empty",Toast.LENGTH_LONG).show();
                    return;
                }
//                Log.v("FINALTOTAL", String.valueOf(totalBill));
                OrderDialog customDialog = new OrderDialog(activity,totalBill,myOrders);
                customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                customDialog.show();
//                myOrders.clear();

//                total.setText(String.valueOf(totalBill));
                customDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
//                        Fragment frg = null;
////                        frg = getActivity().getSupportFragmentManager().findFragmentByTag("Your_Fragment_TAG");
//                        frg = getActivity().getSupportFragmentManager().findFragmentById(R.id.carts);
//                        final FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                        ft.detach(frg);
//                        ft.attach(frg);
//                        ft.commit();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(CartFragment.this).attach(CartFragment.this).commit();
//                        hashMap1 = new HashMap<>();
//                        for (int i = 0; i < myOrders.size(); i++) {
//                            hashMap1.put(myOrders.get(i).getHotelId(),myOrders.get(i));
//                        }
//                        Log.e("Keysinhashmap",hashMap1.keySet().toString());
//                        list = new ArrayList<>();
//                        mapl = new HashMap<>();
//
//                        List<Cart> arraylist1 = new ArrayList<>();
//                        for (String key : hashMap1.keySet()) {
//                            arraylist1.clear();
//                            for(int i=0;i<myOrders.size();i++)
//                            {
//                                if(key.equals(myOrders.get(i).getHotelId()))
//                                {
//                                    arraylist1.add(myOrders.get(i));
//                                }
//                            }
//                            mapl.put(key,arraylist1);
//                        }
//                        list.add(mapl);
//
//                        address = "Hello";
//                        name = "nishu";
//                        mobile = "988799220";
//                        extra = "n";
//
//                        final JsonArray jsonArray = new JsonArray();
//
//                        for (String key : mapl.keySet()) {
//                            Key1 = key;
//
//                            JsonObject jsonObject = new JsonObject();
//                            jsonObject.addProperty("address", address);
//                            jsonObject.addProperty("name", name);
//                            jsonObject.addProperty("mobile", mobile);
//                            jsonObject.addProperty("hotel_id", Key1);
//                            JsonArray jsonArray1 = new JsonArray();
//                            for (Cart cart : mapl.get(key)) {
//
//                                JsonObject jsonObject3 = new JsonObject();
//                                jsonObject3.addProperty("name", cart.getName());
//                                jsonObject3.addProperty("quantity", cart.getQuantity());
//                                jsonObject3.addProperty("extra", "none");
//                                jsonArray1.add(jsonObject3);
//
//                            }
//                            jsonObject.add("items", jsonArray1);
//                            jsonArray.add(jsonObject);
//                        }
//
//                        RequestQueue requestQueue = Volley.newRequestQueue(activity);
//                        String URL = getString(R.string.base_url) + "/order";
//
//                        final String requestBody = jsonArray.toString();
//
//                        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                            }
//                        }, new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                            }
//                        }) {
//                            @Override
//                            public String getBodyContentType() {
//                                return "application/json; charset=utf-8";
//                            }
//
//                            @Override
//                            public byte[] getBody() throws AuthFailureError {
//                                try {
//                                    return requestBody == null ? null : requestBody.getBytes("utf-8");
//                                } catch (UnsupportedEncodingException uee) {
//                                    return null;
//                                }
//                            }
//
//                            @Override
//                            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                                String responseString = "";
//                                if (response != null) {
//                                    responseString = String.valueOf(response.statusCode);
//                                }
//                                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//                            }
//                        };
//
//                requestQueue.add(stringRequest);

                    }
                });
            }
        });

        return view;
    }
    private void showIntro(View view, String usageId) {
        Typeface typeface = ResourcesCompat.getFont(getActivity(), R.font.psb);
        spotLight = new SpotlightView.Builder(getActivity())
                .introAnimationDuration(400)
                .enableRevealAnimation(true)
                .performClick(true)
                .fadeinTextDuration(400)
                .setTypeface(typeface)
                //.setTypeface(FontUtil.get(this, "RemachineScript_Personal_Use"))
                .headingTvColor(Color.parseColor("#9e9d24"))
                .headingTvSize(32)
                .headingTvText("Cart")
                .subHeadingTvColor(Color.parseColor("#cddc39"))
                .subHeadingTvSize(16)
                .subHeadingTvText("See your unplaced orders here!")
                .maskColor(Color.parseColor("#dc000000"))
                .target(view)
                .lineAnimDuration(400)
                .lineAndArcColor(Color.parseColor("#ffffff"))
                .dismissOnTouch(true)
                .dismissOnBackPress(true)
                .enableDismissAfterShown(true)
                .usageId(usageId) //UNIQUE ID
                .show();
    }
}


