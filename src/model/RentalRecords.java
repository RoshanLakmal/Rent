package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class RentalRecords {

	SimpleStringProperty Record_Id;
	SimpleStringProperty Customer_Id;
	SimpleObjectProperty<DateTime> RentDate;
	SimpleObjectProperty<DateTime> Estimated_Return_Date;
	SimpleObjectProperty<DateTime> Actual_Return_Date;
	SimpleDoubleProperty RentalFee;
	SimpleDoubleProperty Late_Fee;
	
	public RentalRecords()
	{
		this.Record_Id = new SimpleStringProperty();
		this.Customer_Id = new SimpleStringProperty();
		this.RentDate = new SimpleObjectProperty<>();
		this.Estimated_Return_Date = new SimpleObjectProperty<>();
		this.Actual_Return_Date = new SimpleObjectProperty<>();
		this.RentalFee = new SimpleDoubleProperty();
		this.Late_Fee = new SimpleDoubleProperty();
		
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
	
	public String getRecord_Id() {
		return Record_Id.get();
	}

	public void setRecord_Id(String record_Id) {
		Record_Id.set(record_Id);
	}

	public String getCustomer_Id() {
		return Customer_Id.get();
	}

	public void setCustomer_Id(String customer_Id) {
		Customer_Id.set(customer_Id);
	}

	public DateTime getRentDate() {
		return RentDate.get();
	}

	public void setRentDate(DateTime rentDate) {
		RentDate.set(rentDate);
	}

	public DateTime getEstimated_Return_Date() {
		return Estimated_Return_Date.get();
	}

	public void setEstimated_Return_Date(DateTime estimated_Return_Date) {
		Estimated_Return_Date.set(estimated_Return_Date);
	}

	public DateTime getActual_Return_Date() {
		return Actual_Return_Date.get();
	}

	public void setActual_Return_Date(DateTime actual_Return_Date) {
		Actual_Return_Date.set(actual_Return_Date);
	}

	public double getRentalFee() {
		return RentalFee.get();
	}

	public void setRentalFee(double rentalFee) {
		RentalFee.set(rentalFee);
	}

	public double getLate_Fee() {
		return Late_Fee.get();
	}

	public void setLate_Fee(double late_Fee) {
		Late_Fee.set(late_Fee);
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

