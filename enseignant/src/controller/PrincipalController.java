package controller;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Exercice;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class PrincipalController {

    private File exo;
    private Stage stage;
    private File ressourceFile;
    private Exercice exercice;

    @FXML
    private MediaView ressource;


	@FXML
	private MenuItem MQ1;
	
	@FXML
	private MenuItem MQ2;

	@FXML
	private TabPane sections;

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

    @FXML
    private ListView<String> aideMots;

    @FXML
    private TextField motEntre;

    @FXML
    private Tab ajoute;

    @FXML
    private VBox zoneTemps;

    @FXML
    private CheckBox limiteTemps;

    @FXML
    private Label labelRessource;

    @FXML
    private VBox infosRessource;

    @FXML
    private Text temps;

    @FXML
    private TextArea consigne;

    @FXML
    private CheckBox motIncomplet, solution, casse;

    @FXML
    private RadioButton modeEval;
    private boolean ready;

    public void init () {
//        DoubleProperty width = ressource.fitWidthProperty();
//        DoubleProperty height = ressource.fitHeightProperty();
//        width.bind(Bindings.selectDouble(ressource.sceneProperty(), "width"));
//        height.bind(Bindings.selectDouble(ressource.sceneProperty(), "height"));

        Spinner spinner = (Spinner) ((HBox) zoneTemps.getChildren().get(0)).getChildren().get(1);
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));

        ressource.setPreserveRatio(true);

    }

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
            controller.init();

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

                ressourceFile = dragboard.getFiles().get(0);

                if (ressourceFile.isFile() && ressourceFile.getName().matches("^.*\\.(mp4|mp3|wav|mkv)$"))
                    setMedia(ressourceFile);

            }

        }else {


            FileChooser chooser = new FileChooser();
            chooser.setTitle("Choisir une ressource");
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Ressource audio ou vidéo (*.mp4, *.mp3, *.wav)", "*.mp3", "*.mp4", "*.wav"));
            ressourceFile = chooser.showOpenDialog(stage);
            if (ressourceFile != null) {
                setMedia(ressourceFile);
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
        Media media = new Media(file.toURI().toString());
        MediaPlayer player = new MediaPlayer(media);

        player.setOnReady(() -> {
            int minutes = (int) (player.getMedia().getDuration().toSeconds() / 60), secondes = (int) (player.getMedia().getDuration().toSeconds() % 60);

            temps.setText(temps.getText().replaceAll("\\d{1,2}:\\d{1,2}$", String.format("%02d", minutes) + ":" + String.format("%02d", secondes)));

            ready = true;

        });

        player.currentTimeProperty().addListener(this::updateTime);

        player.setCycleCount(MediaPlayer.INDEFINITE);

        player.setOnError(()-> System.out.println("media error"+player.getError().toString()));
        ressource.setMediaPlayer(player);

        infosRessource.setVisible(true);

        labelRessource.setVisible(false);

        supprimer.setDisable(false);

    }

    private void updateTime(Observable observable, Duration oldValue, Duration newValue) {

        MediaPlayer player = ressource.getMediaPlayer();

        int minutes = (int) (newValue.toSeconds() / 60), secondes = (int) (newValue.toSeconds() % 60);

        temps.setText(temps.getText().replaceAll("^\\d{1,2}:\\d{1,2}", String.format("%02d", minutes) + ":" + String.format("%02d", secondes)));

    }

    private boolean checkExo () {

        if (ressource.getMediaPlayer() == null)
            return false;

        for (Tab tab : sections.getTabs()) {
            if (tab.getText().equals("+"))
                continue;
            TextArea area = (TextArea) ((VBox) tab.getContent()).getChildren().get(0);
            if (area.getText().equals(""))
                return false;
        }

        return true;

    }

    public void sauvegarder () throws IOException {

        if (!checkExo()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Il manque des informations");
            alert.show();
            return;
        }

        exercice = creerExercice();

        serialize(exercice);

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Sauvegarder l'exercice");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Exo files (*.exo)", "*.exo"));
        File file = chooser.showSaveDialog(stage);

        if (file == null)
            return;

        String nomExo = file.getName();

//        if (!nomExo.endsWith(".exo")) {
//            nomExo = nomExo.replaceAll("(\\..*)?$", ".exo");
//        }

        createZip(nomExo);

        new File("exo.o").delete();

    }

    private void createZip(String nomExo) {

        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(nomExo));
            FileInputStream fileInputStream = new FileInputStream(ressourceFile);
            ZipEntry ressource = new ZipEntry(ressourceFile.getName());

            zipOutputStream.putNextEntry(ressource);

            byte[] bytes = new byte[1024];
            int length;

            while ((length = fileInputStream.read(bytes)) >= 0) {
                zipOutputStream.write(bytes, 0, length);
            }

            fileInputStream = new FileInputStream("exo.o");
            ZipEntry exo = new ZipEntry("exo.o");

            zipOutputStream.putNextEntry(exo);

            while ((length = fileInputStream.read(bytes)) >= 0) {
                zipOutputStream.write(bytes, 0, length);
            }

            zipOutputStream.close();
            fileInputStream.close();

        }catch (Exception ignored) {

        }

    }

    private void serialize(Exercice exercice) {

        try {

            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("exo.o"));
            outputStream.writeObject(exercice);
            outputStream.close();

        }catch (Exception ignored) {

        }

    }

    private Exercice creerExercice () {

        Exercice exercice = new Exercice(consigne.getText(), modeEval.isSelected(), limiteTemps.isSelected());

        for (int i = 0; i < 1; i++) {

            System.out.println(sections.getTabs().get(i).getText());

            if (sections.getTabs().get(i).getText().equals("+"))
                continue;

            int temps = (int) ((Spinner) ((HBox) zoneTemps.getChildren().get(i)).getChildren().get(1)).getValue();
            String texte = ((TextArea) ((VBox) sections.getTabs().get(i).getContent()).getChildren().get(0)).getText();

            System.out.println(temps);
            System.out.println(texte);

            exercice.ajouteSection(temps, texte);

        }

        if (!modeEval.isSelected()) {
            exercice.setMotIncomplet(motIncomplet.isSelected());
            exercice.setSensibiliteCasse(casse.isSelected());
            exercice.setAffichageSolution(solution.isSelected());
        }

        for (String texte : aideMots.getItems()) {
            exercice.ajouteAide(texte);
        }

        System.out.println(exercice);

        return exercice;
    }

    public void supprimer () {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressources/fxml/alerteSuppressionRessource.fxml"));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);

		try {
			stage.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        ((Button) loader.getNamespace().get("confirmer")).setOnAction(event -> {
            stage.close();
            supprimerRessource();
        });

        ((Button) loader.getNamespace().get("annuler")).setOnAction(event -> {
            stage.close();
        });

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
	public void part() {
		Platform.exit();
	}
	
	@FXML
	public void goBack(ActionEvent event) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
	}
	
	@FXML
	public void afficherTemps(Event event) {
        int temps = 0;
		Spinner aSpinner = (Spinner) event.getSource();
		for(Node aNode : aSpinner.getParent().getChildrenUnmodifiable()) {
			temps += Integer.parseInt(aSpinner.getValue().toString());
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

    public void supprimerRessource () {

    	ressource.getMediaPlayer().stop();
        ressource.setMediaPlayer(null);
        supprimer.setDisable(true);

        infosRessource.setVisible(false);

        labelRessource.setVisible(true);

    }

    public void ajouteSection (Event event) {

        TabPane tabs = ((Tab) event.getSource()).getTabPane();

        System.out.println(zoneTemps);
        int nbSection = tabs.getTabs().size();

        if (tabs.getSelectionModel().getSelectedItem().getText().equals("+")) {

            Tab tab = null;
            try {
                tab = FXMLLoader.load(getClass().getResource("/ressources/fxml/tab.fxml"));
                tab.setText("section " + nbSection);
                tab.setOnCloseRequest(this::fermer);
            } catch (IOException e) {
                e.printStackTrace();
            }



            tabs.getTabs().add(nbSection - 1, tab);
            tabs.getSelectionModel().select(tab);

            HBox newTemps = null;
            try {
                newTemps = FXMLLoader.load(getClass().getResource("/ressources/fxml/tempsSection.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Spinner spinner = (Spinner) newTemps.getChildren().get(1);
            spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0));

            Label label = (Label) newTemps.getChildren().get(0);
            label.setText("Section " + nbSection + " :");

            zoneTemps.getChildren().add(nbSection - 1, newTemps);

            tabs.getSelectionModel().select(nbSection - 1);

            if (nbSection >= 4) {
                tabs.getTabs().remove(tabs.getTabs().remove(tabs.getTabs().size() - 1));
            }

        }

    }

    public void fermer(Event event) {

        TabPane tabs = ((Tab) event.getSource()).getTabPane();

        System.out.println(tabs.getTabs().size());

        if (!tabs.getTabs().get(tabs.getTabs().size() - 1).getText().equals("+")) {
            Tab tab = new Tab("+");
            tab.setOnSelectionChanged(this::ajouteSection);
            tabs.getTabs().add(tab);
        }

        for (Tab tab : tabs.getTabs()) {

            if (tab.getText().equals("+"))
                continue;

            int pos = tabs.getTabs().indexOf(tab);

            tab.setText("section " + (pos + 1));

        }

        System.out.println(zoneTemps);

        zoneTemps.getChildren().remove(0);

        for (Node node : zoneTemps.getChildren()) {

            if (node instanceof HBox) {

                for (Node node1 : ((HBox) node).getChildren()) {

                    if (node1 instanceof Label)
                        ((Label) node1).setText(((Label) node1).getText().replaceFirst("[0-9]+", String.valueOf(zoneTemps.getChildren().indexOf(node) + 1)));

                }

            }
        }

    }

    public void option (ActionEvent event) {

        CheckBox checkBox = (CheckBox) event.getSource();

        if (checkBox == limiteTemps) {

            zoneTemps.setDisable(!zoneTemps.isDisabled());

        }
    }

    public void ajouterMot (Event event) {

        if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            if (((KeyEvent) event).getCode() != KeyCode.ENTER || motEntre.getText().equals(""))
                return;
        }

        if (!motEntre.getText().equals("")) {

            aideMots.getItems().add(motEntre.getText());
            motEntre.setText("");

        }

    }

    public void retirerMot (KeyEvent event) {

        if (event.getCode() == KeyCode.DELETE)
            aideMots.getItems().remove(aideMots.getSelectionModel().getSelectedItem());

    }

    public void playPause(ActionEvent event) {

        if (!ready)
            return;
        
        if (ressource.getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING)
            ressource.getMediaPlayer().pause();
        else
            ressource.getMediaPlayer().play();

    }

}

