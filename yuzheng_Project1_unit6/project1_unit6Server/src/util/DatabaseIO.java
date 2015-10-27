package util;

/**
 * @version 2.0
 * @author YuZheng
 * @Date 10/15/2015
 * 
 * This class includes all function which interact with DataBase
 * Before you run this function, please make sure your database is started
 * and input the user name and password according to your data base.
 * also, please make sure your database does not has database 
 * called AutoMobileConfiguration
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Automobile;

public class DatabaseIO {
	private static final String URL = "jdbc:mysql://localhost:3306";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "zhengyu19910808";
	private Connection connection = null;

	/**
	 * Connect to the database
	 */
	public void connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			System.out.println("Success connect Mysql server!");

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Crate the Database AutoMobileConfiguration and create the related table
	 */
	public void createDB() {
		Statement statement = null;
		try {

			connectDB();
			statement = connection.createStatement();

			try {
				statement
						.executeUpdate("DROP DATABASE AutoMobileConfiguration");
			} catch (Exception e) {

			}

			FileReader file = null;
			try {
				file = new FileReader("createDB.txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else {
					statement.executeUpdate(line);
				}

			}
			buff.close();
			statement.close();
			System.out.println("Create DATABASE is done!");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public int[] addAutoMobileToDB(Automobile auto, int autoID, int optSetID,
			int optionID) {
		Statement statement = null;

		// 1 is add automobile; 2 is add option set, 3 is add option
		String addcomment[] = new String[3];
		String[] optionsetName = { "Color", "Transmission",
				"Brakes/Traction Control", "Side Impact Airbags",
				"Power Moonroof" };

		int count = 0;
		try {

			connectDB();
			statement = connection.createStatement();

			FileReader file = null;
			try {
				file = new FileReader("addDB.txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else {
					addcomment[count++] = line;
					// statement.executeUpdate(line);
				}

			}

			// execute the comment line
			// add Automobile into database
			PreparedStatement prepare = connection
					.prepareStatement(addcomment[0]);
			prepare.setInt(1, autoID);
			prepare.setString(2, auto.getName());
			prepare.setFloat(3, auto.getBaseprice());
			prepare.executeUpdate();

			// add the optionset and option name into the database;
			for (int i = 0; i < optionsetName.length; i++) {
				prepare = connection.prepareStatement(addcomment[1]);
				prepare.setInt(1, optSetID);
				prepare.setString(2, optionsetName[i]);
				prepare.setInt(3, autoID);
				prepare.executeUpdate();

				// add option into the database;
				String[] optionnameArray = auto.getOptionName(optionsetName[i]);
				for (int j = 0; j < optionnameArray.length; j++) {
					prepare = connection.prepareStatement(addcomment[2]);
					prepare.setInt(1, optionID++);
					prepare.setString(2, optionnameArray[j]);
					prepare.setFloat(3, auto.getOptionPrice(optionsetName[i],
							optionnameArray[j]));
					prepare.setInt(4, optSetID);
					prepare.setInt(5, autoID);
					prepare.executeUpdate();
				}
				optSetID++;

			}
			System.out.println(auto.getName()
					+ " successfully was added into the database");

			buff.close();
			statement.close();
			System.out.println("Add data into DATABASE is done!");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		int[] returennum = { optSetID, optionID };
		return returennum;
	}

	public void deleteAutoFromDB(String autoname) {
		Statement statement = null;

		// 1. get the ID of AutoMobile
		// 2. Delete the related option from Autooption Table
		// 3. Delete the related option set from optionset Table
		// 4. Delete the AutoMobile from Automobile Table	
		String addcomment[] = new String[4];

		int count = 0;
		try {

			connectDB();
			statement = connection.createStatement();

			FileReader file = null;
			try {
				file = new FileReader("deleteDB.txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else {
					addcomment[count++] = line;
					// statement.executeUpdate(line);
				}

			}
			
			// Get the ID of Automobile form AutoMobile Table
			PreparedStatement prepare = connection
					.prepareStatement(addcomment[0]);
			prepare.setString(1, autoname);
			ResultSet result = prepare.executeQuery();
			result.next();
			int AutoId = Integer.parseInt(result.getString("AutoID"));
			
			// delete the related option from option table
			prepare = connection.prepareStatement(addcomment[1]);
			prepare.setInt(1, AutoId);
			prepare.executeUpdate();
			
			// delete the related option set from option set table
			prepare = connection.prepareStatement(addcomment[2]);
			prepare.setInt(1, AutoId);
			prepare.executeUpdate();
			
			// delete the Automobile from Automobile table
			prepare = connection.prepareStatement(addcomment[3]);
			prepare.setInt(1, AutoId);
			prepare.executeUpdate();
			
			result.close();
			buff.close();
			statement.close();
			System.out.println("Delete the " + autoname + " from DATABASE is done!");
			// execute the comment line
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void updateAutoDB(String autoname, float price) {
		Statement statement = null;

		// Update the AutoMobile BasePrice
		String addcomment = null;

		int count = 0;
		try {

			connectDB();
			statement = connection.createStatement();

			FileReader file = null;
			try {
				file = new FileReader("updateoptionDB.txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			while (!eof) {
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else {
					addcomment = line;
					// statement.executeUpdate(line);
				}

			}
			
			PreparedStatement prepare = connection.prepareStatement(addcomment);
			prepare.setFloat(1, price);
			prepare.setString(2, autoname);
			prepare.executeUpdate();
			
			prepare.close();
			buff.close();
			statement.close();
			System.out.println("Update data from DATABASE is done!");
			// execute the comment line
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}