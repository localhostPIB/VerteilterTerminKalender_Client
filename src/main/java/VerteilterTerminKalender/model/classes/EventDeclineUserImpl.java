package VerteilterTerminKalender.model.classes;

import VerteilterTerminKalender.model.interfaces.EventDeclineUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDeclineUserImpl implements EventDeclineUser {

    private String name;
    private String lastName;


}
