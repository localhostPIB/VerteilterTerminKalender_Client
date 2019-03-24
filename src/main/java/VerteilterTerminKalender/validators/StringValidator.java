package VerteilterTerminKalender.validators;

import VerteilterTerminKalender.constants.ValidatorConstants;

/**
 * Used for validating user-input inside the GUI
 * @author Michelle Blau
 * @author Tobias Gottschalk
 */

public class StringValidator {


    /**
     * Tests if a given String is empty or null
     * @param cmpString
     * @return true if the String is NOT null or empty
     */
    public static boolean isNotStringEmptyOrNull(String cmpString){
        if(cmpString == null|| cmpString.trim().isEmpty()){
            return false;
        }

        return true;
    }

    /**
     * checks whether a given String is empty or null
     * @param cmpString
     * @return true if the String is empty or null
     */
    public static boolean isStringEmptyOrNull(String cmpString){
        return !(isNotStringEmptyOrNull(cmpString));
    }

    /**
     * checks whether a given String is a number
     * @param text
     * @return true if 'text' is a number, else false
     */
    public static boolean isNumber(String text) {
        return text.matches(ValidatorConstants.NUMBER_REGEX);
    }


    /**
     * Tests if a given String s has a valid time-format,
     * e.g. "07:00", "23:59"
     * @param s
     * @return true if time-formatted, else false
     */
    public static boolean isTimeFormatted(String s){
      if (isStringEmptyOrNull(s)){
          return false;
      }

      return s.matches(ValidatorConstants.TIME_FORMAT_REGEX);
    }
}
