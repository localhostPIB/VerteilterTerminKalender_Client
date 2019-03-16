package VerteilterTerminKalender.model.classes;


import VerteilterTerminKalender.model.interfaces.EventDecline;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDeclineImpl implements EventDecline {


    private int declineId;
    private int userId;
    private int eventId;




}
