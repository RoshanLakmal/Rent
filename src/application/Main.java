package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage primaryStage;
	private static BorderPane mainLayout;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Main Program Window");
		showMainView();
		showMainItems();
	}
	
	public void showMainView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("..//view//MainProgramWindow.fxml"));
		mainLayout = loader.load();
		
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void  showMainItems() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("..//view//MainItem.fxml"));
		
		BorderPane mainItems = loader.load();
		mainLayout.setCenter(mainItems);
		
		
	}
	
	
	public static void  showProperty() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("..//view//PropertyDetailWindow.fxml"));
		
		BorderPane property = loader.load();
		mainLayout.setCenter(property);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
