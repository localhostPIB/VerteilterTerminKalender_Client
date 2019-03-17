package VerteilterTerminKalender.service.classes;

import VerteilterTerminKalender.controller.EventDeclineControllerRest;
import VerteilterTerminKalender.model.interfaces.EventDecline;
import VerteilterTerminKalender.service.interfaces.EventDeclineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.core.Response;

public class EventDeclineServiceImpl implements EventDeclineService {

    private ObjectMapper mapper = new ObjectMapper();
    EventDeclineControllerRest eventDeclineControllerRest = new EventDeclineControllerRest();

    @Override
    public EventDecline getEventDeclineById(int declineId) {
        return null;
    }

    @Override
    public EventDecline getUserWhoDeclined(int eventId) {
        return null;
    }

    @Override
    public Response newEventDecline(EventDecline eventDecline) {

        try {
            String jsonInString = mapper.writeValueAsString(eventDecline);
            Response response = eventDeclineControllerRest.newEventDecline(jsonInString);
            return response;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void deleteEventDeclineById(int declineId) {

    }
}
