package VerteilterTerminKalender.service.interfaces;

import VerteilterTerminKalender.model.classes.EventParticipateImpl;
import VerteilterTerminKalender.model.classes.UserImpl;
import VerteilterTerminKalender.model.interfaces.EventParticipate;
import VerteilterTerminKalender.model.interfaces.Person;
import javafx.collections.ObservableList;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public interface EventParticipateService {
    public EventParticipate getParticipate(int participateID);
    public ArrayList<Person> getParticipants(int eventID);
    public Response newParticipate(EventParticipate eventParticipate);
    public String deleteParticipate(int participateID);
    public ArrayList<EventParticipate> getAllParticipate(String userId);
}
