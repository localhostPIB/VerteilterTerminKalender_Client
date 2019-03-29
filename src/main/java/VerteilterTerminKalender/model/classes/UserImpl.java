package VerteilterTerminKalender.model.classes;

import VerteilterTerminKalender.model.interfaces.User;
import lombok.Getter;
import lombok.Setter;

/**
 * Implementation of User interface
 */
@Getter
@Setter
public class UserImpl implements User {

  // @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
   private String userId;
   private String password;
   private String name;
   private String lastName;
   private String email;


    /**
     * Checks if this object is equal to a given object
     * @param o UserImpl object
     * @return true, if both UserImpl-Objects have the same userId, else false
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof UserImpl){
            User cmpUser = (UserImpl) o;

            if (this.userId == null || cmpUser.getUserId() == null){
                return false;
            }

            int compareID = Integer.parseInt(cmpUser.getUserId());
            int ownID = Integer.parseInt(this.userId);

            if (ownID == compareID){
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
