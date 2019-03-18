package VerteilterTerminKalender.service.classes;

import VerteilterTerminKalender.controller.EventParticipateControllerRest;
import VerteilterTerminKalender.model.classes.EventParticipateImpl;
import VerteilterTerminKalender.model.interfaces.EventParticipate;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;

public class EventParticipateServiceImpl implements EventParticipateService{

    EventParticipateControllerRest eventParticipateControllerRest = new EventParticipateControllerRest();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public EventParticipate getParticipate(int participateID) {
        EventParticipateImpl eventParticipate = null;

        String result = eventParticipateControllerRest.getParticipate(participateID);
        System.out.println(result);

        try {
            eventParticipate = objectMapper.readValue(result, EventParticipateImpl.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eventParticipate;
    }

    @Override
    public ArrayList getParticipants(int eventID) {
        return eventParticipateControllerRest.getParticipants(eventID);
    }

    @Override
    public boolean createParticipate(EventParticipate eventParticipate) {
        return eventParticipateControllerRest.createParticipate(eventParticipate);
    }

    @Override
    public boolean deleteParticipate(int participateID) {
        return eventParticipateControllerRest.deleteParticipate(participateID);
    }

    @Override
    public ObservableList<EventParticipateImpl> getAllParticipate() {
        return eventParticipateControllerRest.getAllParticipate();
    }

}
