package application;

import controller.PrincipalController;
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ressources/fxml/fenetre d'acceuil v2.fxml"));
			Scene scene = new Scene(loader.load());
			primaryStage.setScene(scene);
			primaryStage.show();
			PrincipalController p1 = loader.getController();
			p1.setStage(primaryStage);
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					event.consume();
					p1.quitter(event);

				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
