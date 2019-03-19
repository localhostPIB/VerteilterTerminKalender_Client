package VerteilterTerminKalender.service.classes;

import VerteilterTerminKalender.controller.EventParticipateControllerRest;
import VerteilterTerminKalender.model.classes.EventParticipateImpl;
import VerteilterTerminKalender.model.classes.UserImpl;
import VerteilterTerminKalender.model.interfaces.EventParticipate;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.collections.ObservableList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventParticipateServiceImpl implements EventParticipateService{

    EventParticipateControllerRest eventParticipateControllerRest = new EventParticipateControllerRest();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public EventParticipate getParticipate(int participateID) {
        EventParticipateImpl eventParticipate = null;

        String result = eventParticipateControllerRest.getParticipate(participateID);

        try {
            eventParticipate = objectMapper.readValue(result, EventParticipateImpl.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return eventParticipate;
    }

    @Override
    public ArrayList<UserImpl> getParticipants(int eventID) {
        ArrayList<UserImpl> arrayList = new ArrayList<>();

        String result = eventParticipateControllerRest.getParticipants(eventID);

        try {
            arrayList = objectMapper.readValue(result, new TypeReference<List<UserImpl>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    @Override
    public Response newParticipate(EventParticipate eventParticipate) {

        //TOBY
        /*ObjectNode eventParticipateAsJsonObject = objectMapper.createObjectNode();

        //eventId
        eventParticipateAsJsonObject.put("eventId", eventParticipate.getEventId());

        // userId
        eventParticipateAsJsonObject.put("userId", eventParticipate.getUserId());

        // participateId
        eventParticipateAsJsonObject.put("participateId", eventParticipate.getUserId());

        Response response = eventParticipateControllerRest.newParticipate(eventParticipateAsJsonObject.toString());
        return response.getStatus();*/

        //KEVIN
        /*try {
            String jsonInString = objectMapper.writeValueAsString(eventParticipate);

            Response response = eventParticipateControllerRest.newParticipate(jsonInString);

            return response;

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;*/

        Response s = null;

        try {
            //Response r = objectMapper.writeValue(new File("target/eventParticipate.json"), eventParticipate);
            System.out.println(objectMapper.writeValueAsString(eventParticipate));
            s = eventParticipateControllerRest.newParticipate(objectMapper.writeValueAsString(eventParticipate));
            System.out.println(s.getStatus());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public String deleteParticipate(int participateID) {
        return eventParticipateControllerRest.deleteParticipate(participateID);
    }

    // TODO: IMPLEMENT THIS?
    @Override
    public ObservableList<EventParticipateImpl> getAllParticipate() {
        return eventParticipateControllerRest.getAllParticipate();
    }

}
