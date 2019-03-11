package VerteilterTerminKalender.model.interfaces;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public interface EventFx {

    public IntegerProperty getEventId();
    public StringProperty getLocation();
    public StringProperty getStarttime();
    public StringProperty getEndtime();
    public IntegerProperty getAllday();
    public IntegerProperty getRepeat();
    public StringProperty getNote();
    public IntegerProperty getUserid();


    public void setEventId(IntegerProperty eventId);
    public void setLocation(StringProperty location);
    public void setStarttime(StringProperty starttime);
    public void setEndtime(StringProperty endtime);
    public void setAllday(IntegerProperty allday);
    public void setRepeat(IntegerProperty repeat);
    public void setNote(StringProperty note);
    public void setUserid(IntegerProperty userid);
}
