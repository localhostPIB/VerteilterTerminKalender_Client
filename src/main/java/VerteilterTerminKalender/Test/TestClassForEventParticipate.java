package VerteilterTerminKalender.Test;

import VerteilterTerminKalender.model.classes.EventDeclineImpl;
import VerteilterTerminKalender.model.classes.EventParticipateImpl;
import VerteilterTerminKalender.model.interfaces.EventDecline;
import VerteilterTerminKalender.model.interfaces.EventParticipate;
import VerteilterTerminKalender.service.classes.EventDeclineServiceImpl;
import VerteilterTerminKalender.service.classes.EventParticipateServiceImpl;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;

/**
 * Created by kerim on 16.03.2019.
 */
public class TestClassForEventParticipate {

    public static void main(String[] args) {
        EventParticipateService eventParticipateService = new EventParticipateServiceImpl();
        // System.out.println(eventParticipateService.getParticipate(177)); // TEST PASSED
        // System.out.println(eventParticipateService.getParticipants(172)); // TEST PASSED
         System.out.println(eventParticipateService.newParticipate(new EventParticipateImpl(424, 166, 172))); // NOT TESTED YET
        // eventParticipateService.deleteParticipate(333); // NOT TESTED YET
        // System.out.println(eventParticipateService.getAllParticipate()); // NOT TESTED YET
    }


}
