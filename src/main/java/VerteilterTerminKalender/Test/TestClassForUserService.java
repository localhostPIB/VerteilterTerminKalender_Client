package VerteilterTerminKalender.Test;

import VerteilterTerminKalender.model.interfaces.User;
import VerteilterTerminKalender.service.classes.UserServiceImpl;
import VerteilterTerminKalender.service.interfaces.UserService;

public class TestClassForUserService {

    public static void main(String[] args){
        UserService userService = new UserServiceImpl();
        User user = userService.getUserByEmail("mary@jane.com");
        System.out.println(user);
    }
}
