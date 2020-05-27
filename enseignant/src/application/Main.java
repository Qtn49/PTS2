package application;


import controller.PrincipalController;


import controller.AccueilController;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ressources/fxml/accueil.fxml"));
			Scene scene = new Scene(loader.load());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Accueil");
			primaryStage.show();

			AccueilController p1 = loader.getController();
			p1.setStage(primaryStage);
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					event.consume();
					p1.quitter(event);

				}
			});


			AccueilController controller = loader.getController();
			controller.setStage(primaryStage);

			Spinner aSpinner = (Spinner) event.getSource();
			for (Node aNode ; aSpinner.getParent().getChildrenUnmodifiable()) {
				
			}
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
