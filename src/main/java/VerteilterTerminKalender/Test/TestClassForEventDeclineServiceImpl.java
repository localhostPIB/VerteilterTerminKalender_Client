package VerteilterTerminKalender.Test;



import VerteilterTerminKalender.model.classes.EventDeclineImpl;
import VerteilterTerminKalender.model.classes.EventDeclineUserImpl;
import VerteilterTerminKalender.model.interfaces.EventDecline;
import VerteilterTerminKalender.model.interfaces.EventDeclineUser;
import VerteilterTerminKalender.service.classes.EventDeclineServiceImpl;

public class TestClassForEventDeclineServiceImpl {

    public static void main (String[] args) {
        TestClassForEventDeclineServiceImpl testClassForEventDeclineService = new TestClassForEventDeclineServiceImpl();
      //  testClassForEventDeclineService.testNewEventDecline();
        //testClassForEventDeclineService.testDeleteEventDeclineById();
        //testClassForEventDeclineService.testEventDeclineById();
        testClassForEventDeclineService.testUserWhoDeclined();
    }

    private void testEventDeclineById() {

        EventDeclineServiceImpl eventDeclineService = new EventDeclineServiceImpl();
        eventDeclineService.getEventDeclineById(239);
    }

    private void testNewEventDecline() {
        EventDecline eventDecline = new EventDeclineImpl();
        eventDecline.setDeclineId(235);
        eventDecline.setEventId(226);
        eventDecline.setUserId(219);

        EventDeclineServiceImpl eventDeclineService = new EventDeclineServiceImpl();
        eventDeclineService.newEventDecline(eventDecline);

    }

    private void testUserWhoDeclined() {

        EventDeclineServiceImpl eventDeclineService = new EventDeclineServiceImpl();
        System.out.println(eventDeclineService.getUserWhoDeclined(170));
    }

    private void  testDeleteEventDeclineById() {

        EventDeclineServiceImpl eventDeclineService = new EventDeclineServiceImpl();
        eventDeclineService.deleteEventDeclineById(170);
    }
}
