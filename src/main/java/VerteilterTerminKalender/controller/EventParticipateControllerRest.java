package VerteilterTerminKalender.controller;

import VerteilterTerminKalender.model.classes.EventParticipateImpl;
import VerteilterTerminKalender.model.interfaces.EventParticipate;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.util.ArrayList;

import static VerteilterTerminKalender.constants.Configuration.BASE_URL;

public class EventParticipateControllerRest {

    private final String ENDPOINT_GET_PARTICIPATE_BY_PARTICIPATE_ID = "/participate/";
    private final String ENDPOINT_GET_PARTICIPANTS_BY_EVENT_ID ="/participate/event/";
    private final String ENDPOINT_CREATE_PARTICIPATE ="/participate/add";
    private final String ENDPOINT_DELETE_PARTICIPATE_BY_PARTICIPATE_ID ="/participate/delete/";

    private Client client;
    private WebTarget target;

    ObjectMapper objectMapper = new ObjectMapper();

    public EventParticipateControllerRest() {
        client = ClientBuilder.newClient();
        target = client.target(BASE_URL);
    }

    /**
     *
     * @param participateID
     * @return
     */
    public String getParticipate(int participateID) {
        String webContextPath = ENDPOINT_GET_PARTICIPATE_BY_PARTICIPATE_ID + participateID;
        return target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);
    }

    /**
     *
     * @param eventID
     * @return
     */
    public ArrayList getParticipants(int eventID){
        return null;
    }

    /**
     *
     * @param eventParticipate
     * @return
     */
    public boolean newParticipate(EventParticipate eventParticipate){
        return false;
    }

    /**
     *
     * @param participateID
     * @return
     */
    public boolean deleteParticipate(int participateID){
        return false;
    }

    /**
     *
     * @return
     */
    public ObservableList<EventParticipateImpl> getAllParticipate() {
        return null;
    }
}
