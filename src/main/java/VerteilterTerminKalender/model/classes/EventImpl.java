package VerteilterTerminKalender.model.classes;

import VerteilterTerminKalender.model.interfaces.Event;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Wrapper-Class for EventFx-Objects
 * Used to transfer the json string to a object via jackson

 */

@Getter
@Setter
public class EventImpl implements Event {
    private int eventId;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean allDay;
    private int repeat;
    private String note;
    private int userId;

    @Override
    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }
}
