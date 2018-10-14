package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.DataSource;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;

public class MainWindowController {
	
	@FXML
    private BorderPane mainPanel;
	
	@FXML
	private TableView propertyTable;
	
    private DataSource data;
	
	public void listPropertys(){
		Task<ObservableList<Property>> task = new GetAllPropertyTask();
		propertyTable.itemsProperty().bind(task.valueProperty());
		new Thread(task).start();
	}
	
	
	 public void initialize() {
//	        data = new DataSource();
//	        data.loadContacts();
//	        contactsTable.setItems(data.getContacts());
		 propertyTable.setOnMouseClicked(new EventHandler<MouseEvent>(){
	        	@Override
	        	public void handle(MouseEvent event){
//	        		System.out.println("Hi");
	        		FXMLLoader Loader = new FXMLLoader();
	        		Loader.setLocation(getClass().getResource("PropertyDetailWindow.fxml"));
	               try{
	            	   Loader.load();
	               }catch (IOException ex){
	            	   Logger.getLogger(PropertyDetailWindow.class.getName()).log(Level.SEVERE,null,ex);
	               }
	               PropertyDetailWindow myController = Loader.getController();
//	               if(propertyTable.getSelectionModel().getSelectedItem().getClass() instanceof Apartment){
//	            	   
//	               }else{
//	            	   
//	               }
//	               instanceof
	               final Property myProperty = (Property)propertyTable.getSelectionModel().getSelectedItem();
	               myController.setData(myProperty.getProperty_Id());
	               Stage stage = new Stage();
	               stage.setScene(new Scene(Loader.getRoot(), 600, 400));
	               stage.show();
//	               System.out.println("clicked"+contactsTable.getSelectionModel().getSelectedItem().getFirstName());
	        	}
	        });
	    }
	
	@FXML
    public void showAddContactDialog() throws SQLException {
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Add New Contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AddPropertyWindow.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch(IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        
        if(result.isPresent() && result.get() == ButtonType.OK) {
        	
        	AddPropertyWindowController addPropertyController = fxmlLoader.getController();
//            Property newContact = addPropertyController.getNewProperty();
            DataSource.getInstance().insertProperty(addPropertyController.getProperty_Id().getText(), 
            										addPropertyController.getStreet_num().getText(), 
            										addPropertyController.getStreet_name().getText(), 
            										addPropertyController.getSuburb().getText(),
            										Integer.parseInt(addPropertyController.getNum_of_beds().getText()),
            										addPropertyController.getProperty_status().getText(), 
            										addPropertyController.getProperty_type().getText(),
            										stringToDateTime(addPropertyController.getLastMaintenanceDate().getText()));
            listPropertys();
//            System.out.println(newContact.getDetails());
        }
	}
	
	private DateTime stringToDateTime(String sDateTime){
		  String string = sDateTime;
		  String[] parts = string.split("/");
		  int day = Integer.parseInt(parts[0]);
		  int month = Integer.parseInt(parts[1]);
		  int year = Integer.parseInt(parts[2]);
		  
		  DateTime t = new DateTime(day,month,year);
		  return t;
	  }
}

class GetAllPropertyTask extends Task {

	@Override
	public ObservableList<Property> call() {
		// TODO Auto-generated method stub
		return FXCollections.observableArrayList
				(DataSource.getInstance().queryArtists());
	}
	
	
	
}
