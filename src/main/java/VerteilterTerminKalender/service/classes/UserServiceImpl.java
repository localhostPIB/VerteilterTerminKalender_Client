package VerteilterTerminKalender.service.classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import VerteilterTerminKalender.controller.UserControllerRest;
import VerteilterTerminKalender.model.classes.UserImpl;
import VerteilterTerminKalender.model.interfaces.User;
import VerteilterTerminKalender.service.interfaces.UserService;

import javax.ws.rs.InternalServerErrorException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserControllerRest userControllerRest = new UserControllerRest();

    public User getUserByEmail(String email) {
        User user = new UserImpl();

        ObjectMapper mapper = new ObjectMapper();
        String result;
        //UserControllerRest userControllerRest = new UserControllerRest();
        result = userControllerRest.getUserByEmail(email);

        Map<String, Object> map = new HashMap<String, Object>();


        try {
            map = mapper.readValue(result, new TypeReference<Map<String, String>>(){});
            user.setName(map.get("name").toString());
            user.setUserId(map.get("userId").toString());
            user.setPassword(map.get("password").toString());
            user.setLastName(map.get("lastName").toString());
            user.setEmail(map.get("email").toString());

            return user;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



    public boolean verifyUser(User user, String password) {
        if(user.getPassword().equals(password)){
            return true;
        }else {
            return false;
        }
    }
//TODO Implementieren
    public boolean changeUser(User user) {
        return false;
    }

    public boolean createUser(User user) {
        ObjectMapper mapper = new ObjectMapper();
        try{

            String jsonInString = mapper.writeValueAsString(user);
            userControllerRest.createUser(jsonInString);
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return false;
    }

    public boolean isUserExistingByEmail(String email){
        try {
            String result = userControllerRest.findIdByEmail(email);
            return true;
        }catch (InternalServerErrorException e) {

        }

        return false;

    }

    public User getUserByUserId(String userId){
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        try {
            String result = userControllerRest.getUserByUserId(userId);
            user = mapper.readValue(result, User.class);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;

    }
}
