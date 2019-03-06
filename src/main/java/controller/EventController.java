package controller;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static constants.Konfiguration.BASE_URL;


public class EventController {

    private final String ENDPOINT_GET_EVENT_BY_ID ="/event";

    private Client client;
    private WebTarget target;

    public EventController(){
        this.client = ClientBuilder.newClient();
        this. client.target(BASE_URL);

    }

    public String getEventByUserId(String userID) {
        String webContextPath = BASE_URL + ENDPOINT_GET_EVENT_BY_ID + "/" + userID;
        String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);

        return result;
    }
}
