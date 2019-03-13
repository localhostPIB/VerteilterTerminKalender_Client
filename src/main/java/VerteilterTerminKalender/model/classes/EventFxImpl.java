package VerteilterTerminKalender.model.classes;

import javafx.beans.property.*;
import lombok.Getter;
import lombok.Setter;
import VerteilterTerminKalender.model.interfaces.EventFx;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventFxImpl implements EventFx {


    public EventFxImpl() {
    }

    public EventFxImpl( String location, String startTime, String endTime, String allDay, int repeat, String note, int userId){
        StringProperty locationProp = new SimpleStringProperty("location");
        StringProperty locationProp1 = new SimpleStringProperty("location");




        IntegerProperty eventId = new SimpleIntegerProperty();
    }


private IntegerProperty eventId;
private StringProperty location;

private ObjectProperty<LocalDateTime> startTime;
private ObjectProperty<LocalDateTime> endTime;
private StringProperty allDay;
private IntegerProperty repeat;
private StringProperty note;
private IntegerProperty userId;
//private StringProperty duration;

//TODO Brauchen wir die UserIds zusätzlich in den Terminen?
//private IntegerProperty userid;





}
