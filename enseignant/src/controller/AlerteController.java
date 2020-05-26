package controller;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.stage.Stage;

public class AlerteController {

    public void quitter (Event event) {

        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.close();

    }

}
