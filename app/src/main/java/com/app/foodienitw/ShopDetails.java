package com.app.foodienitw;

public class ShopDetails {

    public String name,location,phoneno,openorclose;
    public String closeTime,openTime;

    public  ShopDetails(){
    }


    public ShopDetails(String name, String location, String openTime, String closeTime) {
        this.name = name;
        this.location = location;
        this.closeTime = closeTime;
        this.openTime = openTime;
    }

    public String getName() {
        return name;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public void setOpenorclose(String openorclose){
        this.openorclose = openorclose;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

}
