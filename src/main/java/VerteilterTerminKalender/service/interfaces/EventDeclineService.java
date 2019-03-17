package VerteilterTerminKalender.service.interfaces;

import VerteilterTerminKalender.model.interfaces.EventDecline;

import javax.ws.rs.core.Response;

public interface EventDeclineService {

    EventDecline getEventDeclineById(int declineId);

    EventDecline getUserWhoDeclined(int eventId);

    Response newEventDecline(EventDecline eventDecline);

    String deleteEventDeclineById(int declineId);


}
