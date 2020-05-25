package controller;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
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
		PrincipalController controller = null;
		FXMLLoader loader;
		Scene scene = null;
		boolean valide = true;

		try {

			loader = new FXMLLoader(getClass().getResource("../ressources/fxml/principal.fxml"));
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
				controller.setExo(file);
				System.out.println(file);

				if (file == null)
					valide = false;
				else
					valide = true;
		}

		if (valide) {

			stage.setScene(scene);
			stage.setTitle("Création d'un nouvel exercice");

		}

	}
	
}
