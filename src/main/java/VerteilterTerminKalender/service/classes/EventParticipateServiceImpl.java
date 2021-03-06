package VerteilterTerminKalender.service.classes;

import VerteilterTerminKalender.controller.EventParticipateControllerRest;
import VerteilterTerminKalender.model.classes.EventParticipateImpl;
import VerteilterTerminKalender.model.interfaces.EventParticipate;
import VerteilterTerminKalender.model.interfaces.Person;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of EventParticipateService interface

 */
public class EventParticipateServiceImpl implements EventParticipateService{

    EventParticipateControllerRest eventParticipateControllerRest = new EventParticipateControllerRest();
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Get EventParticipate object
     * @param participateID id of a eventParticipate object
     * @return EventParticipate if operation is successfully executed, null if error
     */
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

    /**
     * Get all participant of an event
     * @param eventID if of a event
     * @return ArrayList<Person> if operation is successfully executed, null if error
     */
    @Override
    public ArrayList<Person> getParticipants(int eventID) {
        ArrayList<Person> arrayList = new ArrayList<>();

        String result = eventParticipateControllerRest.getParticipants(eventID);

        try {
            arrayList = objectMapper.readValue(result, new TypeReference<List<Person>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    /**
     * Create a new eventParticipate object
     * @param eventParticipate eventParticipate object
     * @return Response if operation is successfully executed, null if error
     */
    @Override
    public Response newParticipate(EventParticipate eventParticipate) {
        Response s = null;
        try {
            s = eventParticipateControllerRest.newParticipate(objectMapper.writeValueAsString(eventParticipate));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * Deletes a eventParticipate object
     * @param participateID id of eventParticipate object
     * @return String
     */
    @Override
    public String deleteParticipate(int participateID) {
        return eventParticipateControllerRest.deleteParticipate(participateID);
    }


    /**
     * Returns a List of EventParticipate
     * @param userId id of a user
     * @return ArrayList<EventParticipate> if operation is successfully executed, null if error
     */
    @Override
    public ArrayList<EventParticipate> getAllParticipate(String userId) {
        ArrayList<EventParticipate> arrayList = null;
        String result = eventParticipateControllerRest.getAllParticipate(userId);
        try {
            arrayList =  objectMapper.readValue(result, new TypeReference<List<Person>>(){});
            return arrayList;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}
