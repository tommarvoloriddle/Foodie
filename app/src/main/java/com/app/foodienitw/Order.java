package com.app.foodienitw;

public class Order {
    public String itemName,rate,quantity;

    public Order(){
        itemName = "Chicken Biryani";
        rate = "100";
        quantity = "10";
    }

    public Order(String nameOrder, String rateOrder) {
        this.itemName = nameOrder;
        this.rate = rateOrder;
    }
}
