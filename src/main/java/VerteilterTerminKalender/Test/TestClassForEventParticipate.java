package VerteilterTerminKalender.Test;

import VerteilterTerminKalender.model.classes.EventParticipateImpl;
import VerteilterTerminKalender.model.interfaces.Event;
import VerteilterTerminKalender.model.interfaces.EventParticipate;
import VerteilterTerminKalender.service.classes.EventParticipateServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;

import java.sql.SQLOutput;


public class TestClassForEventParticipate {

    public static void main(String[] args) {
        EventParticipateService eventParticipateService = new EventParticipateServiceImpl();
        // System.out.println(eventParticipateService.getParticipate(177)); // TEST PASSED
        // System.out.println(eventParticipateService.getParticipants(172)); // TEST PASSED


        EventParticipate eventParticipate = new EventParticipateImpl();
        eventParticipate.setEventId(201);
        eventParticipate.setUserId(199);
        //eventParticipate.setParticipateId(0);

         System.out.println(eventParticipateService.newParticipate(eventParticipate)); // TEST PASSED
        // System.out.println(eventParticipateService.deleteParticipate(191)); // TEST PASSED
        // System.out.println(eventParticipateService.getAllParticipate()); // NOT TESTED YET
        System.out.println(eventParticipateService.getAllParticipate("199"));
    }


}
