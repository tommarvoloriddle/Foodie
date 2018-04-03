package com.app.foodienitw;

/**
 * Created by hp on 21/03/2018.
 */

public class UserProfile {

    public String userName;
    public String userEmail;
    public String userPassword;
    public String userNumber;

    //Another constructor -> function overloading concept
    public  UserProfile(){

    }

    //constructor created -> assign values to these variable from activities before it
    public UserProfile(String userName, String userEmail, String userPassword, String userNumber) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNumber = userNumber;

    }
}
