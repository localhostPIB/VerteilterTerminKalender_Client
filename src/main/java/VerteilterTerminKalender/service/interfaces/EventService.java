package VerteilterTerminKalender.service.interfaces;

import VerteilterTerminKalender.model.interfaces.EventFx;

import java.util.ArrayList;

public interface EventService {

    public ArrayList<EventFx> getAllEvents(String userid);

    int newEvent(EventFx eventFx);
    //public boolean modifiyEvent(EventFx eventFx);
    


}
