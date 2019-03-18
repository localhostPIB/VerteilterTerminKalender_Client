package VerteilterTerminKalender.service.classes;

import VerteilterTerminKalender.controller.EventParticipateControllerRest;
import VerteilterTerminKalender.model.classes.EventParticipateImpl;
import VerteilterTerminKalender.model.interfaces.EventParticipate;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class EventParticipateServiceImpl implements EventParticipateService{

    EventParticipateControllerRest eventParticipateControllerRest = new EventParticipateControllerRest();

    @Override
    public EventParticipate getParticipate(int participateID) {
        return eventParticipateControllerRest.getParticipate(participateID);
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
