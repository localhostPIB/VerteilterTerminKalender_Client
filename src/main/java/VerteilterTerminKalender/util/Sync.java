package VerteilterTerminKalender.util;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import VerteilterTerminKalender.service.interfaces.EventInviteService;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;
import VerteilterTerminKalender.service.interfaces.EventService;
import VerteilterTerminKalender.sse.SSEClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Comparator;

import static VerteilterTerminKalender.builders.ServiceObjectBuilder.*;

public class Sync {
    public static void all(MainApp mainApp, String userId){
        EventService eventService = getEventService();
        EventInviteService eventInviteService = getEventInviteService();


        //Fetch all events from server and write to Obervablelist in Mainapp
        mainApp.setEventFXList(eventService.getAllEvents(userId));

        //Fetch all invitations from server and write to Obervablelist in Mainapp
        mainApp.setEventInvitesList(eventInviteService.getAllEventInviteByUserId(userId));





       //mainApp.setEventFXList();


    }

    public static void initiateConnection(MainApp mainApp, String userId){
        Sync.all(mainApp, userId);
        ObservableList<EventInvite> eventInvites = mainApp.getEventInvitesList();

        int lastinviteid = 0;

        if(eventInvites.size()>0){
            lastinviteid = eventInvites.get(eventInvites.size()-1).getInviteId();
        }


        try {
            SSEClient.sseCient(Integer.parseInt(userId),lastinviteid);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }





}
