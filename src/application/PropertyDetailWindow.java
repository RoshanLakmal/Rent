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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PropertyDetailWindow {
	
	@FXML
    private AnchorPane propertyDetailPanel;
	
	@FXML
	private Text mytext;
	
	@FXML
	private Button bclose;
	
	@FXML
	private Button brent;
	
	public void initialize() {
		
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
        	
//        	AddPropertyWindowController addPropertyController = fxmlLoader.getController();
////            Property newContact = addPropertyController.getNewProperty();
//            DataSource.getInstance().insertProperty(addPropertyController.getProperty_Id().getText(), 
//            										addPropertyController.getStreet_num().getText(), 
//            										addPropertyController.getStreet_name().getText(), 
//            										addPropertyController.getSuburb().getText(),
//            										Integer.parseInt(addPropertyController.getNum_of_beds().getText()),
//            										addPropertyController.getProperty_status().getText(), 
//            										addPropertyController.getProperty_type().getText(),
//            										stringToDateTime(addPropertyController.getLastMaintenanceDate().getText()));
//            listPropertys();
//            System.out.println(newContact.getDetails());
        }
	}
	
	public void setData(String firstName){
		mytext.setText(firstName);
	}
}
