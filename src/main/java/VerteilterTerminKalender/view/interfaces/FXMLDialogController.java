package VerteilterTerminKalender.view.interfaces;

import javafx.stage.Stage;

/**
 * Used for creating new Stages that have their own scene (JavaFX)
 * Necessary for opening new windows inside the GUI
 * @author Michelle Blau
 */

public interface FXMLDialogController extends FXMLController{
    public void setDialogStage(Stage dialogStage);
}
