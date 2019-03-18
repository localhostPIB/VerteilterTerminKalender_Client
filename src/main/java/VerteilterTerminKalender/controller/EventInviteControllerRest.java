package VerteilterTerminKalender.controller;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static VerteilterTerminKalender.constants.Configuration.BASE_URL;

public class EventInviteControllerRest {


    private final String ENDPOINT_GET_EVENT_INVITE = "/invitation/user/";


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
}
