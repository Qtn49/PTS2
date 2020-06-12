package controller;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Exercice;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PrincipalController {

	private Media media;

	private Exercice exercice;

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
	
	private static Stage stage;

	@FXML
	private TextField proposition;
	
	@FXML
	private MediaView ressource;
	
	@FXML
	private ScrollBar defilerLecture;
	@FXML
	private ToggleButton playButton, pauseButton;

	@FXML
	private TextArea consigne;

	@FXML
	private TabPane sections;

	@FXML
	private ListView<String> aide;

	public Exercice getExercice() {
		return exercice;
	}

	public void setExercice(Exercice exercice) {

		this.exercice = exercice;

		for (int i = 1; i <= exercice.nbSections(); i++) {

			Tab tab = null;
			try {
				tab = FXMLLoader.load(getClass().getResource("/ressources/fxml/tab.fxml"));
				tab.setText(tab.getText() + " " + i);
				VBox vBox = (VBox) tab.getContent();
				((TextArea) vBox.getChildren().get(0)).setText(exercice.getSection(i).getTexteOccultee());
			} catch (IOException e) {
				e.printStackTrace();
			}

			sections.getTabs().add(tab);

		}

		for (String s : exercice.getAide()) {

			aide.getItems().add(s);

		}

		consigne.setText(exercice.getConsigne());

	}

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
	public void solution (ActionEvent event) {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressources/fxml/alerteSolution.fxml"));
		try {
			VBox vBox = loader.load();
			HBox hBox = (HBox) vBox.getChildren().get(1);
			Button button = (Button) hBox.getChildren().get(1);
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					stage.close();
					montrerSolution(null);
				}
			});
			stage.setScene(new Scene(vBox));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.show();
	}

	@FXML
	public void montrerSolution (ActionEvent event) {

		for (int i = 1; i <= sections.getTabs().size(); i++) {
			VBox vBox = (VBox) sections.getTabs().get(i - 1).getContent();

			TextArea area = (TextArea) vBox.getChildren().get(0);

			area.setText(exercice.getSection(i).getSolution());

		}

	}
	
	@FXML

	public void goback(ActionEvent event) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
	}

	public void cancelsolu (MouseEvent event) {

		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
	}
	
	@FXML
	public void part (Event event) {
		Platform.exit();
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage primaryStage) {
		stage = primaryStage;
		
	}
	
	public void ouvrir (ActionEvent event) throws IOException, ClassNotFoundException, URISyntaxException {

		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("exercice (*.exo)", "*.exo"));
		File file = chooser.showOpenDialog(stage);
		ZipFile zipFile = new ZipFile(file);

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressources/fxml/principal.fxml"));
		stage.setScene(new Scene(loader.load()));
		PrincipalController controller = loader.getController();

		controller.ouvrirExo(zipFile);

		controller.init();

	}

	private void ouvrirExo (ZipFile zipFile) throws IOException, ClassNotFoundException, URISyntaxException {

		Enumeration<? extends ZipEntry> entries = zipFile.entries();

		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();

			if (entry.getName().matches(".*\\.o")) {


				InputStream stream = zipFile.getInputStream(entry);
				ObjectInputStream stream1 = new ObjectInputStream(stream);
				Exercice exercice = (Exercice) stream1.readObject();

				setExercice(exercice);



			}else if (entry.getName().matches(".*\\.(mp4|mp3|wav)")) {
//				InputStream stream = zipFile.getInputStream(entry);

//				media = new Media(getClass().getResource("/ressources/videos/betterNow.mp4").toURI().toString());
			}
		}

		zipFile.close();

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

	public void valider (Event event) {

		if (event instanceof KeyEvent) {
			if (((KeyEvent) event).getCode() != KeyCode.ENTER) {
				return;
			}
		}

		System.out.println(proposition.getText());

		proposition.setText("");

	}
	
	public void init() {
		
//		File file = new File("src/ressources/videos/angele.mp4");
		
		defilerLecture.valueProperty().addListener(this::deplacerCurseur);
		
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		
		mediaPlayer.setOnReady(mediaPlayer::play);

		System.out.println(sections.getTabs());
		
		mediaPlayer.currentTimeProperty().addListener(this::updateCurseur);

//		ressource.setFitWidth(((VBox) ressource.getParent()).getWidth());
//		ressource.setFitHeight(((VBox) ressource.getParent()).getHeight());

		ressource.setMediaPlayer(mediaPlayer);

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
        
		int durationTotal = (int) Math.floor(ressource.getMediaPlayer().getTotalDuration().toSeconds());
		int curseur = (int) (defilerLecture.getValue() * durationTotal / 100);
		
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
