package VerteilterTerminKalender.model.classes;

import VerteilterTerminKalender.model.interfaces.EventInvite;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EventInviteImpl implements EventInvite {

    private int inviteId;
    private int userId;
    private int eventId;
}
