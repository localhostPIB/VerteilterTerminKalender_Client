package VerteilterTerminKalender.model.classes;

import lombok.Getter;
import lombok.Setter;
import VerteilterTerminKalender.model.interfaces.User;

@Getter
@Setter
public class UserImpl implements User {

   private String userId;
   private String password;
   private String name;
   private String lastName;
   private String email;


}
