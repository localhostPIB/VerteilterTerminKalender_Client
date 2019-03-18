package VerteilterTerminKalender.model.interfaces;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = EventDeclineUser.class)
public interface EventDeclineUser {

    public String getName();
    public void setName(String name);

    public String getLastName();
    public void setLastName(String lastName);


}
