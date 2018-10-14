package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import database.DataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.DateTime;

public class PropertyDetailWindow {
	
	@FXML
    private AnchorPane propertyDetailPanel;
	
	@FXML
	private TableView rentalTable;
	
	@FXML
	private Text mytext;
	
	@FXML
	private Button bclose;
	
	@FXML
	private Button brent;
	
	public void initialize() {
		
	}
	
	public void setData(String firstName){
		mytext.setText(firstName);
	}
	
	public void ok(ActionEvent ae){
		 Stage stage = (Stage) bclose.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void rent() throws SQLException{
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(propertyDetailPanel.getScene().getWindow());
        dialog.setTitle("Add New Contact");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AddRentalRecordWindow.fxml"));
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
        	
        	AddRentalRecordController addRentalController = fxmlLoader.getController();
        	
        	String customerID = addRentalController.getCustomer_Id().getText();
        	DateTime t = stringToDateTime(addRentalController.getRentDate().getText());
        	int days = Integer.parseInt(addRentalController.getNumOfRentDay().getText());
        	
        	
        	
        	for(int i=0;i<DataSource.getInstance().queryArtists().size();i++){
        		if(mytext.equals(DataSource.getInstance().queryArtists().get(i).getProperty_Id())){
        			boolean rented = DataSource.getInstance().queryArtists().get(i).rent(customerID,t, days);
        		}
        		
//        		boolean rented = myProperty[i].rent(customerID,t, days);
//   			 System.out.println(DataSource.getInstance().queryArtists().get(i).getProperty_Id());
   		 }
        	
        	
//        	System.out.println(addRentalController.getCustomer_Id().getText());
//        	System.out.println(addRentalController.getRentDate().getText());
//        	System.out.println(addRentalController.getNumOfRentDay().getText());
        	
        	
        	AddRentalRecordController addRentalRecordController = fxmlLoader.getController();
//          Property newContact = addPropertyController.getNewProperty();
          DataSource.getInstance().insertProperty(addRentalRecordController.getProperty_Id().getText(), 
								        		  addRentalRecordController.getStreet_num().getText(), 
								        		  addRentalRecordController.getStreet_name().getText(), 
								        		  addRentalRecordController.getSuburb().getText(),
          										  Integer.parseInt(addRentalRecordController.getNum_of_beds().getText()),
          										  addRentalRecordController.getProperty_status().getText(), 
          										  addRentalRecordController.getProperty_type().getText(),
          										  stringToDateTime(addRentalRecordController.getLastMaintenanceDate().getText()));
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
