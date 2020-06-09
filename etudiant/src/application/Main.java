package application;

import controller.PrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressources/fxml/accueil_etu.fxml"));
			Scene scene = new Scene(loader.load());
			primaryStage.setScene(scene);
//			primaryStage.setResizable(false);
//			primaryStage.getIcons().add(new Image(""));
			primaryStage.show();
			PrincipalController controller = loader.getController();
			PrincipalController.setStage(primaryStage);

			primaryStage.setOnCloseRequest(event -> {
				event.consume();
				controller.quitter(event);
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
