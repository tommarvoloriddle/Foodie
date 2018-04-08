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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
