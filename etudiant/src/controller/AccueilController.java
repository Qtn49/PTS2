package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class AccueilController {
    
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void ouvrir (ActionEvent event) throws IOException, ClassNotFoundException, URISyntaxException {

        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("exercice (*.exo)", "*.exo"));
        File file = chooser.showOpenDialog(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressources/fxml/principal.fxml"));
        stage.setScene(new Scene(loader.load()));

        PrincipalController controller = loader.getController();

        controller.ouvrirExo(file.toURI().toURL());

    }

    public void quitter (Event event) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressources/fxml/quitter.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }
    
}
