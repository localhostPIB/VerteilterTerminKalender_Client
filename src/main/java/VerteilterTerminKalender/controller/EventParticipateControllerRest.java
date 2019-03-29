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
 * Class fetches and send event participate to the server via REST API
 */
public class EventParticipateControllerRest {

    private final String ENDPOINT_GET_PARTICIPATE_BY_PARTICIPATE_ID = "/participate/";
    private final String ENDPOINT_GET_PARTICIPANTS_BY_EVENT_ID ="/participate/event/";
    private final String ENDPOINT_NEW_PARTICIPATE ="/participate";
    private final String ENDPOINT_DELETE_PARTICIPATE_BY_PARTICIPATE_ID ="/participate/";
    private final String ENDPOINT_GET_PARTICIPATES_BY_USER_ID ="/participate/user/";


    private Client client;
    private WebTarget target;

    public EventParticipateControllerRest() {
        client = ClientBuilder.newClient();
        target = client.target(BASE_URL);
    }

    /**
     * Fetch all a participate from the server
     * @param participateID Id of the participate object
     * @return the participate object as json string
     */
    public String getParticipate(int participateID) {
        String webContextPath = ENDPOINT_GET_PARTICIPATE_BY_PARTICIPATE_ID + participateID;
        return target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);
    }

    /**
     * Get all participants by a given id
     * @param eventID id of an event
     * @return all participants of an event
     */
    public String getParticipants(int eventID){
        String webContextPath = ENDPOINT_GET_PARTICIPANTS_BY_EVENT_ID + eventID;
        return target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);
    }


    /**
     * Create a new participate oject
     * @param eventParticipateAsJsonString participante object converted as json string
     * @return http response
     */
    public Response newParticipate(String eventParticipateAsJsonString){
        String webContextPath = ENDPOINT_NEW_PARTICIPATE;
        return target.path(webContextPath).request().post(Entity.entity(eventParticipateAsJsonString, MediaType.APPLICATION_JSON));

    }

    /**
     * Delete a participate object
     * @param participateID id of a participate object
     * @return String
     */
    public String deleteParticipate(int participateID){
        String webContextPath = ENDPOINT_DELETE_PARTICIPATE_BY_PARTICIPATE_ID + participateID;
        try {
            target.path(webContextPath).request(MediaType.APPLICATION_JSON).delete(String.class);
            return "0";
        }catch (InternalServerErrorException e) {

        }
        return "-1";
    }


    /**
     * Get all participates by a given userId
     * @param userId id of the user
     * @return List with all participates
     */
    public String getAllParticipate(String userId) {
        String webContextPath = ENDPOINT_GET_PARTICIPATES_BY_USER_ID + userId;
        String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).delete(String.class);
        return result;
    }
}
