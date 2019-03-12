package VerteilterTerminKalender.util;

import VerteilterTerminKalender.model.classes.EventFxImpl;
import VerteilterTerminKalender.model.interfaces.EventFx;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static VerteilterTerminKalender.validators.ObjectValidator.isNotObjectNull;
import static VerteilterTerminKalender.validators.StringValidator.isNotStringEmptyOrNull;


public class FxUtil {

    public static EventFx convertMapToEventFx(Map<String, Object> eventMap){
        EventFx eventFx = new EventFxImpl();

        //EventId
        IntegerProperty eventId = new SimpleIntegerProperty();
        eventId.setValue(Integer.valueOf(eventMap.get("eventId").toString()));
        eventFx.setEventId(eventId);

        //Location
        StringProperty location = new SimpleStringProperty();
        String locationString = eventMap.get("location").toString();
        if(isNotStringEmptyOrNull(locationString)){
            location.setValue(locationString);
        }

        eventFx.setLocation(location);

        //StartTime
        StringProperty startTime = new SimpleStringProperty();
        ArrayList<Integer> startTimeArray = (ArrayList<Integer>) eventMap.get("startTime");
        if(isNotObjectNull(startTimeArray)) {
            String dateTimeString = convertMapWithTimeAndDateToString(startTimeArray);
            startTime.setValue(dateTimeString);
        }
        eventFx.setStarttime(startTime);


        //endTime
        StringProperty endTime = new SimpleStringProperty();
        ArrayList<Integer> endTimeArray = (ArrayList<Integer>) eventMap.get("endTime");
        if(isNotObjectNull(endTime)){
            String endTimeString = convertMapWithTimeAndDateToString(endTimeArray);
            endTime.setValue(endTimeString);
        }

        eventFx.setEndtime(endTime);


        //allDay
        StringProperty allDay = new SimpleStringProperty();

        String allDayString = eventMap.get("allDay").toString();
        if(isNotStringEmptyOrNull(allDayString)){
            allDay.setValue(allDayString);
        }

        eventFx.setAllday(allDay);


        //repeat
        //TODO Serverteam fragen, ob repeat null werden kann
        IntegerProperty repeat = new SimpleIntegerProperty();
        repeat.setValue(Integer.valueOf(eventMap.get("repeat").toString()));
        eventFx.setRepeat(repeat);


        //note
        StringProperty note = new SimpleStringProperty();
        String noteString = eventMap.get("note").toString();
        if(isNotStringEmptyOrNull(noteString)){
            note.setValue(noteString);
        }

        eventFx.setNote(note);

        //duration
        //TODO abklären in welchem Format. Danach implementieren

        return eventFx;
    }


    /**
     * Transfer a Map (from JSON) to a String
     * @param dateTimeArray
     * @return
     */
    public static String convertMapWithTimeAndDateToString(ArrayList<Integer> dateTimeArray){

        StringBuilder builder = new StringBuilder();

        //TODO Eventuell in einer For Schleife machen, wenn Format klar ist
        builder.append(dateTimeArray.get(0).toString());
        builder.append("-");
        builder.append(dateTimeArray.get(1).toString());
        builder.append("-");
        builder.append(dateTimeArray.get(2).toString());
        builder.append("-");
        builder.append(dateTimeArray.get(3).toString());
        builder.append("-");
        builder.append(dateTimeArray.get(4).toString());

       return builder.toString();

    }

}
