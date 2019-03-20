package VerteilterTerminKalender.model.classes;

import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

public class EventInviteImpl implements EventInvite {

    private int inviteId;
    private int userId;
    private int eventId;


    @Override
    public String toString(){
        EventService eventService = new EventServiceImpl();
        EventFx eventFx = eventService.getEventByEventId(this.eventId);

        return eventFx.toString();
    }
}
