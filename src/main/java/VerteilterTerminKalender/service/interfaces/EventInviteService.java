package VerteilterTerminKalender.service.interfaces;

import VerteilterTerminKalender.model.interfaces.EventInvite;
import javafx.collections.ObservableList;

public interface EventInviteService {

    public ObservableList<EventInvite> getAllEventInviteByUserId(String userId);
    public int newEventInvite(EventInvite eventInvite);
    public void acceptInvite(int userId, int eventId, int inviteId);
    public int declineInvite(int userId, int eventId, int inviteId);
    public void sendInviteToUsers(int eventId, int[] userIds);
}
