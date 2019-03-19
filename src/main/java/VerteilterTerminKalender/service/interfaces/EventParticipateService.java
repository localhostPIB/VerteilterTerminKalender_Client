package VerteilterTerminKalender.service.interfaces;

import VerteilterTerminKalender.model.classes.EventParticipateImpl;
import VerteilterTerminKalender.model.classes.UserImpl;
import VerteilterTerminKalender.model.interfaces.EventParticipate;
import javafx.collections.ObservableList;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

public interface EventParticipateService {
    public EventParticipate getParticipate(int participateID);
    public ArrayList<UserImpl> getParticipants(int eventID);
    public Response newParticipate(EventParticipate eventParticipate);
    public String deleteParticipate(int participateID);
    public ObservableList<EventParticipateImpl> getAllParticipate();
}
