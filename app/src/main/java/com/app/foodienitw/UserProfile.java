package com.app.foodienitw;

/**
 * Created by hp on 21/03/2018.
 */

public class UserProfile {

    public String userName;
    public String userEmail;
    public String userPassword;
    public String userNumber;
    public String userType;
    public String shopType;

    //Another constructor -> function overloading concept
    public  UserProfile(){

    }

    //constructor created -> assign values to these variable from activities before it
    public UserProfile(String userName, String userEmail, String userPassword, String userNumber, String userType, String shopType) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNumber = userNumber;
        this.userType = userType;
        this.shopType = shopType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }
}
