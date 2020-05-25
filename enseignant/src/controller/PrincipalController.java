package controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PrincipalController {


	@FXML
	private MenuItem MIQ;

	@FXML
	private Button BQ1;

	
	private Stage stage;

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
	public void part (MouseEvent event) {
		Platform.exit();
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage primaryStage) {
		stage = primaryStage;

	}
}

