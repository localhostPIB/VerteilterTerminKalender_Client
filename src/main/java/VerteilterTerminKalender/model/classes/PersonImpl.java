package VerteilterTerminKalender.model.classes;


import VerteilterTerminKalender.model.interfaces.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonImpl implements Person {
    private String name;
    private String lastName;

}
