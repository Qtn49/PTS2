package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class PrincipalController {

    private File exo;
    private Stage stage;

    @FXML
    private MediaView ressource;

    public void importer (ActionEvent event) {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choisir une ressource");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Ressource audio ou vidéo (*.mp4, *.mp3, *.wav)", "*.mp3", "*.mp4", "*.wav"));
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            setMedia(file);
        }else {
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ressources/fxml/noRessource.fxml"));
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setAlwaysOnTop(true);

                stage.setScene(new Scene(loader.load()));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public File getExo() {
        return exo;
    }

    public void setExo(File exo) {
        this.exo = exo;
    }

    public void setMedia (File file) {
        MediaPlayer player = new MediaPlayer(new Media(file.toURI().toString()));
        player.setAutoPlay(true);
        player.setOnError(()->
                System.out.println("media error"+player.getError().toString()));
        ressource.setMediaPlayer(player);


    }

}
