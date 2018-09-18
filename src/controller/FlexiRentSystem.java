package controller;

import java.util.Scanner;

import model.Apartment;
import model.DateTime;
import model.Property;
import model.Suit;

import java.io.BufferedReader;
import java.io.IOException;

public class FlexiRentSystem {
int propertyCount = 0;
private int number;	

Property [] myProperty = new Property[50];

public void MenuNavigation() {
	System.out.println("****FLEXIRENT SYSTEM MENU****");
	System.out.println();
	System.out.println("Add Property           :1");
	System.out.println("Rent Property          :2");
	System.out.println("Return Property        :3");
	System.out.println("Property Maintenance   :4");
	System.out.println("Complete Maintenance   :5");
	System.out.println("Display All Properties :6");
	System.out.println("Exit Program           :7");
	System.out.print("Enter your choice      : ");
	}


public void MainMenu()
{

try {	
	
MenuNavigation();

Scanner sc = new Scanner(System.in);
number = sc.nextInt();


if(number<1 || number>7)
{
	throw new Exception();
}

if(number>=1 || number <= 7)
{	
switch(number) {
	case 1:                                     //Add a property
		String id ="", type = "";
		boolean accepted = false;
		
		while(!accepted)
		{
			System.out.println("Enter Property ID: (For Apartments start with 'A_' and for Suits start with 'S_')");
			id = sc.next();
			
			if(id.charAt(0)=='A' && id.charAt(1)=='_')
			{
				type = "Apartment";
				accepted = true;
			}
			else if(id.charAt(0)=='S' && id.charAt(1)=='_')
			{
				type = "Suit";
				accepted = true;
			}
			
			
			if(!accepted)        // If the user inputs a wrong id which is not starting from 'A_' or 'S_', this part gets implemented
			{
				System.out.println("ID must start with either 'A_' for apartments or 'S_' for Premium Suites");
			}
			
			if(propertyCount>0){
					for(int i=0; i<propertyCount;i++){
						if((myProperty[i].property_Id.equals(id))){
							System.out.println("This apartment/Suite is already added to the system !");
							System.out.println("Please specify a new id");
							accepted = false;
						}	
					}
				}
		}
		
		
		
		System.out.println("Enter street number: ");
		String number = sc.next();
		
		System.out.println("Enter street name: ");
		String name = sc.next();
		
		
		System.out.println("Enter suburb: ");
		String suburb = sc.next();
		
		int num=0;
		while (num>3 || num<1) {           // Apartments should always have 1-3 bedrooms
			if (type.equals("Apartment")) {
				System.out.print("Enter Number of bedrooms(1-3): ");
				num = sc.nextInt();
			}
			else {
				num=3;   //Each Premium Suite always has 3 bedrooms
			}
		}


		
		if (accepted) {
			if(type.equals("Apartment"))
			{
				Property a1 = new Apartment(id, number, name, suburb, num, type, "Available",  new DateTime(1,1,0000));	
				myProperty[propertyCount] = a1;
				propertyCount++;
				System.out.println(propertyCount + " properties listed in the system");
			}
			else if(type.equals("Suit"))
			{
				System.out.println("Enter the last maintenace date: ");
				System.out.print("Day: ");
				int day = sc.nextInt();
				System.out.print("Month: ");
				int month = sc.nextInt();
				System.out.print("Year: ");
				int year = sc.nextInt();  							
				DateTime t = new DateTime(day,month,year);
				System.out.println("Last maintenance date is " + t.getFormattedDate());
				Property a1 = new Suit(id, number, name, suburb, num, type, "Available",  t);	
				myProperty[propertyCount] = a1;
				propertyCount++;
				System.out.println(propertyCount + " properties listed in the system");
			}
			}
		
		MainMenu();
		break;

	case 2:                         // Rent a property
		
		boolean valid=false;
		System.out.print("Enter property ID: ");
		String propID = sc.next();
				  					
		for (int i=0; i<propertyCount; i++) {
			if(myProperty[i].property_Id.equals(propID) && myProperty[i].property_status.equals("Available")) {
				System.out.print("Enter customer ID: ");
				String customerID = sc.next();
					
				System.out.println("Enter rent date in DD/MM/YYYY format: ");
				System.out.print("Day: ");
				int day = sc.nextInt();
				System.out.print("Month: ");
				int month = sc.nextInt();
				System.out.print("Year: ");
				int year = sc.nextInt();  							
				DateTime t = new DateTime(day,month,year);
				System.out.println("Rent Date is " + t.getFormattedDate());
					
				System.out.print("Enter no of days for which property will be rented: ");
				int days = sc.nextInt();	
					
				boolean rented = myProperty[i].rent(customerID,t, days);
				
				
				if (rented){
					myProperty[i].property_status = "Rented";
					System.out.println(myProperty[i].property_type + " " 
							+ myProperty[i].property_Id	+ " is now rented by customer " + customerID);
					valid=true;
				}  	  							
				break;
			}
		}
				
		if(!valid) {
			System.out.println("Property " + propID + " could not be rented");
		}
			
		   
		 MainMenu();
		
		break;
		
	case 3:                     // Return a property
		
		boolean returned = false;
		
		
		System.out.print("Please enter propertyID of the property to be returned: ");
		String propID1 = sc.next();
		
	 
		for (int i=0; i<propertyCount; i++) {
			if(propID1.equals(myProperty[i].property_Id)) {
				if(myProperty[i].property_status.equals("Rented")) {
					
					System.out.println("Enter return date in DD/MM/YYYY format: ");
					System.out.print("Day: ");
					int day = sc.nextInt();
					System.out.print("Month: ");
					int month = sc.nextInt();
					System.out.print("Year: ");
					int year = sc.nextInt();  							
					DateTime t = new DateTime(day,month,year);
					System.out.println("Return Date is " + t.getFormattedDate());
					
					boolean done = myProperty[i].Return(t);
			
					if (done) {
						myProperty[i].property_status = "Available";
						System.out.println("Property " + propID1 + " was successfully returned on " + t.getFormattedDate());
					}
					else {
						System.out.println("ERROR returning Property");
					}
					break;
				}
				else {
					System.out.println("This property is currently not being rented");
				}
				
			}
			
		}
	 
	 
		MainMenu();

		break;
		
	  case 4:                            // Property Maintenace
		  
		  System.out.print("Enter property ID: ");
	  		String propID2 = sc.next();
					  					
	  		for (int i=0; i<propertyCount; i++) {
	  			if(myProperty[i].property_Id.equals(propID2)) {
					if(myProperty[i].property_status.equals("Available"))
					{	
					boolean maintenance = myProperty[i].performanceMaintenance();
					
					if (maintenance){
						System.out.println(myProperty[i].property_type + " " 
								+ myProperty[i].property_Id	+ " is now under Maintenance");
					}
					else {
						System.out.println("This property cannot be placed under Maintenance because it is being rented at the moment.");
					}
				     
					break;
					}
					else {
						System.out.println("This property cannot be placed under Maintenance because it is being rented at the moment.");
					}
	  			}	
	  			
	  		}

	 MainMenu(); 		

	break;
	  case 5 :                               // Complete Maintenance
		  System.out.print("Enter property ID: ");
	  		String propID3 = sc.next();
					  					
	  		for (int i=0; i<propertyCount; i++) {
	  			if(myProperty[i].property_Id.equals(propID3)) {
					
	  				System.out.println("Enter maintenance completion date in DD/MM/YYYY format: ");
					System.out.print("Day: ");
					int day = sc.nextInt();
					System.out.print("Month: ");
					int month = sc.nextInt();
					System.out.print("Year: ");
					int year = sc.nextInt();  							
					DateTime t = new DateTime(day,month,year);
					System.out.println("Date is " + t.getFormattedDate());
	  				  				
					boolean completeMaintenance = myProperty[i].completeMaintenance(t);
					
					if (completeMaintenance){
						System.out.println(myProperty[i].property_type + " " 
								+ myProperty[i].property_Id	+ " has all maintenance completed and\r\n" + 
										"ready for rent");
					}else {
						System.out.println("Property could not be completed in maintenance");
					}
					break;
	  			}
	  		}

		  MainMenu();
		  break;
	
	case 6:                             //Display ALl Properties
		System.out.println("The details of the properties are: ");
			for (int i =0; i<propertyCount; i++) {
				System.out.println("--------------------");
				System.out.println(myProperty[i].getDetails());
			}
			
			MainMenu();
			break;
		
	case 7 :                               // Exit Program
		
		boolean dec = false;
		while(!dec)
		{
		System.out.println("Are you sure you want to exit? (Y/N): ");
		String decision = sc.next();
		
		if(decision.equals("Y") || decision.equals("y"))
		{
			System.out.println("System is terminated!");
			System.exit(0);
			dec = true;
		}
		else if(decision.equals("N") || decision.equals("n")){
			MainMenu();
		}
		if(!dec)
		{
			System.out.println("Invalid input!");
		}
		}
		

}			
}
}


catch(Exception e)
{

System.out.println("Invalid input. Please try again !");
System.out.println();
MainMenu();
}


}


}

