package VerteilterTerminKalender.Test;

import VerteilterTerminKalender.builders.ServiceObjectBuilder;
import VerteilterTerminKalender.controller.EventControllerRest;
import VerteilterTerminKalender.model.classes.EventFxImpl;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventService;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestClassForEventService {
    public static void main(String[] args){

        TestClassForEventService testClassForEventService = new TestClassForEventService();
        //testClassForEventService.testgetAllEvents();
        //testClassForEventService.testNewEvent();
        //testClassForEventService.testgetAllEventInviteByUserId();
        testClassForEventService.testDeleteEventFx();

    }


    private void testgetAllEvents(){
        EventService eventService = new EventServiceImpl();

       // ArrayList<EventFx>map= eventService.getAllEvents("65");

        EventControllerRest eventControllerRest = new EventControllerRest();

        String result = eventControllerRest.getEventByUserId("42");
        //System.out.println(result);

    }

    private void testNewEvent(){
        EventService eventService = new EventServiceImpl();
        LocalDateTime localDateTime = LocalDateTime.now();

        EventFx eventFx = new EventFxImpl("htw",localDateTime,localDateTime,false,0,"Meeting", 42);

        int status = eventService.newEvent(eventFx);
        System.out.println(status);


    }


    private void testgetAllEventInviteByUserId(){
        EventService eventService = ServiceObjectBuilder.getEventService();
        ObservableList<EventInvite> eventInvitesList = eventService.getAllEventInviteByUserId("165");
        System.out.println(eventInvitesList);
        System.out.println(eventInvitesList.size());
    }


    private void testDeleteEventFx(){
        EventService eventService = ServiceObjectBuilder.getEventService();
        String result = eventService.deleteEventFx(173);
        System.out.println(result);
    }


}
