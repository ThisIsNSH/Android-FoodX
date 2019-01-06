package com.foodx.nsh.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    String category;
    List<Item> itemList = new ArrayList<>();

    public Menu(String category, List<Item> itemList){
        this.category = category;
        this.itemList = itemList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

}
