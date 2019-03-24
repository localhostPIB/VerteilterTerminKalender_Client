package VerteilterTerminKalender.model.interfaces;


import VerteilterTerminKalender.model.classes.PersonImpl;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = PersonImpl.class)
public interface Person {
    public String getName();
    public String getLastName();

    public void setName(String name);
    public void setLastName(String lastName);


}
