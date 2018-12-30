package com.foodx.nsh;

/**
 * Created by ThisIsNSH on 2/12/2018.
 */

public class placeOrder {

    public String order_name;
    public String total;
    public String address;
    public String mobile;
    public String user_name;
    public String timestamp;
    public String completed;

    public placeOrder()
    { }

    public placeOrder(String order_name,String total,String address,String mobile)
    {

        this.order_name=order_name;
        this.total=total;
        this.address=address;
        this.mobile=mobile;

    }


    public placeOrder(String order_name, String total, String address, String mobile, String user_name, String timestamp, String completed) {
        this.order_name = order_name;
        this.total = total;
        this.address = address;
        this.mobile = mobile;
        this.user_name = user_name;
        this.timestamp = timestamp;
        this.completed = completed;
    }
}
