package Test;

import controller.UserControllerRest;

public class TestClassForUSerControllerRest {

    public static void main (String[] args){
        UserControllerRest userControllerRest = new UserControllerRest();
       String response = userControllerRest.getUserById("thomas@mueller.com");
        System.out.println(response);
    }
}
