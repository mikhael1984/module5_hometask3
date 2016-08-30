package com.epam.bytya.module5_hometask2.businessObjects;

/**
 * Created by Mikhail_Mereminskiy on 8/17/2016.
 */
public class User {

    // Class fields
    private static final String LOGIN= "wktsadmin";
    private static final String PASSWORD = "pwd";

    public static String getLogin() {
        return LOGIN;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}
