package controller;

import java.io.File;
import java.io.IOException;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PrincipalController {
	@FXML
	private MenuItem MIQ;
	
	@FXML
	private Button BQ1;
	
	@FXML
	private Button BS1;
	
	@FXML
	private Button cancelButton;
	
	private Stage stage;
	
	@FXML
	private MediaView ressource;
	@FXML
	private ImageView iconRessource;
	@FXML
	private ScrollBar defilerLecture;
	
	@FXML
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
	
	@FXML
	public void solution (Event event) {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressources/fxml/alerteSolution.fxml"));
		try {
			stage.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.show();
	}
	
	@FXML
	public void cancelsolu (MouseEvent event) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
	}
	
	@FXML
	public void part (MouseEvent event) {
		Platform.exit();
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage primaryStage) {
		stage = primaryStage;
		
	}

	public void init() {
		
		File file = new File(getClass().getResource("/ressources/videos/PostMaloneBetterNow.mp4").toString());
		
		defilerLecture.valueProperty().addListener((observable, oldValue, newValue) -> deplacerCurseur(observable, oldValue, newValue));
		
		MediaPlayer media = new MediaPlayer(new Media(file.toURI().toString()));
		if (file.getName().matches(".*\\.mp4$"))
			iconRessource.visibleProperty().set(false);
		
		media.setOnReady(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				media.play();
				updateCurseur();
				
			}
		});
		
		ressource.setMediaPlayer(media);
		
	}
	
	private void updateCurseur() {

		MediaPlayer player = ressource.getMediaPlayer();
		
		if (player.getStatus() == Status.PLAYING) {
			defilerLecture.setValue(player.getCurrentTime().toSeconds() * player.getTotalDuration().toSeconds() / 100);
		}
		
//		updateCurseur();
		
	}
	
	public void deplacerCurseur (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        
		double durationTotal = ressource.getMediaPlayer().getTotalDuration().toSeconds();
		double curseur = defilerLecture.getValue() * durationTotal / 100;
		
		ressource.getMediaPlayer().seek(Duration.seconds(curseur));
		
    }
	
	public void mute() {
		
		MediaPlayer player = ressource.getMediaPlayer();
		
		player.setVolume(player.getVolume() * -1 + 1);
	}
	
	public void avancer() {
		
		MediaPlayer player = ressource.getMediaPlayer();
		double curseur = player.getCurrentTime().toSeconds() + 5;
		
		player.seek(Duration.seconds(curseur));
	}
	
	public void reculer() {
		
		MediaPlayer player = ressource.getMediaPlayer();
		double curseur = player.getCurrentTime().toSeconds() - 5;
		
		player.seek(Duration.seconds(curseur));
	}
	
}
