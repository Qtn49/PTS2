package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
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


	@FXML
	private MenuItem MQ1;
	
	@FXML
	private MenuItem MQ2;

	@FXML
	private Button BQ1;
	
	@FXML
	private Button backButton2;
	
	@FXML
	private Button BA1;
	
	@FXML
	private TextField TF1;
	
	@FXML
	private TextArea TA1;
	
	private String aidecomp ="";

    @FXML
    private Button supprimer;

    @FXML
    private MenuItem MIQ;

    

    public void ouvrir(ActionEvent event) {

        MenuItem item = (MenuItem) event.getSource();
        PrincipalController controller = null;
        FXMLLoader loader;
        Scene scene = null;
        boolean valide = true;

        try {

            loader = new FXMLLoader(getClass().getResource("/ressources/fxml/principal.fxml"));
            scene = new Scene(loader.load());
            controller = loader.getController();
            controller.setStage(stage);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (item.getText().equalsIgnoreCase("ouvrir")) {


            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Ouvrir un exercice");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Exercices (*.exo)", "*.exo"));
            File file = fileChooser.showOpenDialog(stage);
            assert controller != null;
            controller.setExo(file);
            System.out.println(file);

            valide = file != null;
        }

        if (valide) {

            stage.setScene(scene);
            stage.setTitle("Création d'un nouvel exercice");

        }

    }

    public void dragOver (DragEvent event) {

        Dragboard dragboard = event.getDragboard();


        if (dragboard.hasFiles())
            event.acceptTransferModes(TransferMode.COPY);
        else
            event.consume();

    }

    public void importer (Event event) {

        if (event.getEventType() == DragEvent.DRAG_DROPPED) {
            Dragboard dragboard = ((DragEvent) event).getDragboard();
            if (dragboard.hasFiles()) {

                File file = dragboard.getFiles().get(0);

                if (file.isFile() && file.getName().matches("^.*\\.(mp4|mp3|wav|mkv)$"))
                    setMedia(file);

            }

        }else {


            FileChooser chooser = new FileChooser();
            chooser.setTitle("Choisir une ressource");
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Ressource audio ou vidéo (*.mp4, *.mp3, *.wav)", "*.mp3", "*.mp4", "*.wav"));
            File file = chooser.showOpenDialog(stage);
            if (file != null) {
                setMedia(file);
            }else {
                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressources/fxml/noRessource.fxml"));
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.setAlwaysOnTop(true);

                    stage.focusedProperty().addListener((ov, onHidden, onShow) -> {
                        if (!stage.isFocused())
                            stage.close();
                    });

                    stage.setScene(new Scene(loader.load()));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

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
//        player.setAutoPlay(true);
        player.setOnError(()->
                System.out.println("media error"+player.getError().toString()));
        ressource.setMediaPlayer(player);

        supprimer.setDisable(false);

    }

	
	
	
	@FXML
	public void supprimer (Event event) {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../ressources/fxml/alerteSuppressionExo.fxml"));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
		try {
			stage.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.show();
	}
	
	@FXML
	public void quitter() {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressources/fxml/quitter.fxml"));
		try {
			stage.setScene(new Scene(loader.load()));
			stage.initModality(Modality.APPLICATION_MODAL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.show();
	}
	
	@FXML
	public void Display (ActionEvent event) {
		String aide = TF1.getText();
		aidecomp = aidecomp + "\n" + TF1.getText();
		TA1.setText(aidecomp);
		
		
	}

	@FXML
	public void part() {
		Platform.exit();
	}
	
	@FXML
	public void goback(ActionEvent event) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
	}
	
	@FXML
	public void afficherTemps() {
		Spinner aSpinner = (Spinner) event.getSource();
		for(Node aNode : aSpinner.getParent().getChildrenUnmodifiable()) {
			temps+=aSpinner.getValue();
		}
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

    public void supprimer() {

    	ressource.getMediaPlayer().stop();
        ressource.setMediaPlayer(null);
        supprimer.setDisable(true);

    }
}

