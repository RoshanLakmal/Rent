package application;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;

public class MainWindowController {
	
	@FXML
    private BorderPane mainPanel;
	
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
//        if(result.isPresent() && result.get() == ButtonType.OK) {
//            ContactController contactController = fxmlLoader.getController();
//            Contact newContact = contactController.getNewContact();
//            data.addContact(newContact);
//            data.saveContacts();
//        }
	}
}
