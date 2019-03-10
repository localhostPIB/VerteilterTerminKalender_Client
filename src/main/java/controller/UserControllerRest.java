package controller;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static constants.Konfiguration.BASE_URL;

public class UserControllerRest {

    private final String ENDPOINT_GET_USER_BY_ID ="/user";

    private Client client;
    private WebTarget target;



    public UserControllerRest(){
        client = ClientBuilder.newClient();
        client.target(BASE_URL);

    }


    public String getUserById(String userID){
        String webContextPath = BASE_URL + ENDPOINT_GET_USER_BY_ID + "/" + userID;

        String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);

        return result;
    }



}
