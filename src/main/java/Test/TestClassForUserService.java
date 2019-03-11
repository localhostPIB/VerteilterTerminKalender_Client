package Test;

import model.interfaces.User;
import service.classes.UserServiceImpl;
import service.interfaces.UserService;

public class TestClassForUserService {

    public static void main(String[] args){
        UserService userService = new UserServiceImpl();
        User user = userService.getUserByEmail("mary@jane.com");
        System.out.println(user);
    }
}
