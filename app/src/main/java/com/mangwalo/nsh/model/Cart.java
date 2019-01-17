package com.mangwalo.nsh.model;

public class Cart {

    private String Name;
    private String Quantity;
    private String HotelId;
    private String price;
    public Cart(String name, String quantity, String hotelId,String Price) {
        Name = name;
        Quantity = quantity;
        HotelId = hotelId;
        price = Price;
    }

    public String getName() {
        return Name;
    }
    public String getPrice(){return price;}
    public void finalQuantity(String quant)
    {
        Quantity = quant;
    }
    public void setQuantity(int add)
    {
        int Qu = Integer.parseInt(Quantity);
        Qu += add;
        Quantity = String.valueOf(Qu);
    }
    public String getQuantity() {
        return Quantity;
    }

    public String getHotelId() {
        return HotelId;
    }
}
