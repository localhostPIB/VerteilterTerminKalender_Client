package VerteilterTerminKalender.model.interfaces;

import VerteilterTerminKalender.model.classes.EventDeclineImpl;
import VerteilterTerminKalender.model.classes.EventInviteImpl;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = EventDeclineImpl.class)
public interface EventDecline {



    public int getDeclineId();
    public void setDeclineId(int declineId);

    public int getUserId();
    public void setUserId(int userId);

    public int getEventId();
    public void setEventId(int eventId);
}
