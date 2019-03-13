package VerteilterTerminKalender.validators;

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

}
