package application;

import java.io.IOException;
import java.util.Optional;

import database.DataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import model.*;

public class MainWindowController {
	
	@FXML
    private BorderPane mainPanel;
	
	@FXML
	private TableView propertyTable;
	
	public void listPropertys(){
		Task<ObservableList<Property>> task = new GetAllPropertyTask();
		propertyTable.itemsProperty().bind(task.valueProperty());
		new Thread(task).start();
	}
	
	@FXML
    public void showAddContactDialog() {
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
            Property newContact = addPropertyController.getNewProperty();
            System.out.println(newContact.getDetails());
        }
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
