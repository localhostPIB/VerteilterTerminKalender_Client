package VerteilterTerminKalender.Test;

import VerteilterTerminKalender.model.classes.EventParticipateImpl;
import VerteilterTerminKalender.service.classes.EventParticipateServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;


public class TestClassForEventParticipate {

    public static void main(String[] args) {
        EventParticipateService eventParticipateService = new EventParticipateServiceImpl();
        // System.out.println(eventParticipateService.getParticipate(177)); // TEST PASSED
        // System.out.println(eventParticipateService.getParticipants(172)); // TEST PASSED
        // System.out.println(eventParticipateService.newParticipate(new EventParticipateImpl(0, 166, 172))); // TEST PASSED
        // System.out.println(eventParticipateService.deleteParticipate(191)); // TEST PASSED
        // System.out.println(eventParticipateService.getAllParticipate()); // NOT TESTED YET
    }


}
