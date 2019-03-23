package VerteilterTerminKalender.model.interfaces;


import VerteilterTerminKalender.model.classes.UserImpl;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = UserImpl.class)
public interface User {

    public String getUserId();
    public void setUserId(String userid);

    public String getPassword();
    public void setPassword(String password);

    public String getName();
    public void setName(String name);

    public String getLastName();
    public void setLastName(String lastname);

    public String getEmail();
    public void setEmail(String email);






}
