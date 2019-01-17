package com.mangwalo.nsh.model;

public class OrderItem {

    String name;
    String quantity;
    String order_id;
    String hotel_id;
    String extra;
    public OrderItem(String name,String quantity,String extra,String order_id, String hotel_id){
        this.name = name;
        this.quantity = quantity;
        this.order_id = order_id;
        this.hotel_id = hotel_id;
        this.extra = extra;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getExtra() {
        return extra;
    }
}
