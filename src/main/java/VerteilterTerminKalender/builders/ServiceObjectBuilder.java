package VerteilterTerminKalender.builders;

import VerteilterTerminKalender.service.classes.*;
import VerteilterTerminKalender.service.interfaces.*;

/**
 * This class provides a centralized access point for getting Service Objects.
 *
 * @author Johannes Gerwert
 * @author Michelle Blau
 * @version 19.03.2019
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

    /**
     * Creates an EventParticipate Object with the Standard Constructor.
     *
     * @return The requested EventParticpate Object.
     */
    public static EventParticipateService getEventParticipateService(){
        return new EventParticipateServiceImpl();
    }

    /**
     * Creates an EventInviteService Object with the Standard Constructor.
     *
     * @return The requested EventInviteService Object.
     */
    public static EventInviteService getEventInviteService(){
        return new EventInviteServiceImpl();
    }

    /**
     * Creates an EventDeclineService Object with the Standard Constructor.
     *
     * @return The requested EventDeclineService Object.
     */
    public static EventDeclineService getEventDeclineService(){
        return new EventDeclineServiceImpl();
    }


}
