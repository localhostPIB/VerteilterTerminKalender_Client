package VerteilterTerminKalender.controller;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static VerteilterTerminKalender.constants.Configuration.BASE_URL;

/**
 *  Class fetches and send users to the server via REST API
 */
public class UserControllerRest {

    private final String ENDPOINT_GET_USER_BY_ID ="/user/id/";
    private final String ENDPOINT_GET_USER_BY_EMAIL ="/user/";
    private final String ENDPOINT_POST_USER = "/user/";
    private final String ENDPOINT_FIND_ID_BY_EMAIL ="/user/findid";
    private final String ENDPOINT_GET ="/user/findid";


    private Client client;
    private WebTarget target;



    public UserControllerRest(){
        client = ClientBuilder.newClient();
        //client.target(BASE_URL);
        target = client.target(BASE_URL);

    }


    /**
     * fetch a user by e-mail address
     * @param email address of a user
     * @return user object as json string
     */
    public String getUserByEmail(String email){

        String webContextPath = ENDPOINT_GET_USER_BY_EMAIL +email;

        String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);

        return result;
    }


    /**
     * Create a new user
     * @param userAsJsonString user object as json string
     * @return http response
     */
    public Response createUser (String userAsJsonString){

        Response response = target.path(ENDPOINT_POST_USER).request().post(Entity.entity(userAsJsonString, MediaType.APPLICATION_JSON_TYPE));

        return response;

    }


    /**
     * Get a userid by e-mail address
     * @param email of a user
     * @return the id of user as string
     */
    public String findIdByEmail(String email) {


        String result = target.path(ENDPOINT_FIND_ID_BY_EMAIL).queryParam("email",email).request(MediaType.APPLICATION_JSON).get(String.class);


        return result;
    }

    /**
     * Fetch a user object by userid
     * @param userId id of a user
     * @return the user object as json string
     */
    public String getUserByUserId(String userId){

        String webContextPath = ENDPOINT_GET_USER_BY_EMAIL +userId;

        String result = target.path(webContextPath).request(MediaType.APPLICATION_JSON).get(String.class);

        return result;
    }




}
