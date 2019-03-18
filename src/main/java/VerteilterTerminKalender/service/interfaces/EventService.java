package VerteilterTerminKalender.service.interfaces;

import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface EventService {

    public ObservableList<EventFx> getAllEvents(String userid);
    public int newEvent(EventFx event);
    public int newEventInvite(EventInvite eventInvite);
    public String getEventByUserId(String userId);
    public String deleteEventFx (int eventId);
    public int modifyEventFx(EventFx event);




}
