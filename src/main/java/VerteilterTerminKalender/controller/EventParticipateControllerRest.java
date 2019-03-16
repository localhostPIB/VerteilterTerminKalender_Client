package VerteilterTerminKalender.controller;

import VerteilterTerminKalender.model.interfaces.EventParticipate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;

import static VerteilterTerminKalender.constants.Configuration.BASE_URL;

public class EventParticipateControllerRest {

    private final String ENDPOINT_GET_PARTICIPATE_BY_PARTICIPATE_ID = "/participate/";
    private final String ENDPOINT_GET_PARTICIPANTS_BY_EVENT_ID ="/participate/event/";
    private final String ENDPOINT_CREATE_PARTICIPATE ="/participate/add";
    private final String ENDPOINT_DELETE_PARTICIPATE_BY_PARTICIPATE_ID ="/participate/delete/";

    private Client client;
    private WebTarget target;

    public EventParticipateControllerRest() {
        client = ClientBuilder.newClient();
        client.target(BASE_URL);            //TODO: LINE ENTFERNEN?
        target = client.target(BASE_URL);
    }

    public EventParticipate getParticipate(int participateID){
        String webContextPath = ENDPOINT_GET_PARTICIPATE_BY_PARTICIPATE_ID + participateID;
        EventParticipate result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(EventParticipate.class);
        return null;
    }
    public ArrayList getParticipants(int eventID){
        return null;
    }
    public boolean createParticipate(EventParticipate eventParticipate){
        return false;
    }
    public boolean deleteParticipate(int participateID){
        return false;
    }
}
