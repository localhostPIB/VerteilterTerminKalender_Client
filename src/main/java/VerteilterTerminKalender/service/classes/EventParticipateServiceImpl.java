package VerteilterTerminKalender.service.classes;

import VerteilterTerminKalender.model.interfaces.EventParticipate;
import VerteilterTerminKalender.service.interfaces.EventParticipateService;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class EventParticipateServiceImpl implements EventParticipateService{
    @Override
    public EventParticipate getParticipate(int participateID) {
        return null;
    }

    @Override
    public ArrayList getParticipants(int eventID) {
        return null;
    }

    @Override
    public boolean createParticipate(EventParticipate eventParticipate) {
        return false;
    }

    @Override
    public boolean deleteParticipate(int participateID) {
        return false;
    }

    @Override
    public ObservableList<EventParticipate> getAllParticipate() {
        return null;
    }


}
