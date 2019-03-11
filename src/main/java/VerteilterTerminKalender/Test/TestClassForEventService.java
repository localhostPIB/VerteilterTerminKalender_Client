package VerteilterTerminKalender.Test;

import VerteilterTerminKalender.controller.EventControllerRest;
import VerteilterTerminKalender.service.classes.EventServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventService;

public class TestClassForEventService {
    public static void main(String[] args){

    TestClassForEventService testClassForEventService = new TestClassForEventService();
    testClassForEventService.testgetAllEvents();

    }


    private void testgetAllEvents(){
        EventService eventService = new EventServiceImpl();
        eventService.getAllEvents("42");

        EventControllerRest eventControllerRest = new EventControllerRest();

        //String result = eventControllerRest.getEventByUserId("42");
        //System.out.println(result);

    }
}
