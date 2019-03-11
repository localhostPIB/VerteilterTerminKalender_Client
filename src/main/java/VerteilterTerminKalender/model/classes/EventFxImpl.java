package VerteilterTerminKalender.model.classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;
import VerteilterTerminKalender.model.interfaces.EventFx;

@Getter
@Setter
public class EventFxImpl implements EventFx {

private IntegerProperty eventId;
private StringProperty location;
private StringProperty starttime;
private StringProperty endtime;
private StringProperty allday;
private IntegerProperty repeat;
private StringProperty note;
//private StringProperty duration;

//TODO Brauchen wir die UserIds zusätzlich in den Terminen?
//private IntegerProperty userid;





}
