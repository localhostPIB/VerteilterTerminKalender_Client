package VerteilterTerminKalender.validators;

public class ObjectValidator {


    /**
     * @param object
     * @return true if the Object is NOT null
     */
    public static boolean isNotObjectNull(Object object){
        if(object == null){
            return false;
        }else{
            return true;
        }
    }

    /**
     * @param object
     * @return true if the Object is null
     */
    public static boolean isObjectNull(Object object){
        return !(isNotObjectNull(object));
    }
}
