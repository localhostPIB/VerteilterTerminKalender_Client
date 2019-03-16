package VerteilterTerminKalender.model.classes;

import VerteilterTerminKalender.model.interfaces.EventParticipate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventParticipateImpl implements EventParticipate {


    private int participateId;
    private int userId;
    private int eventId;
}

