package VerteilterTerminKalender.Test;



import VerteilterTerminKalender.model.classes.EventDeclineImpl;
import VerteilterTerminKalender.model.interfaces.EventDecline;
import VerteilterTerminKalender.service.classes.EventDeclineServiceImpl;

public class TestClassForEventDeclineServiceImpl {

    public static void main (String[] args) {
        TestClassForEventDeclineServiceImpl testClassForEventDeclineService = new TestClassForEventDeclineServiceImpl();
        testClassForEventDeclineService.testNewEventDecline();
        testClassForEventDeclineService.deleteEventDeclineById();
        testClassForEventDeclineService.testEventDeclineById();
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

    private void testDeclineObject() {
        EventDecline eventDecline = new EventDeclineImpl();
        //eventDecline
    }

    private void  deleteEventDeclineById() {

        EventDeclineServiceImpl eventDeclineService = new EventDeclineServiceImpl();
        eventDeclineService.deleteEventDeclineById(238);
    }
}
