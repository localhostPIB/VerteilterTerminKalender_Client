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
 *  Class fetches and send event invite to the server via REST API
 */
public class EventInviteControllerRest {


    private final String ENDPOINT_GET_EVENT_INVITE = "/invitation/user/";
    private final String ENDPOINT_POST_EVENT_INVITE = "/invitation";
    private final String ENPOINT_DELETE_EVENT_INVITE = "/invitation/";




    private Client client;
    private WebTarget target;

    public EventInviteControllerRest(){
        client = ClientBuilder.newClient();
        client.target(BASE_URL);
        target = client.target(BASE_URL);

    }


    /**
     * This methode fetchs all event invite by a given userId
     * @param userId Id of the user
     * @return Returns a String 
     */
    public String getAllEventInviteByUserId(String userId){
        String webContextPath = ENDPOINT_GET_EVENT_INVITE +  userId;
        String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);

        return result;
    }

    /**
     * This methode post a new event invite to the server
     * @param eventInviteAsJsonString
     * @return Returns a http reponse
     */
    public Response newEventInvite(String eventInviteAsJsonString){

        Response response = target.path(ENDPOINT_POST_EVENT_INVITE).request().post(Entity.entity(eventInviteAsJsonString, MediaType.APPLICATION_JSON_TYPE));

        return response;
    }

    /**
     * Deletes an event inivte 
     * @param eventInviteId
     * @return
     */
    public String deleteEventInviteByEventInviteId(int eventInviteId) {
        String webContextPath = ENPOINT_DELETE_EVENT_INVITE + eventInviteId;

        try {
            String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).delete(String.class);
            return "0";
        }catch (InternalServerErrorException e) {

        }

        return "-1";
    }
}
