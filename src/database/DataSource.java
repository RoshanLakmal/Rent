package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
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
	 public static final String COLUMN_PROPERTY_STATUS = "property_status";
	 public static final String COLUMN_PROPERTY_TYPE = "property_type";
	
	 public static final String CREATE_PROPERTY_TABLE = "CREATE TABLE IF NOT EXISTS RENTAL_PROPERTY" +
			 "(property_Id VARCHAR(255) not NULL, " +
             " street_num VARCHAR(255), " + 
             " street_name VARCHAR(255), " + 
             " suburb VARCHAR(255), " +
             " property_status VARCHAR(255), " +
             " property_type VARCHAR(255), " +
             " PRIMARY KEY ( property_Id ))"; 
	 
//	 public static final String CREATE_PROPERTY_TABLEE = "CREATE TABLE IF NOT EXISTS warehouses (\n"
//             + "	id integer PRIMARY KEY,\n"
//             + "	name text NOT NULL,\n"
//             + "	capacity real\n"
//             + ");";
	 
	 public static final String INSERT_PROPERTY = "INSERT INTO " + TABLE_PROPERTY +
	            '(' + COLUMN_PROPERTY_ID + ", " + COLUMN_STREET_NUM + ", " + COLUMN_STREET_NAME + ", " + COLUMN_SUBURB + ", " + COLUMN_PROPERTY_STATUS + ", " + COLUMN_PROPERTY_TYPE + ") VALUES(?, ?, ?, ?, ?, ?)";
	 
	 public static final String QUERY_PROPERTY_ID = "SELECT " + COLUMN_PROPERTY_ID + " FROM " +
			 TABLE_PROPERTY + " WHERE " + COLUMN_PROPERTY_ID + " = ?";

  private Connection conn;
  
  private PreparedStatement insertProperty;

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
      } catch(SQLException e) {
          System.out.println("Couldn't close connection: " + e.getMessage());
      }
  }
  
  public List<Property> queryArtists() {

      try(Statement statement = conn.createStatement();
          ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_PROPERTY)) {

          List<Property> property = new ArrayList<>();
          
          while(results.next()) {
        	  Property newProperty = null;
//        	  Property newProperty = new Apartment();
//    		  newProperty.setProperty_Id(results.getString(COLUMN_PROPERTY_ID));
//    		  newProperty.setProperty_type(results.getString(COLUMN_PROPERTY_TYPE));
        	  
        	  if(results.getString(COLUMN_PROPERTY_TYPE).equals("APARTMENT")){
        		  newProperty = new Apartment();
        		  newProperty.setProperty_Id(results.getString(COLUMN_PROPERTY_ID));
        		  newProperty.setProperty_type(results.getString(COLUMN_PROPERTY_TYPE));
//        		  newProperty = new Apartment(results.getString(COLUMN_PROPERTY_ID),results.getString(COLUMN_PROPERTY_TYPE));
        	  }else if (results.getString(COLUMN_PROPERTY_TYPE).equals("SUIT")){
        		  newProperty = new Suit();
        		  newProperty.setProperty_Id(results.getString(COLUMN_PROPERTY_ID));
        		  newProperty.setProperty_type(results.getString(COLUMN_PROPERTY_TYPE));  
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
  
  public int insertProperty(String propertyId, String streetNum, String streetName, String suburb, String propertyStatus, String propertyType) throws SQLException {

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
	  insertProperty.setString(5, propertyStatus);
	  insertProperty.setString(6, propertyType);
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
}
