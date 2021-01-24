package com.mangwalo.nsh.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mangwalo.nsh.R;
import com.mangwalo.nsh.adapter.MenuAdapter;
import com.mangwalo.nsh.model.Item;
import com.mangwalo.nsh.model.Menu;
import com.wooplr.spotlight.SpotlightView;
import com.wooplr.spotlight.prefs.PreferencesManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MenuActivity extends AppCompatActivity {

    private TextView hotelName;
    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private List<Menu> menuList;
    ProgressBar pb;
    String id;
    String mere;
    JSONArray array;
    SpotlightView spotLight;
    TextView play,play2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        play = findViewById(R.id.play);
        SharedPreferences sharedPreferences2;
        SharedPreferences.Editor editor3;
        sharedPreferences2 = getSharedPreferences("time", Context.MODE_PRIVATE);
        String check = sharedPreferences2.getString("menuf",null);
        if (check != null)
        {}
        else
        {
            editor3 = sharedPreferences2.edit();
            editor3.putString("menuf","abc");
            editor3.apply();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    showIntro(play, "fab_intro");
                }
            }, 0);

            PreferencesManager mPreferencesManager = new PreferencesManager(this);
            mPreferencesManager.resetAll();
        }

        onInit();
    }

    public void onInit() {
        hotelName = findViewById(R.id.hotel_name);
        recyclerView = findViewById(R.id.recyclerView);
        pb = findViewById(R.id.load_menu);
        Bundle bundle = getIntent().getExtras();
        hotelName.setText(bundle.getString("name"));

        id = bundle.getString("id");
        menuList = new ArrayList<>();
        //bundle.getInt("color")
        menuAdapter = new MenuAdapter(R.color.color2, menuList, this,id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(menuAdapter);

        LinearSnapHelper snapHelper = new LinearSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                View centerView = findSnapView(layoutManager);
                if (centerView == null)
                    return RecyclerView.NO_POSITION;

                int position = layoutManager.getPosition(centerView);
                int targetPosition = -1;
                if (layoutManager.canScrollHorizontally()) {
                    if (velocityX < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                if (layoutManager.canScrollVertically()) {
                    if (velocityY < 0) {
                        targetPosition = position - 1;
                    } else {
                        targetPosition = position + 1;
                    }
                }

                final int firstItem = 0;
                final int lastItem = layoutManager.getItemCount() - 1;
                targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));
                return targetPosition;
            }
        };
        snapHelper.attachToRecyclerView(recyclerView);
        try
        {
            getData();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void menulist() {
        String temp = getString(R.string.base_url)+"/hotel/menu/";
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, temp + id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String jsonArray) {
                        try {
                            array = new JSONArray(jsonArray);
                            Map<String, ArrayList<Item>> menu = new TreeMap<>();
                            for (int i = 0; i < array.length(); i++) {
                                Log.v("hello", String.valueOf(i));
                                JSONObject jsonObject = array.getJSONObject(i);
                                String category = jsonObject.getString("category");
                                if (menu.containsKey(category)) {
                                    menu.get(category).add(new Item(jsonObject.getString("food_name"),
                                            jsonObject.getString("food_image"),
                                            jsonObject.getString("food_price")));
                                } else {
                                    menu.put(category, new ArrayList<Item>());
                                    menu.get(category).add(new Item(jsonObject.getString("food_name"),
                                            jsonObject.getString("food_image"),
                                            jsonObject.getString("food_price")));
                                }
                            }
                            for (Map.Entry<String, ArrayList<Item>> entry : menu.entrySet()) {
                                String h = entry.getKey();
                                ArrayList<Item> m = entry.getValue();
                                menuList.add(new Menu(h, m));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        menuAdapter.notifyDataSetChanged();
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

    public void getData() throws JSONException {
        menulist();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fadein,R.anim.fadeout);
    }
    private void showIntro(View view, String usageId) {
        Typeface typeface = ResourcesCompat.getFont(this, R.font.psb);
        spotLight = new SpotlightView.Builder(this)
                .introAnimationDuration(400)
                .enableRevealAnimation(true)
                .performClick(true)
                .fadeinTextDuration(400)
                .setTypeface(typeface)
//                .setTypeface(FontUtil.get(this, "RemachineScript_Personal_Use"))
                .headingTvColor(Color.parseColor("#cddc39"))
                .headingTvSize(32)
                .headingTvText("Swipe Horizontally and Vertically")
                .subHeadingTvColor(Color.parseColor("#cddc39"))
                .subHeadingTvSize(16)
                .subHeadingTvText("See categories by swiping horizontally and items by swiping vertically")
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
