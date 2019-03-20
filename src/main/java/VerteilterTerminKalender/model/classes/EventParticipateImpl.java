package VerteilterTerminKalender.model.classes;

import VerteilterTerminKalender.model.interfaces.EventParticipate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventParticipateImpl implements EventParticipate {
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private int participateId;
    private int userId;
    private int eventId;

    public EventParticipateImpl(int participateId, int userId, int eventId){
        this.participateId = participateId;
        this.userId = userId;
        this.eventId = eventId;
    }

    public EventParticipateImpl(int userId, int eventId){
        this(0, userId, eventId);
    }

    public EventParticipateImpl(){

    }

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

    @Override
    public String toString() {
        return "EventParticipateImpl{" +
                "participateId=" + participateId +
                ", userId=" + userId +
                ", eventId=" + eventId +
                '}';
    }
}

