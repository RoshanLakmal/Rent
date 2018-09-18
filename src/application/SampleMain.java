package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SampleMain extends Application {

	private Stage primaryStage;
	private BorderPane mainLayout;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Test");
		showMainView();
	}
	
	public void showMainView(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SampleMain.class.getResource("Sample.fxml"));
		try {
			mainLayout = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
