package VerteilterTerminKalender.model.interfaces;

import VerteilterTerminKalender.model.classes.EventDeclineImpl;
import VerteilterTerminKalender.model.classes.EventImpl;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Duration;
import java.time.LocalDateTime;



@JsonDeserialize(as = EventImpl.class)
public interface Event {

    public int getEventId();
    public void setEventId(int eventId);

    public String getLocation();
    public void setLocation(String location);

    public LocalDateTime getStartTime();
    public void setStartTime(LocalDateTime startTime);

    public LocalDateTime getEndTime();
    public void setEndTime(LocalDateTime endTime);

    public boolean isAllDay();
    public void setAllDay(boolean allDay);

    public int getRepeat();
    public void setRepeat(int repeat);

    public String getNote();
    public void setNote(String note);

    public int getUserId();
    public void setUserId(int userId);

    public Duration getDuration();
}
