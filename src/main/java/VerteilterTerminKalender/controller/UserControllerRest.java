package VerteilterTerminKalender.controller;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static VerteilterTerminKalender.constants.Configuration.BASE_URL;

public class UserControllerRest {

    private final String ENDPOINT_GET_USER_BY_ID ="/user/id";
    private final String ENDPOINT_POST_USER = "/user/add";
    private final String ENDPOINT_FIND_ID_BY_EMAIL ="/user/findid";
    private final String ENDPOINT_GET ="/user/findid";


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


    public String findIdByEmail(String email) {


        String result = target.path(ENDPOINT_FIND_ID_BY_EMAIL).queryParam("email",email).request(MediaType.APPLICATION_JSON).get(String.class);


        return result;
    }




}
