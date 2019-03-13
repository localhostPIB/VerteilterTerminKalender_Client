package VerteilterTerminKalender.Test;

import VerteilterTerminKalender.controller.EventControllerRest;
import VerteilterTerminKalender.model.classes.EventFxImpl;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventService;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TestClassForEventService {
    public static void main(String[] args){

    TestClassForEventService testClassForEventService = new TestClassForEventService();
    //testClassForEventService.testgetAllEvents();
        testClassForEventService.testNewEvent();

    }


    private void testgetAllEvents(){
        EventService eventService = new EventServiceImpl();

        ArrayList<EventFx>map= eventService.getAllEvents("65");

        EventControllerRest eventControllerRest = new EventControllerRest();

        String result = eventControllerRest.getEventByUserId("42");
        //System.out.println(result);

    }

    private void testNewEvent(){
        EventService eventService = new EventServiceImpl();
        LocalDateTime localDateTime = LocalDateTime.now();

        EventFx eventFx = new EventFxImpl("htw",localDateTime,localDateTime,"0",0,"Meeting", 42);

        eventService.newEvent(eventFx);



    }
}
