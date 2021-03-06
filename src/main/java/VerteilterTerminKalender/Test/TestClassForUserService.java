package VerteilterTerminKalender.Test;

import VerteilterTerminKalender.model.classes.UserImpl;
import VerteilterTerminKalender.model.interfaces.User;
import VerteilterTerminKalender.service.classes.UserServiceImpl;
import VerteilterTerminKalender.service.interfaces.UserService;

public class TestClassForUserService {

    public static void main(String[] args){
        TestClassForUserService test = new TestClassForUserService();
       // test.testVerifiyUser();
        //test.testCreateUser();
        //test.testisUserExistingByEmail();
        test.testGetUserByUserId();
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
        user.setEmail("htw");
        user.setName("Tobias");
        user.setLastName("Gottschalk");
        user.setPassword("password");

        userService.createUser(user);


    }

    private void testisUserExistingByEmail(){
        UserService userService = new UserServiceImpl();
        boolean test = userService.isUserExistingByEmail("435435");
        System.out.println(test);

    }

    private void testGetUserByUserId(){
        UserService userService = new UserServiceImpl();
        User user = ((UserServiceImpl) userService).getUserByUserId("163");
        System.out.println(user);
    }
}
