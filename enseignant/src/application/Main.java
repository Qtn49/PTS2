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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/ressources/fxml/accueil.fxml"));
			Scene scene = new Scene(loader.load());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Accueil");
			primaryStage.show();


			PrincipalController controller = loader.getController();
			controller.setStage(primaryStage);

			primaryStage.setOnCloseRequest(event -> {
				event.consume();
				controller.quitter();
			});


		} catch (Exception ignored) {

		}


	}

	public static void main(String[] args) {
		launch(args);
	}

}
