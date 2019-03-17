package VerteilterTerminKalender.util;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;
import VerteilterTerminKalender.service.interfaces.EventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Comparator;

import static VerteilterTerminKalender.builders.ServiceObjectBuilder.getEventParticipateService;
import static VerteilterTerminKalender.builders.ServiceObjectBuilder.getEventService;

public class Sync {
    public static void all(MainApp mainApp, String userId){
        EventService eventService = getEventService();
        EventParticipateService eventParticipateService = getEventParticipateService();

        //Fetch all events from server and write to Obervablelist in Mainapp
        mainApp.setEventFXList(eventService.getAllEvents(userId));

        //Fetch all invitations from server and write to Obervablelist in Mainapp
        mainApp.setEventInvitesList(eventService.getAllEventInviteByUserId(userId));





       //mainApp.setEventFXList();


    }

    public static void initiateConnection(MainApp mainApp, String userId){
        Sync.all(mainApp, userId);
        ObservableList<EventInvite> eventInvites = mainApp.getEventInvitesList();
        Comparator<EventInvite> sortGreatest = (ei1, ei2) ->{
            if(ei1.getInviteId()<ei2.getInviteId()){
                return 1;
            }
            if(ei1.getInviteId()>ei2.getInviteId()){
                return -1;
            }

            return 0;

        };

        System.out.println(eventInvites.sorted(sortGreatest).toArray());
        System.out.println("test");

    }





}
