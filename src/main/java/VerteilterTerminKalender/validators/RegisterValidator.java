package VerteilterTerminKalender.validators;

import VerteilterTerminKalender.constants.ValidatorConstants;
import VerteilterTerminKalender.model.interfaces.User;
import VerteilterTerminKalender.service.classes.UserServiceImpl;
import VerteilterTerminKalender.service.interfaces.UserService;

/**
 * Used for validating user-input during the registration process
 * @author Michelle Blau
 */

public class RegisterValidator {
    private static UserService userService = new UserServiceImpl();


    /**
     * Checks if String-argument s is an email.
     *
     * @param s String
     * @return true if s is an email, else false
     */
    public static boolean isEmail(String s){
        if (StringValidator.isNotStringEmptyOrNull(s)){
            return s.matches(ValidatorConstants.EMAIL_REGEX);
        }
        return false;
    }

    /**
     * Checks if String-Argument is long enough to be a password
     *
     * @param password String
     * @return true if 'password' has more than 3 characters, else false
     */
    public static boolean hasEnoughCharacters(String password){
        if (StringValidator.isNotStringEmptyOrNull(password)){
            return password.length() >= 3;
        }
        return false;
    }


    /**
     * Tests whether a given user's email already exists in the database
     * @param email
     * @return true if email exists, else false
     */
    public static boolean userExists(String email){
        if(email == null){
            return true;
        }
        User newUserOrNull = userService.getUserByEmail(email);

        if(newUserOrNull == null){
            return false;
        }else{
            return true;
        }
    }
}
