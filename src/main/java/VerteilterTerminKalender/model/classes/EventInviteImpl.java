package VerteilterTerminKalender.model.classes;

import VerteilterTerminKalender.model.interfaces.EventInvite;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

public class EventInviteImpl implements EventInvite {

    private int inviteId;
    private int userId;
    private int eventId;
}
