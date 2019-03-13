package VerteilterTerminKalender.model.classes;

import javafx.beans.property.*;
import lombok.Getter;
import lombok.Setter;
import VerteilterTerminKalender.model.interfaces.EventFx;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventFxImpl implements EventFx {

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

    public EventFxImpl() {
    }

    public EventFxImpl( String location, LocalDateTime startTime, LocalDateTime endTime, String allDay, int repeat, String note, int userId){
        this.startTime = new SimpleObjectProperty();
        this.endTime = new SimpleObjectProperty();

        this.location = new SimpleStringProperty(location);
        this.startTime.set(startTime);
        this.endTime.set(endTime);
        this.allDay = new SimpleStringProperty(allDay);
        this.repeat = new SimpleIntegerProperty(repeat);
        this.note = new SimpleStringProperty(note);
        this.userId = new SimpleIntegerProperty(userId);
    }











}
