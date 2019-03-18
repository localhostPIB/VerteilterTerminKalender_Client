package VerteilterTerminKalender.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static VerteilterTerminKalender.constants.Configuration.BASE_URL;


public class EventControllerRest {

    private final String ENDPOINT_GET_EVENT_BY_ID ="/event/user/";
    private final String ENDPOINT_POST_EVENT = "/event/add";
    private final String ENDPOINT_UPDATE_EVENT ="/event/update";
    private final String ENDPOINT_POST_EVENT_INVITE = "/invitation/add";
    private final String ENDPOINT_GET_EVENT_INVITE = "/invitation/user/";
    private final String ENDPOINT_DELETE_EVENT ="/event/delete/";

    private Client client;
    private WebTarget target;

    public EventControllerRest(){
        client = ClientBuilder.newClient();
        client.target(BASE_URL);
        target = client.target(BASE_URL);

    }

    public String getEventByUserId(String userid) {
        String webContextPath = ENDPOINT_GET_EVENT_BY_ID +  userid;
        String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);

        return result;
    }

    public String deleteEventByUserId(int eventid) {
        String webContextPath = ENDPOINT_DELETE_EVENT + eventid;


        try {
            String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).delete(String.class);
            return "0";
        }catch (InternalServerErrorException e) {

        }

        return "-1";
    }



    public Response newEvent(String eventAsJsonString){
        Response response = target.path(ENDPOINT_POST_EVENT).request().post(Entity.entity(eventAsJsonString, MediaType.APPLICATION_JSON_TYPE));


        return response;
    }

    public Response newEventInvite(String eventInviteAsJsonString){

        Response response = target.path(ENDPOINT_POST_EVENT_INVITE).request().post(Entity.entity(eventInviteAsJsonString, MediaType.APPLICATION_JSON_TYPE));

        return response;
    }

    public String getAllEventInviteByUserId(String userId){
        String webContextPath = ENDPOINT_GET_EVENT_INVITE +  userId;
        String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);

        return result;
    }

    public Response modifyEvent(String eventAsJsonString){
        Response response = target.path(ENDPOINT_UPDATE_EVENT).request().put(Entity.entity(eventAsJsonString, MediaType.APPLICATION_JSON_TYPE));

        return response;
    }
}
