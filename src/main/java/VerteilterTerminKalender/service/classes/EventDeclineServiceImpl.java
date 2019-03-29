package VerteilterTerminKalender.service.classes;

import VerteilterTerminKalender.controller.EventDeclineControllerRest;
import VerteilterTerminKalender.model.interfaces.EventDecline;
import VerteilterTerminKalender.model.interfaces.EventDeclineUser;
import VerteilterTerminKalender.service.interfaces.EventDeclineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Implementation of EventDeclineService interface
 */
public class EventDeclineServiceImpl implements EventDeclineService {

    private ObjectMapper mapper = new ObjectMapper();

    EventDeclineControllerRest eventDeclineControllerRest = new EventDeclineControllerRest();

    /**
     * Get a event decline object from the server
     * @param declineId id of the event decline
     * @return EventDecline object
     */
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


    /**
     * Get a List of users which declined by a specified id
     * @param eventId id of a event
     * @return List of EventDeclineUser
     */
    @Override
    public List<EventDeclineUser> getUserWhoDeclined(int eventId) {

        try {
            String jsonResult = eventDeclineControllerRest.getUserWhoDeclined(eventId);

            List<EventDeclineUser> jsonToList =   mapper.readValue(jsonResult, new TypeReference<List<EventDeclineUser>>(){});

            return jsonToList;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Add a new event decline
     * @param eventDecline object
     * @return response if operation is successfully executed, null if error
     */
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


    /**
     * Delete a event decline
     * @param declineId id of an event decline object
     * @return http response
     */
    @Override
    public String deleteEventDeclineById(int declineId) {

        String response = eventDeclineControllerRest.deleteEventDeclineById(declineId);

        return response;

    }
}
