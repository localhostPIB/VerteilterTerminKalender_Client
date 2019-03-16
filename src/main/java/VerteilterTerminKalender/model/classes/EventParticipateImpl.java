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

    @Override
    public int getParticipateId() {
        return participateId;
    }

    @Override
    public void setParticipateId(int participateId) {
        this.participateId = participateId;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int getEventId() {
        return eventId;
    }

    @Override
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}

