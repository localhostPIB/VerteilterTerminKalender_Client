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

        String webContextPath = ENDPOINT_GET_USER_BY_ID + "/event" + userid;

        String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);

        return result;
    }


    public void createUser (String userAsJsonString, User user){
        String webContextPath = ENDPOINT_POST_USER;

        WebTarget employeeWebTarget
                = target.path("/user/add");
        target.path(webContextPath);
        Invocation.Builder invocationBuilder = employeeWebTarget.request(MediaType.APPLICATION_JSON);

        Response response1 = target.path("/user/add").request().post(Entity.entity(userAsJsonString, MediaType.APPLICATION_JSON_TYPE));

        Response response = invocationBuilder.post(Entity.entity(userAsJsonString, MediaType.APPLICATION_JSON));
        System.out.println(response);

//WebTarget webTarget = client.target("http://localhost:8080/JerseyDemos/rest").path("employees").path("1");

    }



}
