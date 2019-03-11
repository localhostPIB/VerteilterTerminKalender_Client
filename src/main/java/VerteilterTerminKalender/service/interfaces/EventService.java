package VerteilterTerminKalender.service.interfaces;

import VerteilterTerminKalender.model.interfaces.EventFx;

import java.util.ArrayList;

public interface EventService {

    public ArrayList<EventFx> getAllEvents(String userid);
    //public boolean modifiyEvent(EventFx eventFx);
    


}
