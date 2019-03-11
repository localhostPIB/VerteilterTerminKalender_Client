package VerteilterTerminKalender.service.interfaces;

import VerteilterTerminKalender.model.interfaces.User;

public interface UserService {

          public User getUserByEmail(String email);
          public boolean verifyUser (String password);
          public boolean changeUser(User  user);
          public boolean createUser(User user);

}
