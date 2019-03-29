package VerteilterTerminKalender.model.classes;


import VerteilterTerminKalender.model.interfaces.Person;
import lombok.Getter;
import lombok.Setter;

/**
 * Implementation of Person interface
 * Used for event participation and decline
 */
@Getter
@Setter
public class PersonImpl implements Person {
    private String name;
    private String lastName;


    @Override
    public String toString(){
        return name + ", " + lastName;
    }
}
