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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mangwalo.nsh.R;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    Activity activity;
    public DetailsFragment() {
        // Required empty public constructor
    }
    @SuppressLint("ValidFragment")
    public DetailsFragment(Activity activity) {
        this.activity = activity;
        // Required empty public constructor
    }
    TextView play;
    SpotlightView spotLight;
//    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_details, container, false);
        play = view.findViewById(R.id.play);SharedPreferences sharedPreferences2;
        SharedPreferences.Editor editor3;
        sharedPreferences2 = getContext().getSharedPreferences("time", Context.MODE_PRIVATE);
        String check = sharedPreferences2.getString("detailsf",null);
        if (check != null)
        {}
        else
        {
            editor3 = sharedPreferences2.edit();
            editor3.putString("detailsf","abc");
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
                .headingTvText("Details")
                .subHeadingTvColor(Color.parseColor("#cddc39"))
                .subHeadingTvSize(16)
                .subHeadingTvText("Read them carefully!")
                .maskColor(Color.parseColor("#dc000000"))
                .target(view)
                .lineAnimDuration(400)
                .lineAndArcColor(Color.parseColor("#ffffff"))
                .dismissOnTouch(true)
                .dismissOnBackPress(true)
                .enableDismissAfterShown(true)
                .usageId(usageId) //UNIQUE ID
                .setTypeface(Typeface.defaultFromStyle(R.font.psb))
                .show();
    }
}
