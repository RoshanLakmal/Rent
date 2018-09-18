package view;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;

public class MenuBarConroller {
	@FXML
	private void goToHome() throws IOException{
		Main.showMainItems();
	}
}
