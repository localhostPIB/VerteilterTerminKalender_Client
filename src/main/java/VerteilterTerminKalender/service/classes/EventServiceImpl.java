package VerteilterTerminKalender.service.classes;

import VerteilterTerminKalender.controller.EventControllerRest;
import VerteilterTerminKalender.model.classes.EventFxImpl;
import VerteilterTerminKalender.model.interfaces.EventFx;
import VerteilterTerminKalender.service.interfaces.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static VerteilterTerminKalender.util.FxUtil.convertMapToEventFx;

public class EventServiceImpl implements EventService {

    EventControllerRest eventControllerRest = new EventControllerRest();
    ObjectMapper mapper = new ObjectMapper();

    public ArrayList<EventFx> getAllEvents(String userid) {
        ArrayList<EventFx> eventFxArrayList = new ArrayList<EventFx>();
        List<Map<String, Object>> dataAsMap = null;

        String result = eventControllerRest.getEventByUserId(userid);
        Map<String, Object> map = new HashMap<String, Object>();

        try {
             dataAsMap = mapper.readValue(result, List.class);


            Iterator<Map<String, Object>> iterator = dataAsMap.listIterator();
            while (iterator.hasNext()){
                Map<String, Object> eventMap = iterator.next();

                EventFx eventFx = convertMapToEventFx(eventMap);
                eventFxArrayList.add(eventFx);

            }


            // map = mapper.readValue(result, new TypeReference<Map<String, String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }



        return eventFxArrayList;
    }

    public void newEvent(EventFx event){
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode objectNode1 = mapper.createObjectNode();

        objectNode1.put("location", event.getLocation().getValue());


        ArrayNode startTime = objectNode1.putArray("startTime");
        LocalDateTime startTimeDate = event.getStartTime().getValue();
        startTime.add()



        //objectNode1.put(startTime);




        System.out.println(objectNode1.toString());

        System.out.println();



    }
}
