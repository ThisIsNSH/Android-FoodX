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
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mangwalo.nsh.R;
import com.mangwalo.nsh.adapter.HotelAdapter;
import com.mangwalo.nsh.model.Hotel;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotelsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RelativeLayout linearLayout;
    private HotelAdapter hotelAdapter;
    private List<Hotel> hotelList;
    private TextView foodx;
    private ProgressBar pb;
    private Activity activity;
    private int up = 0;
    View view;
    TextView play;
    private SpotlightView spotLight;
    public HotelsFragment() {
    }

    @SuppressLint("ValidFragment")
    public HotelsFragment(Activity activity){
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_hotels, container, false);

        play = view.findViewById(R.id.play);
        SharedPreferences sharedPreferences2;
        SharedPreferences.Editor editor3;
        sharedPreferences2 = getContext().getSharedPreferences("time", Context.MODE_PRIVATE);
        String check = sharedPreferences2.getString("hotelsf",null);
        if (check != null)
        {}
        else
        {
            editor3 = sharedPreferences2.edit();
            editor3.putString("hotelsf","abc");
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
        onInit();
        return view;
    }
    public void onInit() {
        pb = view.findViewById(R.id.pb);
        linearLayout = view.findViewById(R.id.mainsurface);
        foodx = view.findViewById(R.id.foodx);
        recyclerView = view.findViewById(R.id.recyclerView);
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
        hotelList = new ArrayList<>();
        hotelAdapter = new HotelAdapter(hotelList, activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(hotelAdapter);
//        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(activity);
//        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
//        if (isFirstRun)
//        {
//            Notificationdialog customDialog = new Notificationdialog(activity,"Terms and Conditions","Minimum Order of ₹250 will be entertained.");
//            customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            customDialog.show();
//            SharedPreferences.Editor editor = wmbPreference.edit();
//            editor.putBoolean("FIRSTRUN", false);
//            editor.commit();
//        }
        getData();
    }
    public void getData() {
        final RequestQueue requestQueue = Volley.newRequestQueue(activity);
        JsonArrayRequest request = new JsonArrayRequest(getString(R.string.base_url)+"/hotel/Z2FuZXNo",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for(int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String name, location, mobile, id;
                                id = jsonObject.getString("_id");
                                name = jsonObject.getString("name");
                                location = jsonObject.getString("location");
                                mobile = jsonObject.getString("mobile");
                                hotelList.add(new Hotel(id, name, "https://i.dlpng.com/static/png/151888_preview.png", mobile, location));
                            }
                            catch(JSONException e) {
                            }
                        }
                        hotelAdapter.notifyDataSetChanged();
                        pb.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                });
        requestQueue.add(request);
    }

    private void showIntro(View view, String usageId) {
        Typeface typeface = ResourcesCompat.getFont(activity, R.font.psb);
        spotLight = new SpotlightView.Builder(activity)
                .introAnimationDuration(400)
                .enableRevealAnimation(true)
                .performClick(true)
                .fadeinTextDuration(400)
                .setTypeface(typeface)
                //.setTypeface(FontUtil.get(this, "RemachineScript_Personal_Use"))
                .headingTvColor(Color.parseColor("#cddc39"))
                .headingTvSize(32)
                .headingTvText("Welcome To Mangwalo")
                .subHeadingTvColor(Color.parseColor("#cddc39"))
                .subHeadingTvSize(16)
                .subHeadingTvText("Mangwalo serves from different restaurants at same time. So now you can order anything from anywhere at same time.")
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
