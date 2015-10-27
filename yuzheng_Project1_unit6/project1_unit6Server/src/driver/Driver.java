package driver;

/**
 * @version  1.0
 * @author YuZheng
 * @Date 10/15/2015
 * 
 * Test program, test the C,U,D function
 * 
 */

import java.sql.*;

import util.DatabaseIO;
import adapter.BuildAuto;
import adapter.DataBaseAuto;

public class Driver {
	private static final String URL = "jdbc:mysql://localhost:3306";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "zhengyu19910808";

	public static void main(String args[]) {
		System.out.println("AutoMobile with Database Program");
		System.out.println("-------------------------------------");

		System.out.println("Create the DataBase with no Data");
		new DatabaseIO().createDB();
		System.out.println("-------------------------------------");

		System.out.println("Add Basic AutoMobile Information Into Database");
		DataBaseAuto auto = new BuildAuto();
		System.out.println("-------------------------------------");
		auto.creatDBAuto("FordZTW.txt");
		System.out.println("-------------------------------------");
		auto.creatDBAuto("GTR.txt");
		System.out.println("-------------------------------------");

		System.out.println("-------------Show The All Table--------");
		showAutomobileTable();
		showOptionSetTable();
		showOptionTable();
		System.out.println("\n-------------------------------------");
		
		System.out.println("Delete AutoMobile Information Into Database");
		System.out.println("-------------------------------------");
		auto.deleteDBAuto("Focus Wagon ZTW");
		System.out.println("-------------------------------------");
		System.out.println("-------------Show The All Table--------");
		showAutomobileTable();
		showOptionSetTable();
		showOptionTable();
		System.out.println("\n-------------------------------------");
		
		System.out.println("Update AutoMobile BasePrice from Database");
		System.out.println("-------------------------------------");
		auto.updateDBAutoBasicPrice("Nissan GTR", 99999);
		System.out.println("-------------------------------------");
		System.out.println("-------------Show The AutoMobile Table--------");
		showAutomobileTable();
		
	}

	public static void showAutomobileTable() {
		System.out.println("Automobile Table");
		Connection connection = null;
		Statement statement = null;
		ResultSet result;

		try {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			statement = connection.createStatement();

			statement = connection.createStatement();
			result = statement
					.executeQuery("SELECT * FROM AutoMobileConfiguration.Automobile;");

			System.out.println("AutoId | AutoName | BasePrice");
			System.out.println("-------------------------------------");
			while (result.next()) {
				System.out.println(result.getString("AutoId") + " | "
						+ result.getString("AutoName") + " | "
						+ result.getString("BasePrice"));

			}
			// close
			statement.close();
			result.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void showOptionSetTable() {
		System.out.println("OptionSet Table");
		Connection connection = null;
		Statement statement = null;
		ResultSet result;

		try {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			statement = connection.createStatement();

			statement = connection.createStatement();
			result = statement
					.executeQuery("SELECT * FROM AutoMobileConfiguration.OptionSet;");

			System.out.println("OpSetID | OpSetName | AutoId");
			System.out.println("-------------------------------------");
			while (result.next()) {
				System.out.println(result.getString("OptionSetId") + " | "
						+ result.getString("OptionSetName") + " | "
						+ result.getString("AutoId"));

			}
			// close
			statement.close();
			result.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void showOptionTable() {
		System.out.println("Option Table");
		Connection connection = null;
		Statement statement = null;
		ResultSet result;

		try {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			statement = connection.createStatement();

			statement = connection.createStatement();
			result = statement
					.executeQuery("SELECT * FROM AutoMobileConfiguration.AutoOption;");

			System.out
					.println("OptionId | OptionName | OptionPrice | OptionSetId | AutoId");
			System.out.println("-------------------------------------");
			while (result.next()) {
				System.out.println(result.getString("OptionId") + " | "
						+ result.getString("OptionName") + " | "
						+ result.getString("OptionPrice") + " | "
						+ result.getString("OptionSetId") + " | "
						+ result.getString("AutoId"));

			}
			// close
			statement.close();
			result.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}