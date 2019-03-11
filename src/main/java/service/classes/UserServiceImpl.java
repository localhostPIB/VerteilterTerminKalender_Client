package service.classes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.UserControllerRest;
import model.classes.UserImpl;
import model.interfaces.User;
import service.interfaces.UserService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    UserControllerRest userControllerRest = new UserControllerRest();

    public User getUserByEmail(String email) {
        User user = new UserImpl();

        ObjectMapper mapper = new ObjectMapper();
        String result;
        UserControllerRest userControllerRest = new UserControllerRest();
        result = userControllerRest.getUserById(email);

        Map<String, Object> map = new HashMap<String, Object>();


        try {
            map = mapper.readValue(result, new TypeReference<Map<String, String>>(){});
            user.setName(map.get("name").toString());
            user.setUserid(map.get("userId").toString());
            user.setPassword(map.get("password").toString());
            user.setLastname(map.get("lastName").toString());
            user.setEmail(map.get("email").toString());

            return user;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean verifyUser(String password) {
        return false;
    }

    public boolean changeUser(User user) {
        return false;
    }
}
