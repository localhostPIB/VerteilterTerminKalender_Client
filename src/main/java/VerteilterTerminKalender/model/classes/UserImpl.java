package VerteilterTerminKalender.model.classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import VerteilterTerminKalender.model.interfaces.User;

@Getter
@Setter
public class UserImpl implements User {

  // @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
   private String userId;
   private String password;
   private String name;
   private String lastName;
   private String email;


   @Override
   public String toString(){
    String result = name + ", " + lastName;

    return result;
   }
}
