package VerteilterTerminKalender.Test;



import VerteilterTerminKalender.model.classes.EventDeclineImpl;
import VerteilterTerminKalender.model.interfaces.EventDecline;
import VerteilterTerminKalender.service.classes.EventDeclineServiceImpl;

public class TestClassForEventDeclineServiceImpl {

    public static void main (String[] args)
    {
        TestClassForEventDeclineServiceImpl testClassForEventDeclineService = new TestClassForEventDeclineServiceImpl();
        testClassForEventDeclineService.testNewEventDecline();
    }

    private void testNewEventDecline() {
        EventDecline eventDecline = new EventDeclineImpl();
        eventDecline.setDeclineId(1235);
        eventDecline.setEventId(1234);
        eventDecline.setUserId(1122);

        EventDeclineServiceImpl eventDeclineService = new EventDeclineServiceImpl();
        System.out.println(eventDeclineService.newEventDecline(eventDecline));

    }
}
