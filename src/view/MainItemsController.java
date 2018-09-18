package view;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainItemsController {
	
	
	@FXML
	private void goToItem() throws IOException{
		Main.showProperty();
	}
	
}
