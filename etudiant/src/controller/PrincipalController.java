package controller;

import java.io.*;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
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

	@FXML
	private Button backButton;
	
	private Stage stage;
	
	@FXML
	private MediaView ressource;
	
	@FXML
	private ScrollBar defilerLecture;
	@FXML
	private ToggleButton playButton, pauseButton;
	
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

	public void goback(ActionEvent event) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
	}

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
	
	public void ouvrir (ActionEvent event) throws IOException {

		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("exercice (*.exo)", "*.exo"));
		File file = chooser.showOpenDialog(stage);
		ZipFile zipFile = new ZipFile(file);

		Enumeration<? extends ZipEntry> entries = zipFile.entries();

		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			InputStream stream = zipFile.getInputStream(entry);
			Scanner scanner = new Scanner(stream);

			while (scanner.hasNext()) {
				System.out.println(scanner.nextLine());
			}
		}

	}

	@FXML
	public void play (ActionEvent event) {
	
		if (ressource.getMediaPlayer().getStatus() == Status.PLAYING) {
			playButton.setSelected(true);
			return;
		}
		
		pauseButton.setSelected(false);
		ressource.getMediaPlayer().play();
		
	}
	
	public void init() {
		
		File file = new File("src/ressources/videos/angele.mp4");
		
		defilerLecture.valueProperty().addListener((observable, oldValue, newValue) -> deplacerCurseur(observable, oldValue, newValue));
		
		MediaPlayer media = new MediaPlayer(new Media(file.toURI().toString()));
		
		media.currentTimeProperty().addListener((observable, oldValue, newValue) -> updateCurseur(observable, oldValue, newValue));
		
		ressource.setFitWidth(((VBox) ressource.getParent()).getWidth());
		ressource.setFitHeight(((VBox) ressource.getParent()).getHeight());
		
		ressource.setMediaPlayer(media);
		
	}

	@FXML
	private void pause (ActionEvent event) {
		MediaPlayer player = ressource.getMediaPlayer();
		
		if (player.getStatus() == Status.PAUSED) {
			playButton.setSelected(true);
			return;
		}
		
		playButton.setSelected(false);
		
		player.pause();
	}
	
	private void updateCurseur(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

		MediaPlayer player = ressource.getMediaPlayer();
		
		int curseur = (int) (Math.floor(newValue.toSeconds()) * 100 / Math.floor(player.getTotalDuration().toSeconds()));
		
		defilerLecture.setValue(curseur);
		
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
	
	@FXML
	public void tutoriel() {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressources/fxml/tutoriel.fxml"));
		try {
			stage.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.show();
	}
	
}
