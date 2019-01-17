package com.mangwalo.nsh.fragments;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mangwalo.nsh.R;
import com.mangwalo.nsh.adapter.OrderAdapter;
import com.mangwalo.nsh.model.OrderArray;
import com.mangwalo.nsh.model.OrderItem;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private int up=0;
    private Activity activity;
    private RecyclerView recyclerView;
    TextView foodx;
    private SpotlightView spotLight;
    TextView play;

    public SettingsFragment() {
    }
    @SuppressLint("ValidFragment")
    public SettingsFragment(Activity activity){
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        foodx = view.findViewById(R.id.foodx);
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
        play = view.findViewById(R.id.play);SharedPreferences sharedPreferences2;
        SharedPreferences.Editor editor3;
        sharedPreferences2 = getContext().getSharedPreferences("time", Context.MODE_PRIVATE);
        String check = sharedPreferences2.getString("settingsf",null);
        if (check != null)
        {}
        else
        {
            editor3 = sharedPreferences2.edit();
            editor3.putString("settingsf","abc");
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
        SharedPreferences oldOrders = activity.getSharedPreferences("myOldOrders", Context.MODE_PRIVATE);
        SharedPreferences.Editor oldOrderEditor = oldOrders.edit();
        String strJson = oldOrders.getString("oldOrders","[]");
        try{
            JSONArray jsonArray = new JSONArray(strJson);
            List<OrderArray> orderArrayList = new ArrayList<>();

            for (int i=0;i<jsonArray.length();i++){
                List<OrderItem> orderItemList = new ArrayList<>();
                JSONArray innerArray = jsonArray.getJSONArray(i);
                int total=0;
                for (int j=0;j<innerArray.length();j++){
                    String order_id = innerArray.getJSONObject(j).getString("_id");
                    String hotel_id = innerArray.getJSONObject(j).getString("hotel_id");
                    JSONArray innerInnerArray = innerArray.getJSONObject(j).getJSONArray("items");
                    for (int k=0;k<innerInnerArray.length();k++){
                        String name = innerInnerArray.getJSONObject(k).getString("name");
                        String quantity = innerInnerArray.getJSONObject(k).getString("quantity");
                        String extra = innerInnerArray.getJSONObject(k).getString("extra");
                        total+=Integer.parseInt(!extra.equals("none") ? extra : "0");
                        orderItemList.add(new OrderItem(name,quantity,extra,order_id,hotel_id));
                    }
                }
                orderArrayList.add(new OrderArray(orderItemList,total));
            }

            OrderAdapter orderAdapter = new OrderAdapter(orderArrayList,activity);
            recyclerView.setAdapter(orderAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,true));
            recyclerView.scrollToPosition(0);
            orderAdapter.notifyDataSetChanged();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return view;
    }
    private void showIntro(View view, String usageId) {
        spotLight = new SpotlightView.Builder(getActivity())
                .introAnimationDuration(400)
                .enableRevealAnimation(true)
                .performClick(true)
                .fadeinTextDuration(400)
                //.setTypeface(FontUtil.get(this, "RemachineScript_Personal_Use"))
                .headingTvColor(Color.parseColor("#9e9d24"))
                .headingTvSize(32)
                .headingTvText("Order History")
                .subHeadingTvColor(Color.parseColor("#cddc39"))
                .subHeadingTvSize(16)
                .subHeadingTvText("Check your orders status here!")
                .maskColor(Color.parseColor("#dc000000"))
                .target(view)
                .lineAnimDuration(400)
                .lineAndArcColor(Color.parseColor("#ffffff"))
                .dismissOnTouch(true)
                .dismissOnBackPress(true)
                .enableDismissAfterShown(true)
                .setTypeface(Typeface.defaultFromStyle(R.font.psb))
                .usageId(usageId) //UNIQUE ID
                .show();
    }

}
