package VerteilterTerminKalender.model.classes;

import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Implementation of EventInvite interface
 */
@Getter
@Setter
@NoArgsConstructor

public class EventInviteImpl implements EventInvite {

    private int inviteId;
    private int userId;
    private int eventId;


    /**
     * Uses the toString()-method of class "EventFxImpl"
     * @return string e.g."Presentation 11:00-17:00"
     */
    @Override
    public String toString(){
        EventService eventService = new EventServiceImpl();
        EventFx eventFx = eventService.getEventByEventId(this.eventId);

        return eventFx.toString();
    }
}
