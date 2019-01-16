package com.mangwalo.nsh.model;

import java.util.ArrayList;
import java.util.List;

public class OrderArray {

    List<OrderItem> itemList = new ArrayList<>();

    public OrderArray(List<OrderItem> itemList){
        this.itemList = itemList;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

}