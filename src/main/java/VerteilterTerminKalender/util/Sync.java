package VerteilterTerminKalender.util;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import VerteilterTerminKalender.service.interfaces.EventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

import static VerteilterTerminKalender.builders.ServiceObjectBuilder.getEventService;

public class Sync {
    public static void all(MainApp mainApp, String userId){
        EventService eventService = getEventService();

        //Fetch all events from server and write to Obervablelist in Mainapp
        mainApp.setEventFXList(eventService.getAllEvents(userId));

        //Fetch all invitations from server and write to Obervablelist in Mainapp
        mainApp.setEventInvitesList(eventService.getAllEventInviteByUserId(userId));





       //mainApp.setEventFXList();


    }

}
