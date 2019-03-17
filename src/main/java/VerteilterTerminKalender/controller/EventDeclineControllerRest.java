package VerteilterTerminKalender.controller;

import VerteilterTerminKalender.model.interfaces.EventDecline;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import static VerteilterTerminKalender.constants.Configuration.BASE_URL;

public class EventDeclineControllerRest
{

    private final String ENDPOINT_GET_EVENT_DECLINE ="/decline/";
    private final String ENDPOINT_GET_USER_WHO_DECLINED ="/decline/event/";
    private final String ENDPOINT_ADD_EVENT_DECLINE ="/decline/add";
    private final String ENDPOINT_DELETE_DECLINE ="/decline/delete/";


    private Client client;
    private WebTarget target;


    public EventDeclineControllerRest()
    {
        client = ClientBuilder.newClient();
        client.target(BASE_URL);
        target = client.target(BASE_URL);

    }

    public EventDecline getEventDeclineById(int declineId){

        String webContextPath = ENDPOINT_GET_EVENT_DECLINE + declineId;

        EventDecline response = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(EventDecline.class);

        return response;
    }

    public EventDecline getUserWhoDeclined(int eventId) {

        String webContextPath = ENDPOINT_GET_USER_WHO_DECLINED + eventId;
        EventDecline response = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(EventDecline.class);

        return response;
    }

    public Response newEventDecline(String eventDecline)
    {
        String webContextPath = ENDPOINT_ADD_EVENT_DECLINE + eventDecline;

        Response response = target.path(webContextPath).request().post(Entity.entity(eventDecline, MediaType.APPLICATION_JSON));

        return response;

    }

    public void deleteEventDeclineById(int declineId) {

        String webContextPath = ENDPOINT_DELETE_DECLINE + declineId;

        target.path(webContextPath).request().delete();

    }




}
