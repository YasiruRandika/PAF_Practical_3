package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import model.Item;
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
			preparedStatement.setFloat(3, Float.parseFloat(price));
			preparedStatement.setString(4, desc);
			
			//execute the statement
			preparedStatement.execute();
			connection.close();
			output = "Inserted successfully"; 
			
		} catch (Exception e) {
			output = "Error while inserting"; 
			logger.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close statement and database connectivity at the end of transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (java.sql.SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
		
		return output;
	}

	/*
	 * Method implementation for Get Items
	 * Follow itemService.java for the method description
	 * 
	 * */
	@Override
	public ArrayList<Item> getItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
			connection = ConnectDB.getDBConnection();
			
			//Check whether properly connected or not
			if (connection == null) {
				logger.log(Level.SEVERE, "Connection Error");
			}
			
			//Create Statement
			String sql = "SELECT * FROM Item";
			
			//execute the statement
			ResultSet resultSet = statement.executeQuery(sql);
			connection.close();
			
			while (resultSet.next()) {
				Item item  = new Item();
				item.setItemID(resultSet.getInt(0));
				item.setItemCode(resultSet.getString(1));
				item.setItemName(resultSet.getString(2));
				item.setItemPrice(resultSet.getFloat(3));
				
				items.add(item);
			}
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close statement and database connectivity at the end of transaction
			 */
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (java.sql.SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
		
		return items;
	}

	/*
	 * Method implementation for Delete an Item
	 * Follow itemService.java for the method description
	 * 
	 * */
	@Override
	public String deleteItem(int id) {
		String output;
		try {
			connection = ConnectDB.getDBConnection();
			
			//Check whether properly connected or not
			if (connection == null) {
				logger.log(Level.SEVERE, "Connection Error");
			}
			
			//Create Prepared Statement
			String sql = "DELETE FROM Item WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			//Bind Values
			preparedStatement.setInt(1, id);
			
			//execute the statement
			preparedStatement.execute();
			connection.close();
			output = "Deleted successfully"; 
			
		} catch (Exception e) {
			output = "Error while Deleting"; 
			logger.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close statement and database connectivity at the end of transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (java.sql.SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
		return output;
	}

	/*
	 * Method implementation for Update an Item
	 * Follow itemService.java for the method description
	 * 
	 * */
	@Override
	public String updateItem(int id, String code, String name, String price, String desc) {
		String output;
		try {
			connection = ConnectDB.getDBConnection();
			
			//Check whether properly connected or not
			if (connection == null) {
				logger.log(Level.SEVERE, "Connection Error");
			}
			
			//Create Prepared Statement
			String sql = "UPDATE Item SET itemCode = ?, name = ?, price = ?, description = ? WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			
			//Bind Values
			preparedStatement.setString(1, code);
			preparedStatement.setString(2, name);
			preparedStatement.setFloat(3, Float.parseFloat(price));
			preparedStatement.setString(4, desc);
			preparedStatement.setInt(5, id);
			
			//execute the statement
			preparedStatement.execute();
			connection.close();
			output = "Updated successfully"; 
			
		} catch (Exception e) {
			output = "Error while Updating"; 
			logger.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close statement and database connectivity at the end of transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (java.sql.SQLException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
		return null;
	}

}
