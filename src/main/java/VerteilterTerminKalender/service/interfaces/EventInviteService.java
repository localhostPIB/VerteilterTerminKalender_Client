package VerteilterTerminKalender.service.interfaces;

import VerteilterTerminKalender.model.interfaces.EventInvite;
import javafx.collections.ObservableList;

public interface EventInviteService {

    public ObservableList<EventInvite> getAllEventInviteByUserId(String userId);
    public int acceptInvite(int eventId, int[] userIdsOfParticipants);
}
