package service;

import java.util.logging.Logger;

/* 
 * @author W.G. YASIRU RANDIKA 
 * IT19131184
 * 
 * */

public interface itemService {
	// Initializing logger
	public static final Logger logger = Logger.getLogger(itemService.class.getName());
	
	/**
	 * InsertItem
	 * 
	 * This method will insert an item to the mysql database
	 * 
	 * @return string 
	 * @param code String
	 * @param name String
	 * @param price double
	 * @param desc String
	 */
	public String insertItem(String code, String name, double price, String desc);

}
