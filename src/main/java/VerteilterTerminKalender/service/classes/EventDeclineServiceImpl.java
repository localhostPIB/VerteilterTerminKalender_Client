package VerteilterTerminKalender.service.classes;

import VerteilterTerminKalender.controller.EventDeclineControllerRest;
import VerteilterTerminKalender.model.interfaces.EventDecline;
import VerteilterTerminKalender.service.interfaces.EventDeclineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.core.Response;
import java.io.IOException;

public class EventDeclineServiceImpl implements EventDeclineService {

    private ObjectMapper mapper = new ObjectMapper();

    EventDeclineControllerRest eventDeclineControllerRest = new EventDeclineControllerRest();

    @Override
    public EventDecline getEventDeclineById(int declineId) {

        try {
                String jsonResult = eventDeclineControllerRest.getEventDeclineById(declineId);
                EventDecline jsonToObject = mapper.readValue(jsonResult, EventDecline.class);

                return jsonToObject;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public String deleteEventDeclineById(int declineId) {

        String response = eventDeclineControllerRest.deleteEventDeclineById(declineId);

        return response;

    }
}
