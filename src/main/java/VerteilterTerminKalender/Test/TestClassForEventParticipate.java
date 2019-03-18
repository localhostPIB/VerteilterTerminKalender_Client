package VerteilterTerminKalender.Test;

import VerteilterTerminKalender.model.classes.EventParticipateImpl;
import VerteilterTerminKalender.service.classes.EventParticipateServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;

/**
 * Created by kerim on 16.03.2019.
 */
public class TestClassForEventParticipate {

    public static void main(String[] args) {
        EventParticipateService eventParticipateService = new EventParticipateServiceImpl();
         System.out.println(eventParticipateService.getParticipate(177)); // TEST PASSED
        // System.out.println(eventParticipateService.getParticipants(172)); // NOT TESTED YET
        // System.out.println(eventParticipateService.createParticipate(new EventParticipateImpl(88, 69))); // NOT TESTED YET
        // System.out.println(eventParticipateService.deleteParticipate(9000)); // NOT TESTED YET
        // System.out.println(eventParticipateService.getAllParticipate()); // NOT TESTED YET
    }
}
