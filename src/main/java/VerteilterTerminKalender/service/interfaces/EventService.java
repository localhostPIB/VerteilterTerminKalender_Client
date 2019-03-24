package VerteilterTerminKalender.service.interfaces;

import VerteilterTerminKalender.model.interfaces.EventFx;
import javafx.collections.ObservableList;

public interface EventService {

    public ObservableList<EventFx> getAllEvents(String userid);
    public int newEvent(EventFx event);;
    public String getEventByUserId(String userId);
    public String deleteEventFx (int eventId);
    public int modifyEventFx(EventFx event);
    public EventFx getEventByEventId(int eventId);




}
