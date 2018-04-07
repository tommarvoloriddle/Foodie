package com.app.foodienitw;

public class FoodItem {

    public String rate,name;
    //public boolean isAvailable;

    public FoodItem(String name, String rate) {

        this.name = name;
        this.rate = rate;
    }

    public FoodItem()
    {
        name = "IceCream";
        rate = "100";
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
