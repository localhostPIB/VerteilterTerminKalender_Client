package VerteilterTerminKalender.validators;

import VerteilterTerminKalender.constants.ValidatorConstants;

public class StringValidator {


    /**
     * Die Methode ueberprueft, ob der String leer oder null ist
     * @param zuUeberpruefenderString zu pruefender String
     * @return true when the String is NOT null or empty
     */
    public static boolean isNotStringEmptyOrNull(String zuUeberpruefenderString){
        if(zuUeberpruefenderString == null|| zuUeberpruefenderString.trim().isEmpty()){
            return false;
        }

        return true;
    }

    /**
     * checks whether a given String s is empty or null
     * @param s
     * @return true if s is empty or null
     */
    public static boolean isStringEmptyOrNull(String s){
        return !(isNotStringEmptyOrNull(s));
    }

    /**
     * checks whether a given String is a number
     * @param text
     * @return true if 'text' is a number, else false
     */
    public static boolean isNumber(String text) {
        return text.matches(ValidatorConstants.NUMBER_REGEX);
    }


    public static boolean isTimeFormatted(String s){
      if (isStringEmptyOrNull(s)){
          return false;
      }

      return s.matches(ValidatorConstants.TIME_FORMAT_REGEX);
    }
}
