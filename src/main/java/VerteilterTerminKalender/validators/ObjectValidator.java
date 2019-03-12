package VerteilterTerminKalender.validators;

public class ObjectValidator {


    /**
     * @param zuUeberpruefendesObject
     * @return true when the Object is NOT null
     */
    public static boolean isNotObjectNull(Object zuUeberpruefendesObject){
        if(zuUeberpruefendesObject == null){
            return false;
        }else{
            return true;
        }
    }
}
