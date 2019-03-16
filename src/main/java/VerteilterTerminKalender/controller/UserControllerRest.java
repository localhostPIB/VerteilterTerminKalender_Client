package VerteilterTerminKalender.controller;


import VerteilterTerminKalender.model.interfaces.User;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import static VerteilterTerminKalender.constants.Configuration.BASE_URL;

public class UserControllerRest {

    private final String ENDPOINT_GET_USER_BY_ID ="/user";
    private final String ENDPOINT_POST_USER = "/user/add";

    private Client client;
    private WebTarget target;



    public UserControllerRest(){
        client = ClientBuilder.newClient();
        //client.target(BASE_URL);
        target = client.target(BASE_URL);

    }


    public String getUserById(String userid){

        String webContextPath = ENDPOINT_GET_USER_BY_ID +"/" +userid;

        String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);

        return result;
    }


    public Response createUser (String userAsJsonString){
        //TODO Was soll zurück geben werden, wenn die Operation erfolgreich bzw. nicht erfolgreich war
        Response response = target.path(ENDPOINT_POST_USER).request().post(Entity.entity(userAsJsonString, MediaType.APPLICATION_JSON_TYPE));

        return response;

    }



}
