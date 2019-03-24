package VerteilterTerminKalender.controller;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static VerteilterTerminKalender.constants.Configuration.BASE_URL;

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


    public String getAllEventInviteByUserId(String userId){
        String webContextPath = ENDPOINT_GET_EVENT_INVITE +  userId;
        String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);

        return result;
    }

    public Response newEventInvite(String eventInviteAsJsonString){

        Response response = target.path(ENDPOINT_POST_EVENT_INVITE).request().post(Entity.entity(eventInviteAsJsonString, MediaType.APPLICATION_JSON_TYPE));

        return response;
    }

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
