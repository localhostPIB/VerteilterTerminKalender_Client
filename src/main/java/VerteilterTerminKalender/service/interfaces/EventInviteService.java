package VerteilterTerminKalender.service.interfaces;

import VerteilterTerminKalender.model.interfaces.EventInvite;
import javafx.collections.ObservableList;

public interface EventInviteService {

    public ObservableList<EventInvite> getAllEventInviteByUserId(String userId);
    public int newEventInvite(EventInvite eventInvite);
    public void acceptInvite(EventInvite eventInvite);
    public int declineInvite(EventInvite eventInvite);
    public void sendInviteToUsers(int eventId, int[] userIds);
}
