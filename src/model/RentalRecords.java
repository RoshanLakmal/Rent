package model;
public class RentalRecords {

	 String Record_Id;
	 String Customer_Id;
	 DateTime RentDate;
	DateTime Estimated_Return_Date;
	DateTime Actual_Return_Date;
	double RentalFee;
	double Late_Fee;
	
	public RentalRecords()
	{
		this.Record_Id = null;
		this.RentDate = null;
		this.Estimated_Return_Date = null;
		this.Actual_Return_Date = null;
		this.RentalFee = 0;
		this.Late_Fee = 0;
		
	}
	
//	public RentalRecords(String Record_Id, String Customer_Id, DateTime RentDate, DateTime Estimated_Return_Date, DateTime Actual_Return_Date)
//    {
//		
//		this.Record_Id = Record_Id;
//		this.Customer_Id = Customer_Id;
//		this.RentDate = RentDate;
//		this.Estimated_Return_Date = Estimated_Return_Date;
//		this.Actual_Return_Date = Actual_Return_Date;
//		this.RentalFee = RentalFee;
//		this.Late_Fee = Late_Fee;
//    }
	
  
	
	public String toString()
	{
		String test = "";
		
		test = test + this.Record_Id+ ":" + this.RentDate + ":" + this.Estimated_Return_Date + ":" + this.Actual_Return_Date + ":" + this.RentalFee + ":" + this.Late_Fee;
		
		return test;
	}
	
	public String getDetails()  //getting all the rental records of each property
	{
		String test = "";
		
		test = test +
		"Rental ID            : " + this.Record_Id + "\n" +
		"Rental Date          : " + this.RentDate + "\n" +
		"Estimated Return Date: " + this.Estimated_Return_Date + "\n" ;
		if (this.Actual_Return_Date != null) {
			test = test + "\n" +
			"Actual Return Date   : " + this.Actual_Return_Date + "\n" +	
			"Rental fee           : " +	this.RentalFee + "\n" +
			"Late Fee             : " + this.Late_Fee ;
		}

		
		return test;
	}

}

