package com.foodx.nsh.model;

public class Cart {
    private String Name;
    private String Quantity;
    private String HotelId;

    public Cart(String name, String quantity, String hotelId) {
        Name = name;
        Quantity = quantity;
        HotelId = hotelId;
    }

    public String getName() {
        return Name;
    }

    public String getQuantity() {
        return Quantity;
    }

    public String getHotelId() {
        return HotelId;
    }
}
