package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class DataSource {
	public static final String DB_NAME = "FLEXIRENT.db";

//  public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\databases\\" + DB_NAME;
	public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/RMIT/Desktop/Test/AdvancedProgramming-Assignment02/" + DB_NAME;

	 public static final String TABLE_PROPERTY = "RENTAL_PROPERTY";
	 public static final String COLUMN_PROPERTY_ID = "property_Id";
	 public static final String COLUMN_PROPERTY_TYPE = "property_type";

  private Connection conn;

  public boolean open() {
      try {
          conn = DriverManager.getConnection(CONNECTION_STRING);
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
        	  if(results.getString(COLUMN_PROPERTY_TYPE).equals("APARTMENT")){
        		  newProperty = new Apartment(results.getString(COLUMN_PROPERTY_ID),results.getString(COLUMN_PROPERTY_TYPE));
        	  }else if (results.getString(COLUMN_PROPERTY_TYPE).equals("SUIT")){
        		  newProperty = new Suit(results.getString(COLUMN_PROPERTY_ID),results.getString(COLUMN_PROPERTY_TYPE));
        	  }
        	  
//              Property artist = new Property();
//              artist.setId(results.getInt(COLUMN_ARTIST_ID));
//              artist.setName(results.getString(COLUMN_ARTIST_NAME));
              property.add(newProperty);
          }

          return property;

      } catch(SQLException e) {
          System.out.println("Query failed: " + e.getMessage());
          return null;
      }

  }
}
