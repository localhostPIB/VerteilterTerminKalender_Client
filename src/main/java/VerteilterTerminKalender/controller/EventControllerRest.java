package VerteilterTerminKalender.controller;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static VerteilterTerminKalender.constants.Configuration.BASE_URL;


/**
 * This class provides REST functionality
 */
public class EventControllerRest {

    private final String ENDPOINT_GET_EVENT_BY_ID ="/event/user/";
    private final String ENDPOINT_POST_EVENT = "/event";
    private final String ENDPOINT_UPDATE_EVENT ="/event";
    private final String ENDPOINT_GET_EVENT_BY_EVENTID ="/event/";

    private final String ENDPOINT_DELETE_EVENT ="/event/";

    private Client client;
    private WebTarget target;

    public EventControllerRest(){
        client = ClientBuilder.newClient();
        client.target(BASE_URL);
        target = client.target(BASE_URL);

    }

    /**
     * Fetch a Event from Server
     * Method returns the user as a JSON String
     * @param userid Id of a User
     * @return String
     */
    public String getEventByUserId(String userid) {
        String webContextPath = ENDPOINT_GET_EVENT_BY_ID +  userid;
        String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);

        return result;
    }

    /**
     * Delete a Event
     * @param eventid Id of a Event
     * @return 0: successfully deleted, -1: error
     */
    public String deleteEventByEventId(int eventid) {
        String webContextPath = ENDPOINT_DELETE_EVENT + eventid;


        try {
            String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).delete(String.class);
            return "0";
        }catch (InternalServerErrorException e) {

        }

        return "-1";
    }


    /**
     * Create a new event
     * @param eventAsJsonString event object as a json string
     * @return Response A http response
     */
    public Response newEvent(String eventAsJsonString){
        Response response = target.path(ENDPOINT_POST_EVENT).request().post(Entity.entity(eventAsJsonString, MediaType.APPLICATION_JSON_TYPE));


        return response;
    }


    /**
     * Modify a event
     * @param eventAsJsonString event object as a json string
     * @return Response A http response
     */
    public Response modifyEvent(String eventAsJsonString){
        Response response = target.path(ENDPOINT_UPDATE_EVENT).request().put(Entity.entity(eventAsJsonString, MediaType.APPLICATION_JSON_TYPE));

        return response;
    }

    /**
     * Fetches a event from server
     * @param eventId Id of the event
     * @return String result of the
     */
    public String getEventByEventId(int eventId){
        String webContextPath = ENDPOINT_GET_EVENT_BY_EVENTID +  eventId;
        String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);

        return result;
    }
}
