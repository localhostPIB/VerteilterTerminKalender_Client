package VerteilterTerminKalender.model.classes;

import lombok.Getter;
import lombok.Setter;
import VerteilterTerminKalender.model.interfaces.User;

@Getter
@Setter
public class UserImpl implements User {

   private String userid;
   private String password;
   private String name;
   private String lastname;
   private String email;


}
