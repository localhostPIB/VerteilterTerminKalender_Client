package VerteilterTerminKalender.util;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.service.interfaces.EventService;

import static VerteilterTerminKalender.builders.ServiceObjectBuilder.getEventService;

public class SyncAll {
    public static void syncAll(MainApp mainApp, String userId){
        EventService eventService = getEventService();
        mainApp.=eventService.getAllEvents(userId);
    }

}
