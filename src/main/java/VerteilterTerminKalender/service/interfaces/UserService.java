package VerteilterTerminKalender.service.interfaces;

import VerteilterTerminKalender.model.interfaces.User;

public interface UserService {

          public User getUserByEmail(String email);
          public boolean verifyUser (User user, String password);
          public boolean changeUser(User  user);
          public boolean createUser(User user);
          public boolean isUserExistingByEmail(String email);

}
