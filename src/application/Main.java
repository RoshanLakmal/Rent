package application;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import database.*;

public class Main extends Application {
	 
	 

	DataSource datasource = new DataSource();
	    
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		if(!datasource.open()) {
            System.out.println("Can't open datasource");
            return;
        }
		
		List<Property> propertys = datasource.queryArtists();
        if(propertys == null) {
            System.out.println("No artists!");
            return;
        }

        for(Property property : propertys) {
            System.out.println(property.getDetails());
        }

		
        datasource.close();
//		try {
//            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
//            Statement statement = conn.createStatement();
//            
//            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_PROPERTY + 
//            		" (" + COLUMN_PROPERTY_ID + " TEXT, " + 
//            			   COLUMN_PROPERTY_TYPE + " TEXT" + 
//            		")");
//            statement.execute("INSERT INTO RENTAL_PROPERTY (property_Id, property_type) " +
//                  "VALUES('ROSHAN', 'APARTMENT')");
//            statement.execute("UPDATE RENTAL_PROPERTY SET property_type='SUIT' WHERE property_Id='ROSHAN'");
//            statement.execute("DELETE FROM RENTAL_PROPERTY WHERE property_Id='LAKMAL'");
        
           
//            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_PROPERTY);
//            while(results.next()) {
//                System.out.println(results.getString("property_Id") + " " +
//                                    " " +
//                                   results.getString("property_type"));
//            }
//            
//            results.close();
//            statement.close();
//            conn.close();

//            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\databases\\testjava.db");
//            Class.forName("org.sql.JDBC");

//        } catch (SQLException e) {
//            System.out.println("Something went wrong: " + e.getMessage());
//        }
		
		Parent root = FXMLLoader.load(getClass().getResource("MainProgramWindow.fxml"));
        primaryStage.setTitle("FLEXIRENT SYSTEM");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
