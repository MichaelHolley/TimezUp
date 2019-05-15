package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TimeApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Font.loadFont(TimeApp.class.getResource("Autumn.TTF").toExternalForm(), 10);
		Parent root = FXMLLoader.load(getClass().getResource("LocalTime.fxml"));
		stage.setTitle("Debt-Manager");
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
	}
}
