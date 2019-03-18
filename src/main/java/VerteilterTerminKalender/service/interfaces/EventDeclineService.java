package VerteilterTerminKalender.service.interfaces;

import VerteilterTerminKalender.model.interfaces.EventDecline;
import VerteilterTerminKalender.model.interfaces.EventDeclineUser;

import javax.ws.rs.core.Response;
import java.util.List;

public interface EventDeclineService {

    EventDecline getEventDeclineById(int declineId);

    List<EventDeclineUser> getUserWhoDeclined(int eventId);

    Response newEventDecline(EventDecline eventDecline);

    String deleteEventDeclineById(int declineId);


}
