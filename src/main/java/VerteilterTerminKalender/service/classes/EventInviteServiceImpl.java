package VerteilterTerminKalender.service.classes;

import VerteilterTerminKalender.controller.EventControllerRest;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import VerteilterTerminKalender.service.interfaces.EventInviteService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class EventInviteServiceImpl implements EventInviteService {

    private EventControllerRest eventControllerRest = new EventControllerRest();
    private ObjectMapper mapper = new ObjectMapper();


    public ObservableList<EventInvite> getAllEventInviteByUserId(String userId) {

        ObservableList<EventInvite> eventInvitesList = null;

        String result = eventControllerRest.getAllEventInviteByUserId(userId);

        try {
            List<EventInvite> eventInviteList = mapper.readValue(result, new TypeReference<List<EventInvite>>() {
            });
            eventInvitesList = FXCollections.observableArrayList(eventInviteList);

            return eventInvitesList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int acceptInvite(int eventId, int[] userIdsOfParticipants){
        return 0;
    }




}
