package controller;

import java.io.IOException;

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
import javafx.stage.Stage;

public class PrincipalController {


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

	
	private Stage stage;

	
	public void ouvrir(ActionEvent event) {

		MenuItem item = (MenuItem) event.getSource();

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ressources/fxml/principal.fxml"));
			Scene scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.setTitle("Création d'un nouvel exercice");
			stage.show();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@FXML
	public void supprimer (Event event) {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../ressources/fxml/alerteSuppressionExo.fxml"));
		try {
			stage.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage.show();
	}
	
	@FXML
	public void quitter (Event event) {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../ressources/fxml/quitter.fxml"));
		try {
			stage.setScene(new Scene(loader.load()));
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
	public void part (MouseEvent event) {
		Platform.exit();
	}
	
	@FXML
	public void goback(ActionEvent event) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage primaryStage) {
		stage = primaryStage;

	}
}

