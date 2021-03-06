package VerteilterTerminKalender.service.classes;

import VerteilterTerminKalender.controller.UserControllerRest;
import VerteilterTerminKalender.model.classes.UserImpl;
import VerteilterTerminKalender.model.interfaces.User;
import VerteilterTerminKalender.service.interfaces.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.InternalServerErrorException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of UserService interface
 */
public class UserServiceImpl implements UserService {

    private UserControllerRest userControllerRest = new UserControllerRest();

    /**
     * Get user by e-mail address
     * @param email of an user
     * @return User if operation is successfully executed, null if error
     */
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


    /**
     * Verify password
     * @param user object
     * @param password provided
     * @return true if password matches,  else false
     */
    public boolean verifyUser(User user, String password) {
        if(user.getPassword().equals(password)){
            return true;
        }else {
            return false;
        }
    }


    /**
     * Create a new user
     * @param user object
     * @return true if operation is successfully executed, false if error
     */
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

    /**
     * Check if a user is already registered
     * @param email address
     * @return true if operation is successfully executed, false if error
     */
    public boolean isUserExistingByEmail(String email){
        try {
            String result = userControllerRest.findIdByEmail(email);
            return true;
        }catch (InternalServerErrorException e) {

        }

        return false;

    }

    /**
     * Get a user by userid
     * @param userId id of an user
     * @return User if operation is successfully executed, null if error
     */
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
