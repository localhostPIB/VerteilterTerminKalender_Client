package VerteilterTerminKalender.builders;

import VerteilterTerminKalender.service.classes.EventParticipateServiceImpl;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.classes.UserServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;
import VerteilterTerminKalender.service.interfaces.EventService;
import VerteilterTerminKalender.service.interfaces.UserService;

/**
 * This class provides a centralized access point for getting Service Objects.
 *
 * @author Johannes Gerwert
 * @version 16.03.2019
 */
public class ServiceObjectBuilder {

    /**
     * Creates an EventService Object with the Standard Constructor.
     *
     * @return The requested EventService Object.
     */
    public static EventService getEventService(){
        return new EventServiceImpl();
    }

    /**
     * Creates an UserService Object with the Standard Constructor.
     *
     * @return The requested UserService Object.
     */
    public static UserService getUserService(){
        return new UserServiceImpl();
    }

    public static EventParticipateService getEventParticipateService(){
        return new EventParticipateServiceImpl();
    }
}
