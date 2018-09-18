package model;
public class Suit extends Property {
	
	int numRec = 0;
	double rent = 0;
	
	public Suit(String property_Id, String Street_num, String Street_name, String Suburb, int Num_of_beds,
			String property_type, String property_status, DateTime lastMaintenaceDate)
	{
	super(property_Id, Street_num, Street_name, Suburb, Num_of_beds, property_type, property_status,lastMaintenaceDate);
	
	}
	
	public boolean rent(String customerid, DateTime rentDate , int numOfRentDays)      //Renting a suit
	{
		
		boolean rentable = false;
		DateTime esreturnday = new DateTime(rentDate,numOfRentDays);
		DateTime nextMaintenanceDate = new DateTime(this.lastMaintenanceDate, 10);
		int nextMaintenance = DateTime.diffDays(nextMaintenanceDate, esreturnday);
		
		if (this.property_status.equals("Available") && numOfRentDays>0){
			if(nextMaintenance>0)
			{	
			RentalRecords records= new RentalRecords();
			records.Record_Id = this.property_Id+"_"+customerid+"_"+rentDate.getEightDigitDate();	
			
			
			records.RentDate = rentDate;
			records.Estimated_Return_Date = esreturnday;
			
			System.arraycopy(this.arr, 0, this.arr, 1, 9);
			this.arr[0]=records;
			this.property_status="Rented";
			if (numRec<10) {
				numRec++;
			}
			
			rentable = true;
		}
			else {
				rentable = false;
			}
		}
		return rentable;
	}

	
	
    public double RentalRates(int numOfRentDays)             //The calculation of the rent on or before the estimated return date
    {
    	if(numOfRentDays>=1)
    	{
    		rent = numOfRentDays*554;
    	}
    	return rent;
    }
	
    public double LateFee(DateTime Estimated_Date, DateTime Actual_Date)         // Calculation of the late fee if the suit
    {                                                                            // if the suit has been returned after the estimated date.
    	double Late_fee = 0;
    	
    	int numOfRentDays = DateTime.diffDays(Actual_Date, Estimated_Date);
    	
    	if(numOfRentDays>0)
    	{
    		Late_fee = 662*(numOfRentDays);
    		rent = rent + this.RentalRates(numOfRentDays);
    	}
    	
    	return Late_fee;
    }
    
    public boolean Return(DateTime returnDate)                 // Returning a suit
    {
    	int numOfDays = DateTime.diffDays(returnDate, this.arr[0].RentDate);
		boolean returnable = false;
		if(numOfDays>0){
			returnable = true;
			this.arr[0].Actual_Return_Date = returnDate;
			this.property_status="Available";
			int days = DateTime.diffDays(this.arr[0].Estimated_Return_Date, this.arr[0].RentDate);
			if (days < 0 ){
				
				this.arr[0].RentalFee = RentalRates(days);
			}
			else 
			{
				
				this.arr[0].Late_Fee = LateFee(this.arr[0].Estimated_Return_Date, returnDate);
				this.arr[0].RentalFee = RentalRates(days);
			}			
			
		}
		return returnable;
        	
    }
    

    public boolean performanceMaintenance()                 //Suit is under maintenance
    {
    	boolean maintenance = false;
		if (this.property_status.equals("Available")) {
			maintenance=true;
			this.property_status="Under Maintenance";
		}
		return maintenance;

    }
    
    public boolean completeMaintenance(DateTime completionDate)       //Suit maintenance has been completed
    {
    	boolean complete = false;
		System.out.println("HERE");
		if (this.property_status.equals("Maintenance")) {
			this.property_status="Available";
			this.lastMaintenanceDate= completionDate;
			complete=true;
		}
		return complete;

    	
    }
    
    public String toString()
	{
		String test = "";
		
		test = test + this.property_Id+":"+this.Street_num+":" + this.Street_name + ":" + this.Suburb + ":" + this.property_type + ":" + this.Num_of_beds + ":" + this.property_status;
		return test;
	}
	
	
	
	public String getDetails()      // getting details of the each suit with its 10 most recent rental records.
	{
		
	String test = "";
		
		test = test + 
		"Property ID: " + this.property_Id +"\n" +
		"Address:     "+ this.Street_num+" " +this.Street_name + "\n" + 
		"Type:        " + this.property_type + "\n" + 
		"Bedrooms:    " + this.Num_of_beds + "\n" + 
		"Status:      " + this.property_status + "\n"+
		"Last Maintenance Date: " + this.lastMaintenanceDate + "\n"+
		"-----------------------------------"+"\n";
		
		for(int i=0; i<numRec; i++)
		{
			test=test+this.arr[i].getDetails()+"\n"; 
		}
	 return test;
	}


	

    
}
