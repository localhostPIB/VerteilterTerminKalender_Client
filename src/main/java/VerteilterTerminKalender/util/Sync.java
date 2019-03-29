package VerteilterTerminKalender.util;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import VerteilterTerminKalender.service.classes.EventParticipateServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventInviteService;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;
import VerteilterTerminKalender.service.interfaces.EventService;
import VerteilterTerminKalender.sse.SSEClient;
import javafx.collections.ObservableList;

import static VerteilterTerminKalender.builders.ServiceObjectBuilder.getEventInviteService;
import static VerteilterTerminKalender.builders.ServiceObjectBuilder.getEventService;

/**
 * This class provides methods to synchronize data
 */
public class Sync {
    /**
     * Fetch all relevant data from the server an loads into the GUI
     * @param mainApp Object
     * @param userId id of a user
     */
    public static void all(MainApp mainApp, String userId){
        EventService eventService = getEventService();
        EventInviteService eventInviteService = getEventInviteService();
        EventParticipateService eventParticipateService = new EventParticipateServiceImpl();
        ObservableList<EventFx> eventFXList = mainApp.getEventFXList();


        //Fetch all events from server and write to Obervablelist in Mainapp
        mainApp.setEventFXList(eventService.getAllEvents(userId));

        //Fetch all invitations from server and write to Obervablelist in Mainapp
        mainApp.setEventInvitesList(eventInviteService.getAllEventInviteByUserId(userId));

/*       ArrayList<EventParticipate> eventParticipates = eventParticipateService.getAllParticipate(mainApp.getUser().getUserId());

        if(eventParticipates != null) {

            for (EventParticipate eventParticipate : eventParticipates) {
                EventFx eventFx = eventService.getEventByEventId(eventParticipate.getEventId());
                eventFXList.add(eventFx);
            }

        }*/


       //mainApp.setEventFXList();


    }

    /**
     * Executes the initial sync. Reported to the sse server
     * @param mainApp object
     * @param userId if of the user
     */
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
