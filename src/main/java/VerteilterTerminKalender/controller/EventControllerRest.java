package VerteilterTerminKalender.controller;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static VerteilterTerminKalender.constants.Configuration.BASE_URL;


public class EventControllerRest {

    private final String ENDPOINT_GET_EVENT_BY_ID ="/event";
    private final String ENDPOINT_POST_EVENT = "/event/add";
    private final String ENDPOINT_DELETE_EVENT ="/event/delete/";
    private final String ENDPOINT_FIND_ID_BY_EMAIL ="/user/findid?email=";



    private Client client;
    private WebTarget target;

    public EventControllerRest(){
        client = ClientBuilder.newClient();
        client.target(BASE_URL);
        target = client.target(BASE_URL);

    }

    public String getEventByUserId(String userid) {
        String webContextPath = ENDPOINT_GET_EVENT_BY_ID + "/user/" + userid;
        String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);

        return result;
    }
    public String deleteEventByUserId(int eventid) {
        String webContextPath = ENDPOINT_DELETE_EVENT + eventid;
        String response = target.path(webContextPath).request(MediaType.APPLICATION_JSON).delete(String.class);

        return response;

    }

    public String findIdByEmail(String email) {
        String webContextPath = "/user/findid?email=" + email;

        String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);

        return result;
    }

    public Response newEvent(String eventAsJsonString){
        Response response = target.path(ENDPOINT_POST_EVENT).request().post(Entity.entity(eventAsJsonString, MediaType.APPLICATION_JSON_TYPE));


        return response;
    }
}
