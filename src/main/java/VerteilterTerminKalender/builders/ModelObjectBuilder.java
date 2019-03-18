package VerteilterTerminKalender.builders;

import VerteilterTerminKalender.model.classes.EventFxImpl;
import VerteilterTerminKalender.model.classes.EventInviteImpl;
import VerteilterTerminKalender.model.classes.UserImpl;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import VerteilterTerminKalender.model.interfaces.User;

import java.time.LocalDateTime;


/**
 * This class provides a centralized access point for getting Model Objects.
 *
 * @author Johannes Gerwert
 * @version 16.03.2019
 */
public class ModelObjectBuilder {

    /**
     * Creates a User Object with the Standard Constructor.
     *
     * @return The requested User Object.
     */
    public static User getUserObject(){
        return new UserImpl();
    }

    /**
     * Creates a User Object with the Standard Constructor, then assigns the attributes with
     * the set-methods.
     *
     * @param userId The UserID of the requested User.
     * @param password The password of the requested User.
     * @param name The first name of the requested User.
     * @param lastName The last name of the requested User.
     * @param email The EMail-adress of the requested User.
     *
     * @return The requested User object.
     */
    public static User getUserObject(String userId, String password, String name, String lastName, String email){
        User user = new UserImpl();
        user.setUserId(userId);
        user.setPassword(password);
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);

        return user;
    }

    /**
     * Creates an EventFx Object with the Standard Constructor.
     *
     * @return The requested EventFx Object.
     */
    public static EventFx getEventFxObject(){
        return new EventFxImpl();
    }

    /**
     * Creates an EventFx Object with the respective Constructor.
     *
     * @param location The location of the requested EventFx Object.
     * @param startTime The start time of the requested EventFx Object.
     * @param endTime The ent time of the requested EventFx Object.
     * @param allDay The status, if the requested EventFx Object lasts all day.
     * @param repeat The status, how frequently the requested Event will be repeated.
     * @param note More information about the requested Event.
     * @param userId The UserID of the respective user.
     *
     * @return The requested EventFx Object.
     */
    public static EventFx getEventFxObject(String location, LocalDateTime startTime, LocalDateTime endTime,
                                           boolean allDay, int repeat, String note, int userId){
        return new EventFxImpl(location, startTime, endTime, allDay, repeat, note, userId);
    }

    public static EventInvite getEventInviteObject(){
        return new EventInviteImpl();
    }
}
