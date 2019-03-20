package VerteilterTerminKalender.model.classes;

import VerteilterTerminKalender.model.interfaces.EventFx;
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
    public boolean equals(Object o){
        if (o instanceof UserImpl){
            User vergleichsUser = (UserImpl) o;

            if (this.userId == null || vergleichsUser.getUserId() == null){
                return false;
            }

            int vergleichdsID = Integer.parseInt(vergleichsUser.getUserId());
            int eigeneID = Integer.parseInt(this.userId);

            if (eigeneID == vergleichdsID){
                return true;
            }
        }
        return false;
    }


   @Override
   public String toString(){
    String result = name + ", " + lastName;

    return result;
   }
}
