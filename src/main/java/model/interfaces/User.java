package model.interfaces;

import lombok.Getter;
import lombok.Setter;

public interface User {

    public int getUserid();
    public void setUserid(int userid);

    public String getPassword();
    public void setPassword(String password);

    public String getName();
    public void setName(String name);

    public String getLastname();
    public void setLastname(String lastname);

    public String getEmail();
    public void setEmail(String email);






}
