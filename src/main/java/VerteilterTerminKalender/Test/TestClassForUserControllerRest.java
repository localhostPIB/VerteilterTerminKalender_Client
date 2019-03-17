package VerteilterTerminKalender.Test;

import VerteilterTerminKalender.controller.UserControllerRest;

public class TestClassForUserControllerRest {

    public static void main (String[] args){
        UserControllerRest userControllerRest = new UserControllerRest();
       String response = userControllerRest.getUserById("thomas@mueller.com");
        System.out.println(response);
    }
}
