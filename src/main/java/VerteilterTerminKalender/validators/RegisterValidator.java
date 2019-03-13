package VerteilterTerminKalender.validators;

import VerteilterTerminKalender.constants.ValidatorConstants;


public class RegisterValidator {


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


}
