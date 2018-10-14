package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddRentalRecordController {

	@FXML
    private TextField Customer_Id;
	
	@FXML
    private TextField RentDate;
    
    @FXML
    private TextField numOfRentDay;

	public TextField getCustomer_Id() {
		return Customer_Id;
	}

	public TextField getRentDate() {
		return RentDate;
	}

	public TextField getNumOfRentDay() {
		return numOfRentDay;
	}   
}
