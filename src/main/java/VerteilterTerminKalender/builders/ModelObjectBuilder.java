package VerteilterTerminKalender.builders;

import VerteilterTerminKalender.model.classes.*;
import VerteilterTerminKalender.model.interfaces.*;
import VerteilterTerminKalender.view.model.EvenSimplerStringProperty;
import javafx.beans.property.SimpleStringProperty;
import org.glassfish.jersey.message.internal.HttpHeaderReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


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

    public static EventParticipate getEventParticipateObject(){
        return new EventParticipateImpl();
    }

    public static EventDecline getEventDeclineObject(){
        return new EventDeclineImpl();
    }

    public static EventSimpleString getEventSimpleStringObject(EventFx eventFx){
        EventSimpleString eventSimpleString = new EventSimpleString();

        eventSimpleString.setEventId(new EvenSimplerStringProperty(eventFx.getEventId().getValue().toString()));
        eventSimpleString.setLocation(new EvenSimplerStringProperty(eventFx.getLocation().getValue()));

        String startTime = eventFx.getStartTime().getValue().toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        eventSimpleString.setStartTime(new EvenSimplerStringProperty(startTime));

        String endTime = eventFx.getEndTime().getValue().toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        eventSimpleString.setEndTime(new EvenSimplerStringProperty(endTime));

        eventSimpleString.setAllDay(new EvenSimplerStringProperty(eventFx.getAllDay().getValue().toString()));
        eventSimpleString.setRepeat(new EvenSimplerStringProperty(eventFx.getRepeat().getValue().toString()));
        eventSimpleString.setNote(new EvenSimplerStringProperty(eventFx.getNote().getValue()));
//        eventSimpleString.setUserId(new SimpleStringProperty(eventFx.getUserId().getValue().toString()));

        return eventSimpleString;
    }
}
