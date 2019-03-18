package VerteilterTerminKalender.model.interfaces;

import VerteilterTerminKalender.model.classes.EventDeclineUserImpl;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = EventDeclineUserImpl.class)
public interface EventDeclineUser {

    public String getName();
    public void setName(String name);

    public String getLastName();
    public void setLastName(String lastName);


}
