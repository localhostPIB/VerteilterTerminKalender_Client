package VerteilterTerminKalender.model.classes;


import VerteilterTerminKalender.model.interfaces.EventDecline;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *  Implementation of event decline interface
 */
@Getter
@Setter
public class EventDeclineImpl implements EventDecline {

    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private int declineId;
    private int userId;
    private int eventId;




}
