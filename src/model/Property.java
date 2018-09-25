package model;
public abstract class Property {

		protected String property_Id;
		protected String Street_num;
		protected String Street_name;
		protected String Suburb;
		protected int Num_of_beds;
		protected String property_type;
		protected String property_status;
		protected DateTime lastMaintenanceDate;
		protected RentalRecords[] arr;

		public Property(String property_Id,String property_type){
			this.property_Id = property_Id;
			this.property_type = property_type;
		}

public Property(String property_Id, String Street_num, String Street_name, String Suburb, int Num_of_beds, String property_type, String property_status, DateTime lastMaintenanceDate)

{
	this.property_Id = property_Id;
	this.Street_num = Street_num;
	this.Street_name = Street_name;
	this.Suburb = Suburb;
	this.Num_of_beds = Num_of_beds;
	this.property_type = property_type;
	this. property_status = property_status;
	this.lastMaintenanceDate = lastMaintenanceDate;
	this.arr = new RentalRecords[10];
}
      

	public abstract boolean rent(String customerID, DateTime rentDate, int numOfRentDay);
	
	public abstract boolean Return(DateTime returnDate);
	
	public abstract boolean performanceMaintenance();
	
	public abstract boolean completeMaintenance(DateTime completionDate);
	
	public abstract String toString();
	
	public abstract String getDetails();
	
}

