package VerteilterTerminKalender.service.classes;

import VerteilterTerminKalender.controller.EventControllerRest;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.service.interfaces.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static VerteilterTerminKalender.util.FxUtil.convertMapToEventFx;

/**
 * Implementation of EventService interface
 */
public class EventServiceImpl implements EventService {

   private EventControllerRest eventControllerRest = new EventControllerRest();
    private ObjectMapper mapper = new ObjectMapper();


    /**
     * Get a ObservableList<EventFx> for an user
     * @param userid id of an user
     * @return ObservableList<EventFx> if operation is successfully executed, null if error
     */
    @Override
    public  ObservableList<EventFx> getAllEvents(String userid) {
       // ArrayList<EventFx> eventFxArrayList = new ArrayList<EventFx>();
        ObservableList<EventFx> eventInvitesList = FXCollections.observableArrayList();
        List<Map<String, Object>> dataAsMap = null;

        String result = eventControllerRest.getEventByUserId(userid);
        Map<String, Object> map = new HashMap<String, Object>();

        try {
             dataAsMap = mapper.readValue(result, List.class);


            Iterator<Map<String, Object>> iterator = dataAsMap.listIterator();
            while (iterator.hasNext()){
                Map<String, Object> eventMap = iterator.next();

                EventFx eventFx = convertMapToEventFx(eventMap);
               // eventFxArrayList.add(eventFx);
                eventInvitesList.add(eventFx);

            }


            // map = mapper.readValue(result, new TypeReference<Map<String, String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return eventInvitesList;
    }


    /**
     * Get a event
     * @param userId id of an user
     * @return Reponse
     */
    @Override
    public String getEventByUserId(String userId){
        String response = eventControllerRest.getEventByUserId(userId);
        return  response;
    }

    /**
     * Deletes an event
     * @param eventId if of an event
     * @return String result
     */
    @Override
    public String deleteEventFx(int eventId) {
        String result = eventControllerRest.deleteEventByEventId(eventId);
        return result;

    }

    /**
     * Add a new event
     * @param event Object
     * @return int http status
     */
    @Override
    public int newEvent(EventFx event){
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode eventAsJsonObject = mapper.createObjectNode();

        //location
        eventAsJsonObject.put("location", event.getLocation().getValue());

        //startTime
        ArrayNode startTime = eventAsJsonObject.putArray("startTime");
        LocalDateTime startTimeDate = event.getStartTime().getValue();
        startTime.add(startTimeDate.getYear());
        startTime.add(startTimeDate.getMonthValue());
        startTime.add(startTimeDate.getDayOfMonth());
        startTime.add(startTimeDate.getHour());
        startTime.add(startTimeDate.getMinute());

        //endTime
        ArrayNode endTime = eventAsJsonObject.putArray("endTime");
        LocalDateTime endTimeDate = event.getEndTime().getValue();
        endTime.add(endTimeDate.getYear());
        endTime.add(endTimeDate.getMonthValue());
        endTime.add(endTimeDate.getDayOfMonth());
        endTime.add(endTimeDate.getHour());
        endTime.add(endTimeDate.getMinute());

        //allDay
        eventAsJsonObject.put("allDay", event.getAllDay().getValue());

        //repeat
        eventAsJsonObject.put("repeat", event.getRepeat().getValue());

        //note
        eventAsJsonObject.put("note", event.getNote().getValue());

        //userId
        eventAsJsonObject.put("userId", event.getUserId().getValue());


        Response response = eventControllerRest.newEvent(eventAsJsonObject.toString());
            return response.getStatus();
    }


    /**
     * Change an event object
     * @param event object
     * @return http status code
     */
    @Override
    public int modifyEventFx(EventFx event) {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode eventAsJsonObject = mapper.createObjectNode();

        //userId

        eventAsJsonObject.put("eventId",event.getEventId().getValue());

        //location
        eventAsJsonObject.put("location", event.getLocation().getValue());

        //startTime
        ArrayNode startTime = eventAsJsonObject.putArray("startTime");
        LocalDateTime startTimeDate = event.getStartTime().getValue();
        startTime.add(startTimeDate.getYear());
        startTime.add(startTimeDate.getMonthValue());
        startTime.add(startTimeDate.getDayOfMonth());
        startTime.add(startTimeDate.getHour());
        startTime.add(startTimeDate.getMinute());

        //endTime
        ArrayNode endTime = eventAsJsonObject.putArray("endTime");
        LocalDateTime endTimeDate = event.getEndTime().getValue();
        endTime.add(endTimeDate.getYear());
        endTime.add(endTimeDate.getMonthValue());
        endTime.add(endTimeDate.getDayOfMonth());
        endTime.add(endTimeDate.getHour());
        endTime.add(endTimeDate.getMinute());

        //allDay
        eventAsJsonObject.put("allDay", event.getAllDay().getValue());

        //repeat
        eventAsJsonObject.put("repeat", event.getRepeat().getValue());

        //note
        eventAsJsonObject.put("note", event.getNote().getValue());

        //userId
        eventAsJsonObject.put("userId", event.getUserId().getValue());



        Response response = eventControllerRest.modifyEvent(eventAsJsonObject.toString());
        return response.getStatus();
    }

    /**
     * Get an event by event id
     * @param eventId id of event
     * @return EventFx if operation is successfully executed, null if error
     */
    @Override
    public EventFx getEventByEventId(int eventId) {
        List<Map<String, Object>> dataAsMap = null;

        String result = eventControllerRest.getEventByEventId(eventId);
        String resultNEW = "[" + result + "]";

        Map<String, Object> map = new HashMap<String, Object>();

        try {
            dataAsMap = mapper.readValue(resultNEW, List.class);
            EventFx eventFx = convertMapToEventFx(dataAsMap.get(0));

            return eventFx;
        } catch (IOException e) {
            e.printStackTrace();
        }



        return null;


    }


}
