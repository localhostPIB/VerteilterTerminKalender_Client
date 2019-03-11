package service.interfaces;

import model.interfaces.User;

public interface UserService {

          public User getUserByEmail(String email);
          public boolean verifyUser (String password);
          public boolean changeUser(User  user);
}
