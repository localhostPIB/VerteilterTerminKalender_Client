package model.classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;
import model.interfaces.EventFx;

@Getter
@Setter
public class EventFxImpl implements EventFx {

private IntegerProperty eventId;
private StringProperty location;
private StringProperty starttime;
private StringProperty endtime;
private IntegerProperty allday;
private IntegerProperty repeat;
private StringProperty note;
private IntegerProperty userid;





}
