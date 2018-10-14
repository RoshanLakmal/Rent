package application;

import model.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddPropertyWindowController {
	
	
	@FXML
    private TextField property_Id;

	@FXML
    private TextField street_num;
	
	@FXML
    private TextField street_name;
	
	@FXML
    private TextField suburb;
	
	@FXML
    private TextField num_of_beds;
	
    @FXML
    private TextField property_type;
    
    @FXML
    private TextField property_status;
    
    public TextField getProperty_Id() {
		return property_Id;
	}

	public TextField getProperty_type() {
		return property_type;
	}

	public TextField getStreet_num() {
		return street_num;
	}

	public TextField getStreet_name() {
		return street_name;
	}

	public TextField getSuburb() {
		return suburb;
	}

	public TextField getProperty_status() {
		return property_status;
	}

	public TextField getNum_of_beds() {
		return num_of_beds;
	}

	
//	public Property getNewProperty() {
//        String propertyId = property_Id.getText();
//        String propertyType = property_type.getText();
//        Property newProperty = null;
//        
//        if(propertyType.equals("APARTMENT"))
//		{
////        	newProperty = new Apartment(propertyId,propertyType);
////			Property a1 = new Apartment(id, number, name, suburb, num, type, "Available",  new DateTime(1,1,0000));	
////			myProperty[propertyCount] = a1;
////			propertyCount++;
////			System.out.println(propertyCount + " properties listed in the system");
//		}
//		else if(propertyType.equals("SUIT"))
//		{
////			newProperty = new Suit(propertyId,propertyType);
////			System.out.println("Enter the last maintenace date: ");
////			System.out.print("Day: ");
////			int day = sc.nextInt();
////			System.out.print("Month: ");
////			int month = sc.nextInt();
////			System.out.print("Year: ");
////			int year = sc.nextInt();  							
////			DateTime t = new DateTime(day,month,year);
////			System.out.println("Last maintenance date is " + t.getFormattedDate());
////			Property a1 = new Suit(id, number, name, suburb, num, type, "Available",  t);	
////			myProperty[propertyCount] = a1;
////			propertyCount++;
////			System.out.println(propertyCount + " properties listed in the system");
//		}
//        return newProperty;
//    }
    

}
