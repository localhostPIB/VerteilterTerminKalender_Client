package VerteilterTerminKalender.model.interfaces;

import VerteilterTerminKalender.model.classes.EventInviteImpl;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = EventInviteImpl.class)
public interface EventInvite {

    public int getInviteId();
    public void setInviteId(int inviteId);

    //Invited PersonImpl
    public int getUserId();
    public void setUserId(int userId);

    public int getEventId();
    public void setEventId(int eventId);




}
