package VerteilterTerminKalender.view;

import VerteilterTerminKalender.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class DayOverviewController {

    private MainApp mainApp;

    @FXML
    private Label labelDayOfMonth;
    @FXML
    private VBox vBoxPreviewEvents;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setup(int dayOfMonth){
        labelDayOfMonth.setText(Integer.toString(dayOfMonth));
        for(int i = 1; i <= 3; i++){
            String previewEventName = "TestEvent" + i;
            Label labelPreviewEvent = new Label(previewEventName);
            BorderPane centerTheLabel = new BorderPane();
            centerTheLabel.setCenter(labelPreviewEvent);
            vBoxPreviewEvents.getChildren().add(centerTheLabel);
        }
    }
}
