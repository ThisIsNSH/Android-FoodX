package com.foodx.nsh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.foodx.nsh.R;
import com.foodx.nsh.adapter.MenuAdapter;
import com.foodx.nsh.model.Item;
import com.foodx.nsh.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private TextView hotelName;
    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private List<Menu> menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        onInit();
    }

    public void onInit(){
        hotelName = findViewById(R.id.hotel_name);
        recyclerView = findViewById(R.id.recyclerView);
        Bundle bundle = getIntent().getExtras();
//        cardView.setCardBackgroundColor(getResources().getColor(bundle.getInt("color")));
        hotelName.setText(bundle.getString("name"));
        menuList = new ArrayList<>();
        menuAdapter = new MenuAdapter(bundle.getInt("color"),menuList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
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
        getData();
    }

    public void getData() {
        List<Item> itemList = new ArrayList<>(),itemList1 = new ArrayList<>(),itemList2 = new ArrayList<>();

         itemList.add(new Item("Item 1", "image", "100"));
         itemList.add(new Item("Item 1", "image", "100"));
         itemList.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
         itemList.add(new Item("Item 1", "image", "100"));
         itemList.add(new Item("Item 1", "image", "100"));
         itemList.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
         itemList.add(new Item("Item 1", "image", "100"));
         itemList.add(new Item("Item 1", "image", "100"));
         itemList.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
         itemList.add(new Item("Item 1", "image", "100"));
         itemList.add(new Item("Item 1", "image", "100"));
         itemList.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList1.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
        itemList2.add(new Item("Item 1", "image", "100"));
         menuList.add(new Menu("Pizza",itemList));
         menuList.add(new Menu("Pizza1",itemList1));
         menuList.add(new Menu("Pizza2",itemList2));

         menuAdapter.notifyDataSetChanged();
    }
}
