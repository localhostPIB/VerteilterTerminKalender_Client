package VerteilterTerminKalender.model.interfaces;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

public interface EventFx {

    public IntegerProperty getEventId();
    public StringProperty getLocation();
    public ObjectProperty<LocalDateTime> getStartTime();
    public ObjectProperty<LocalDateTime> getEndTime();
    public StringProperty getAllDay();
    public IntegerProperty getRepeat();
    public StringProperty getNote();
    public IntegerProperty getUserId();
    //public StringProperty getDuration();


    public void setEventId(IntegerProperty eventId);
    public void setLocation(StringProperty location);
    public void setStartTime(ObjectProperty<LocalDateTime> startTime);
    public void setEndTime(ObjectProperty<LocalDateTime> endTime);
    public void setAllDay(StringProperty allDay);
    public void setRepeat(IntegerProperty repeat);
    public void setNote(StringProperty note);
    public void setUserId(IntegerProperty userid);
    //public StringProperty setDuration();

}
