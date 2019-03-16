package VerteilterTerminKalender.Test;

import VerteilterTerminKalender.model.classes.UserImpl;
import VerteilterTerminKalender.model.interfaces.User;
import VerteilterTerminKalender.service.classes.UserServiceImpl;
import VerteilterTerminKalender.service.interfaces.UserService;

public class TestClassForUserService {

    public static void main(String[] args){
        TestClassForUserService test = new TestClassForUserService();
        test.testVerifiyUser();
        //test.testCreateUser();
    }


    private void testVerifiyUser(){
        UserService userService = new UserServiceImpl();
        User user = userService.getUserByEmail("mary@jane.com");
        boolean bool = userService.verifyUser(user, "hello");
        System.out.println(bool);
    }

    private void testCreateUser(){
        UserService userService = new UserServiceImpl();
        UserImpl user= new UserImpl();
        user.setEmail("eschy");
        user.setName("Tobias");
        user.setLastName("Gottschalk");
        user.setPassword("password");

        userService.createUser(user);


    }
}
