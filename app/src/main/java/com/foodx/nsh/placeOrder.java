package com.foodx.nsh;

/**
 * Created by ThisIsNSH on 2/12/2018.
 */

public class placeOrder {

    public String order_name;
    public String total;
    public String address;
    public String mobile;

    public placeOrder()
    { }

    public placeOrder(String order_name,String total,String address,String mobile)
    {
        this.order_name=order_name;
        this.total=total;
        this.address=address;
        this.mobile=mobile;

    }


}
