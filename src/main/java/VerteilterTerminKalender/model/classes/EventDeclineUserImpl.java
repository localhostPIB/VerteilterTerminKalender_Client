package VerteilterTerminKalender.model.classes;

import VerteilterTerminKalender.model.interfaces.EventDeclineUser;
import lombok.Getter;
import lombok.Setter;

/**
 * Implementation of event decline user interface
 */
@Getter
@Setter
public class EventDeclineUserImpl implements EventDeclineUser {

    private String name;
    private String lastName;


    @Override
    public String toString(){
        String resultString = name + ", " + lastName;
        return resultString;
    }

}
