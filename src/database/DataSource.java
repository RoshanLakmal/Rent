package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import model.*;

public class DataSource {
	public static final String DB_NAME = "FLEXIRENT.db";

//  public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\databases\\" + DB_NAME;
	public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/RMIT/Desktop/Test/AdvancedProgramming-Assignment02/" + DB_NAME;

	 public static final String TABLE_PROPERTY = "RENTAL_PROPERTY";
	 public static final String COLUMN_PROPERTY_ID = "property_Id";
	 public static final String COLUMN_STREET_NUM = "street_num";
	 public static final String COLUMN_STREET_NAME = "street_name";
	 public static final String COLUMN_SUBURB = "suburb";
	 public static final String COLUMN_NUM_OF_BEDS = "num_of_beds";
	 public static final String COLUMN_PROPERTY_STATUS = "property_status";
	 public static final String COLUMN_PROPERTY_TYPE = "property_type";
	 public static final String COLUMN_LASTMAINTENANCEDATE = "lastMaintenanceDate";
	 
	 public static final String TABLE_RENTAL_RECORD = "RENTAL_RECORD";
//	 public static final String COLUMN_RENTAL_RECORD_PROPERTY_ID = "property_Id_record_Id";
	 public static final String COLUMN_RECORD_ID = "Record_Id";
	 public static final String COLUMN_CUSTOMER_ID = "Customer_Id";
	 public static final String COLUMN_RENTDATE= "RentDate";
	 public static final String COLUMN_ESTIMATED_RETURN_DATE = "Estimated_Return_Date";
	 public static final String COLUMN_ACTUAL_RETURN_DATE = "Actual_Return_Date";
	 public static final String COLUMN_RENTALFEE = "RentalFee";
	 public static final String COLUMN_LATE_FEE = "Late_Fee";
	
	 public static final String CREATE_PROPERTY_TABLE = "CREATE TABLE IF NOT EXISTS RENTAL_PROPERTY" +
			 "(property_Id VARCHAR(255) not NULL, " +
             " street_num VARCHAR(255), " + 
             " street_name VARCHAR(255), " + 
             " suburb VARCHAR(255), " +
             " num_of_beds INT, " +
             " property_status VARCHAR(255), " +
             " property_type VARCHAR(255), " +
             " lastMaintenanceDate VARCHAR(255), " +
             " PRIMARY KEY ( property_Id ))"; 
	 
	 public static final String CREATE_RENTAL_RECORD_TABLE = "CREATE TABLE IF NOT EXISTS RENTAL_RECORD" +
			 "(Record_Id VARCHAR(255) not NULL, " +
             " property_Id VARCHAR(255), " + 
             " Customer_Id VARCHAR(255), " + 
             " RentDate VARCHAR(255), " +
             " Estimated_Return_Date VARCHAR(255), " +
             " Actual_Return_Date VARCHAR(255), " +
             " RentalFee DOUBLE, " +
             " Late_Fee DOUBLE, " +
             " PRIMARY KEY ( Record_Id ), FOREIGN KEY (property_Id))"; 
	 
//	 public static final String CREATE_PROPERTY_TABLEE = "CREATE TABLE IF NOT EXISTS warehouses (\n"
//             + "	id integer PRIMARY KEY,\n"
//             + "	name text NOT NULL,\n"
//             + "	capacity real\n"
//             + ");";
	 
	 public static final String INSERT_PROPERTY = "INSERT INTO " + TABLE_PROPERTY +
	            '(' + COLUMN_PROPERTY_ID + ", "
	            	+ COLUMN_STREET_NUM + ", " 
	            	+ COLUMN_STREET_NAME + ", " 
	            	+ COLUMN_SUBURB + ", "
	            	+ COLUMN_NUM_OF_BEDS + ", " 
	            	+ COLUMN_PROPERTY_STATUS + ", " 
	            	+ COLUMN_PROPERTY_TYPE + ", "
	            	+ COLUMN_LASTMAINTENANCEDATE +") VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	 
	 public static final String INSERT_RENTAL_RECORD = "INSERT INTO " + TABLE_RENTAL_RECORD +
	            '(' + COLUMN_PROPERTY_ID + ", "
	            	+ COLUMN_RECORD_ID + ", " 
	            	+ COLUMN_CUSTOMER_ID + ", " 
	            	+ COLUMN_RENTDATE + ", " 
	            	+ COLUMN_ESTIMATED_RETURN_DATE + ", "
	            	+ COLUMN_ACTUAL_RETURN_DATE + ", " 
	            	+ COLUMN_RENTALFEE + ", " 
	            	+ COLUMN_LATE_FEE +") VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	 
	 public static final String QUERY_PROPERTY_ID = "SELECT " + COLUMN_PROPERTY_ID + " FROM " +
			 TABLE_PROPERTY + " WHERE " + COLUMN_PROPERTY_ID + " = ?";
	 
	 public static final String QUERY_RECORD_ID = "SELECT " + COLUMN_RECORD_ID + " FROM " +
			 TABLE_RENTAL_RECORD + " WHERE " + COLUMN_RECORD_ID + " = ?";

  private Connection conn;
  
  private PreparedStatement insertProperty;
  private PreparedStatement insertRentalRecord;

  private static DataSource instance = new DataSource();
  
  public static DataSource getInstance() {
      return instance;
  }
  
  public boolean open() {
      try {
          conn = DriverManager.getConnection(CONNECTION_STRING);
          Statement statement = conn.createStatement();
          statement.executeUpdate(CREATE_PROPERTY_TABLE); 
          insertProperty = conn.prepareStatement(INSERT_PROPERTY, Statement.RETURN_GENERATED_KEYS);
          insertRentalRecord = conn.prepareStatement(INSERT_RENTAL_RECORD, Statement.RETURN_GENERATED_KEYS);
          return true;
      } catch(SQLException e) {
          System.out.println("Couldn't connect to database: " + e.getMessage());
          return false;
      }
  }

  public void close() {
      try {
          if(conn != null) {
              conn.close();
          }
          
          if(insertProperty != null) {
        	  insertProperty.close();
          }
          
          if(insertRentalRecord != null) {
        	  insertRentalRecord.close();
          }
      } catch(SQLException e) {
          System.out.println("Couldn't close connection: " + e.getMessage());
      }
  }
  
  public List<Property> queryArtists() {

//	  DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
//	  String pattern = "yyyy-MM-dd";
//	  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	  
	  
	  
      try(Statement statement = conn.createStatement();
          ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_PROPERTY)) {

          List<Property> property = new ArrayList<>();
          
//          DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//    	  DateTime dt = formatter.parseDateTime("dfs");
          
          while(results.next()) {
        	  Property newProperty = null;
//        	  Property newProperty = new Apartment();
//    		  newProperty.setProperty_Id(results.getString(COLUMN_PROPERTY_ID));
//    		  newProperty.setProperty_type(results.getString(COLUMN_PROPERTY_TYPE));
        	  
        	  if(results.getString(COLUMN_PROPERTY_TYPE).equals("APARTMENT")){
        		  newProperty = new Apartment();
        		  newProperty.setProperty_Id(results.getString(COLUMN_PROPERTY_ID));
        		  newProperty.setStreet_num(results.getString(COLUMN_STREET_NUM));
        		  newProperty.setStreet_name(results.getString(COLUMN_STREET_NAME));
        		  newProperty.setSuburb(results.getString(COLUMN_SUBURB));
        		  newProperty.setNum_of_beds(results.getInt(COLUMN_NUM_OF_BEDS));
        		  newProperty.setProperty_status(results.getString(COLUMN_PROPERTY_STATUS));
        		  newProperty.setProperty_type(results.getString(COLUMN_PROPERTY_TYPE));
        		  newProperty.setLastMaintenanceDate(stringToDateTime(results.getString(COLUMN_LASTMAINTENANCEDATE)));
//        		  newProperty = new Apartment(results.getString(COLUMN_PROPERTY_ID),results.getString(COLUMN_PROPERTY_TYPE));
        	  }else if (results.getString(COLUMN_PROPERTY_TYPE).equals("SUIT")){
        		  newProperty = new Suit();
        		  newProperty.setProperty_Id(results.getString(COLUMN_PROPERTY_ID));
        		  newProperty.setStreet_num(results.getString(COLUMN_STREET_NUM));
        		  newProperty.setStreet_name(results.getString(COLUMN_STREET_NAME));
        		  newProperty.setSuburb(results.getString(COLUMN_SUBURB));
        		  newProperty.setNum_of_beds(results.getInt(COLUMN_NUM_OF_BEDS));
        		  newProperty.setProperty_status(results.getString(COLUMN_PROPERTY_STATUS));
        		  newProperty.setProperty_type(results.getString(COLUMN_PROPERTY_TYPE));  
        		  newProperty.setLastMaintenanceDate(stringToDateTime(results.getString(COLUMN_LASTMAINTENANCEDATE)));
        	  }
//        	  
//              Property artist = new Property();
//              artist.setId(results.getInt(COLUMN_ARTIST_ID));
//              artist.setName(results.getString(COLUMN_ARTIST_NAME));
              property.add(newProperty);
          }
          
//          for(int i=0;i<property.size();i++){
//        	 System.out.println(property.get(i).getClass()); 
//          }

          return property;

      } catch(SQLException e) {
          System.out.println("Query failed: " + e.getMessage());
          return null;
      }

  }
  
  private DateTime stringToDateTime(String sDateTime){
	  String string = sDateTime;
	  String[] parts = string.split("/");
	  int day = Integer.parseInt(parts[0]);
	  int month = Integer.parseInt(parts[1]);
	  int year = Integer.parseInt(parts[2]);
	  
	  DateTime t = new DateTime(day,month,year);
	  return t;
  }
  
  public int insertProperty(String propertyId, String streetNum, String streetName, String suburb, int numOfBeds, String propertyStatus, String propertyType, DateTime lastMaintenanceDate) throws SQLException {

//      queryAlbum.setString(1, name);
//      ResultSet results = queryAlbum.executeQuery();
//      if(results.next()) {
//          return results.getInt(1);
//      } else {
          // Insert the album
	  insertProperty.setString(1, propertyId);
	  insertProperty.setString(2, streetNum);
	  insertProperty.setString(3, streetName);
	  insertProperty.setString(4, suburb);
	  insertProperty.setInt(5, numOfBeds);
	  insertProperty.setString(6, propertyStatus);
	  insertProperty.setString(7, propertyType);
	  insertProperty.setString(8, lastMaintenanceDate.getFormattedDate());
          int affectedRows = insertProperty.executeUpdate();

          if(affectedRows != 1) {
              throw new SQLException("Couldn't insert album!");
          }

          ResultSet generatedKeys = insertProperty.getGeneratedKeys();
          if(generatedKeys.next()) {
              return generatedKeys.getInt(1);
          } else {
              throw new SQLException("Couldn't get _id for album");
          }
//      }
  }
  
  public int insertRentalRecord(String propertyId, String streetNum, String streetName, String suburb, int numOfBeds, String propertyStatus, String propertyType, DateTime lastMaintenanceDate) throws SQLException {

//    queryAlbum.setString(1, name);
//    ResultSet results = queryAlbum.executeQuery();
//    if(results.next()) {
//        return results.getInt(1);
//    } else {
        // Insert the album
	  insertRentalRecord.setString(1, propertyId);
	  insertRentalRecord.setString(2, streetNum);
	  insertRentalRecord.setString(3, streetName);
	  insertRentalRecord.setString(4, suburb);
	  insertRentalRecord.setInt(5, numOfBeds);
	  insertRentalRecord.setString(6, propertyStatus);
	  insertRentalRecord.setString(7, propertyType);
	  insertRentalRecord.setString(8, lastMaintenanceDate.getFormattedDate());
        int affectedRows = insertProperty.executeUpdate();

        if(affectedRows != 1) {
            throw new SQLException("Couldn't insert album!");
        }

        ResultSet generatedKeys = insertProperty.getGeneratedKeys();
        if(generatedKeys.next()) {
            return generatedKeys.getInt(1);
        } else {
            throw new SQLException("Couldn't get _id for album");
        }
//    }
}
}
