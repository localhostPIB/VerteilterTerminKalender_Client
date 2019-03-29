package VerteilterTerminKalender.sse;

import VerteilterTerminKalender.MainApp;
import VerteilterTerminKalender.model.interfaces.EventInvite;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.InboundSseEvent;
import javax.ws.rs.sse.SseEventSource;
import java.util.List;
import java.util.function.Consumer;

import static VerteilterTerminKalender.constants.Configuration.BASE_URL;
import static VerteilterTerminKalender.util.FxUtil.convertJsonStringToEventInviteListe;

/**
 * Client side example for consuming SSE
 */
public class SSEClient
{

	/**
	 * Start a SSE client
	 * @param userId id of a user
	 * @param lastinviteid id of the EventInvite which was last which was last received
	 */
	public static void sseCient(int userId, int lastinviteid) throws Exception {


		String url = BASE_URL +"/sse/invitation/" + userId + "?lastinviteid=" + lastinviteid;

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		SseEventSource eventSource = SseEventSource.target(target).build();

			eventSource.register(onEvent, onError, onComplete);
			eventSource.open();


	}


	/**
	 * A new event is received
	 */
	private static Consumer<InboundSseEvent> onEvent = (inboundSseEvent) -> {
		String data = inboundSseEvent.readData();


		System.out.println(data);
		MainApp mainApp = MainApp.getMainApp();
		ObservableList<EventInvite> eventInvitesList = mainApp.getEventInvitesList();

		List<EventInvite>  eventInviteListFetchFROMServer = convertJsonStringToEventInviteListe(data);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				for(EventInvite eventInvite : eventInviteListFetchFROMServer){
					eventInvitesList.add(eventInvite);
				}
			}
		});


	};


	/**
	 * Gets execute in case of failure
	 */
	private static Consumer<Throwable> onError = Throwable::printStackTrace;


	/**
	 * Closes connection
	 */
	private static Runnable onComplete = () -> {

	};

}
