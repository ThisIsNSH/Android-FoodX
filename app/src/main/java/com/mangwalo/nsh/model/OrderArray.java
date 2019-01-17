package com.mangwalo.nsh.model;

import java.util.ArrayList;
import java.util.List;

public class OrderArray {

    List<OrderItem> itemList = new ArrayList<>();
    int total;
    public OrderArray(List<OrderItem> itemList,int total){
        this.itemList = itemList;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

}