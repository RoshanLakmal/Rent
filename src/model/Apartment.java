package model;

import javafx.beans.property.SimpleStringProperty;

public class Apartment extends Property {
	
    private int numRec = 0;
	double rent = 0;
	
	public Apartment(){
		super();
	}
	
//	public Apartment(SimpleStringProperty property_Id, String Street_num, String Street_name, String Suburb, int Num_of_beds,
//			SimpleStringProperty property_type, String property_status, DateTime lastMaintenanceDate) {
//		super(property_Id, Street_num, Street_name, Suburb, Num_of_beds, property_type, property_status, lastMaintenanceDate);
//	
//	}

	public boolean rent(String customerID, DateTime rentDate, int numOfRentDay)  //Renting an apartment
	{     
		boolean temp = true;
		
		if (numOfRentDay < 2 && (rentDate.getThisWeek().equals("Sunday") || rentDate.getThisWeek().equals("Monday") ||
				rentDate.getThisWeek().equals("Tuesday") || rentDate.getThisWeek().equals("Wednesday") || rentDate.getThisWeek().equals("Thursday"))){
			temp = false;
		}
		
		else if ( ( rentDate.getThisWeek().equals("Friday") || rentDate.getThisWeek().equals("Saturday")) && numOfRentDay < 3) {
			temp = false;
		}

		else if (numOfRentDay > 28) {
			temp = false;
		}
		

		
		if(temp)
		{
			RentalRecords records= new RentalRecords();
			records.setRecord_Id(this.property_Id+"_"+customerID+"_"+rentDate.getEightDigitDate());	

			DateTime esreturnday = new DateTime(rentDate,numOfRentDay);
			
			records.setRentDate(rentDate);
			
			records.setEstimated_Return_Date(esreturnday);
			
//			System.arraycopy(this.arr, 0, this.arr, 1, 9);
//			this.arr[0]=records;
			
//			this.property_status="Rented";
			this.setProperty_status("Rented");
			
//			if (numRec<10) {
//				numRec++;
//			}
		}
		
//		else if(numOfRentDay<1){
//			temp = false;
//		}
		
		return temp;

			
		
		
	}
	
	
	
	
	public boolean Return(DateTime returnDate)               // Returning an apartment
		{
		int numOfDays = DateTime.diffDays(returnDate, this.arr[0].RentDate);
		boolean returnable = false;
		if(numOfDays>0){
			returnable = true;
			this.arr[0].Actual_Return_Date = returnDate;
			this.property_status="Available";
			int days = DateTime.diffDays(this.arr[0].Estimated_Return_Date, this.arr[0].RentDate);
			if (DateTime.diffDays(this.arr[0].Actual_Return_Date, this.arr[0].Estimated_Return_Date) < 0 ){
				
				this.arr[0].RentalFee = RentalRates(days);
				
			}
			else {
//				days = DateTime.diffDays(this.arr[0].Actual_Return_Date, this.arr[0].RentDate);
				this.arr[0].RentalFee = RentalRates(days);
				this.arr[0].Late_Fee = LateFee(this.arr[0].Estimated_Return_Date, returnDate);
			}			
			
		}
		return returnable;

		}

	public double RentalRates(int numOfRentDays) {        //The calculation of the rent on or before the estimated return date
		
		if (this.Num_of_beds == 1) {
			rent = rent+ 143 * numOfRentDays;
		} else if (this.Num_of_beds == 2) {
			rent = rent + 210 * numOfRentDays;
		} else if (this.Num_of_beds == 3) {
			rent = rent + 319 * numOfRentDays;
		}

		return rent;
	}

	public boolean performanceMaintenance() {              //Apartment is under maintenance
		boolean maintenance = false;
		if (this.property_status.equals("Available")) {
			maintenance=true;
			this.property_status="Under Maintenance";
		}
		return maintenance;

	}
	
	public boolean completeMaintenance(DateTime completionDate)      //Apartment maintenance has been completed 
	{
		boolean complete = true;
		
		if(this.property_status.equals("rented"))
		{
			complete = false;
		}
		else if(this.property_status.equals("Maintenance"))
		{
			this.property_status="Available";
			
		}
		return complete;
	}

	public double LateFee(DateTime Estimated_date, DateTime Actual_date)  // Calculation of the late fee if the apartment
	{                                                                     // if the apartment has been returned after the estimated date.
		double late_fee = 0;
		int numOfRentDays = DateTime.diffDays(Actual_date,Estimated_date);
       
		if (numOfRentDays > 0 && this.Num_of_beds == 1) {
			
			late_fee = late_fee+ (143 * (numOfRentDays) * 115) / 100;
			rent = rent + this.RentalRates(numOfRentDays);
		}

		else if (numOfRentDays > 0 && this.Num_of_beds ==2) {
			
			late_fee = late_fee +210+ (210 * (numOfRentDays) * 115) / 100;
			rent = rent + this.RentalRates(numOfRentDays);

		}

		else if (numOfRentDays > 0 && this.Num_of_beds == 3) {
			late_fee = late_fee + 319 * (numOfRentDays);
			rent = rent + late_fee;
            
		}

		return late_fee;

	}
	
	public String toString()
	{
		String test = "";
		
		test = test + this.property_Id+":"+this.Street_num+":" + this.Street_name + ":" + this.Suburb + ":" + this.property_type + ":" + this.Num_of_beds + ":" + this.property_status;
		return test;
	}
	
	
	
	public String getDetails()   // getting details of the each apartment with its 10 most recent rental records.
	{
		
		
		String test = "";
		
		test = test + 
		"Property ID: " + this.property_Id +"\n" +
		"Address:     "+ this.Street_num+" " +this.Street_name + "\n" + 
		"Type:        " + this.property_type + "\n" + 
		"Bedrooms:    " + this.Num_of_beds + "\n" + 
		"Status:      " + this.property_status + "\n"+
		"-----------------------------------"+"\n";
		for(int i=0; i<numRec; i++)
		{
			test=test+this.arr[i].getDetails()+"\n"; 
		}
	 return test;
	}



}

