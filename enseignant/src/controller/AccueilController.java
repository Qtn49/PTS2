package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class AccueilController {

	private Stage stage;
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

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
	
}
