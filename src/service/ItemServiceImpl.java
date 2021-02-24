package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;

import util.ConnectDB;

public class ItemServiceImpl implements itemService{
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static Statement statement;
	
	/*
	 * Method implementation for Insert Item
	 * Follow itemService.java for the method description
	 * 
	 * */
	@Override
	public String insertItem(String code, String name, String price, String desc) {
		String output;
		try {
			connection = ConnectDB.getDBConnection();
			
			//Check whether properly connected or not
			if (connection == null) {
				logger.log(Level.SEVERE, "Connection Error");
			}
			
			//Create Prepared Statement
			String sql = "INSERT INTO Item(itemCode, name, price, description) VALUES(?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			
			//Bind Values
			preparedStatement.setString(1, code);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, price);
			preparedStatement.setString(4, desc);
			
			//execute the statement
			preparedStatement.execute();
			connection.close();
			output = "Inserted successfully"; 
			
		} catch (Exception e) {
			output = "Error while inserting"; 
			logger.log(Level.SEVERE, e.getMessage());
		}
		
		return output;
	}

}
