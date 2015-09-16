package parkingticket;

/**
 * This is a test file. Test all situation of the car. 
 * 
 * @version  1.0
 * @author YuZheng
 * @Date 9/5/2015
 */ 

public class test {

	public static void main(String[] args) {
		
		// Case1, when the ParkedVehicle is with in the parking time purchased
		String fineReport;
		ParkedCar parkedCarIn = new ParkedCar("China", "BMW", "Red", "PAM205", 50);
		ParkingMeter parkingMeter = new ParkingMeter(60);
		PoliceOfficer policeOffice = new PoliceOfficer("Joey", "MP95213");
		
		// Case2 information, when the ParkedVehicle just in the parking time purchased
		ParkedCar parkedCarIn2 = new ParkedCar("USA", "BENZ", "Black", "PDH213", 60);
		ParkingMeter parkingMeter2 = new ParkingMeter(60);
		PoliceOfficer policeOffice2 = new PoliceOfficer("Matt", "HB24213");
		
		//Case3 information, when the ParkedVehicle out of the parking time purchased
		ParkedCar parkedCarIn3 = new ParkedCar("AUS", "BLUCK", "White", "234FG", 70);
		ParkingMeter parkingMeter3 = new ParkingMeter(60);
		PoliceOfficer policeOffice3 = new PoliceOfficer("Herry", "PO3913");
		
		//Case4 information, when the Ticketing under 1 hour
		ParkedCar parkedCarIn4 = new ParkedCar("JPN", "NISSANA", "Pink", "21EFS", 20);
		ParkingMeter parkingMeter4 = new ParkingMeter(0);
		PoliceOfficer policeOffice4 = new PoliceOfficer("Pet", "IO3432");
		
		//Case4 information, when the Ticketing more than 1 hour
		ParkedCar parkedCarIn5 = new ParkedCar("GER", "LOS", "Green", "RF3131", 70);
		ParkingMeter parkingMeter5 = new ParkingMeter(0);
		PoliceOfficer policeOffice5 = new PoliceOfficer("Joe", "UI2134");
		
		ParkingTicket parkingTicket = new ParkingTicket(); 	// ParkingTicket, run different case
		
		// run Case 1 
		System.out.println("The Case 1: ");
		fineReport = parkingTicket.reportFine(parkedCarIn, parkingMeter, policeOffice);
		System.out.println(fineReport);
		
		// run Case 2
		System.out.println("\nThe Case 2: ");
		fineReport = parkingTicket.reportFine(parkedCarIn2, parkingMeter2, policeOffice2);
		System.out.println(fineReport);
		
		// run Case 3
		System.out.println("\nThe Case 3: ");
		fineReport = parkingTicket.reportFine(parkedCarIn3, parkingMeter3, policeOffice3);
		System.out.println(fineReport);
		
		// run Case 4
		System.out.println("\nThe Case 4: ");
		fineReport = parkingTicket.reportFine(parkedCarIn4, parkingMeter4, policeOffice4);
		System.out.println(fineReport);
		
		// run Case 5
		System.out.println("\nThe Case 5: ");
		fineReport = parkingTicket.reportFine(parkedCarIn5, parkingMeter5, policeOffice5);
		System.out.println(fineReport);
		
		// if you want to test you can use the below code segment
		// The below variables you can change by yourself.
		parkedCarIn.setMake("11"); 
		parkedCarIn.setColor("color");
		parkedCarIn.setLisenceNumber("ff22");
		parkedCarIn.setModel("BMW");
		parkedCarIn.setParkingMinute(60);
		parkingMeter.setPurchaseminute(20);
		policeOffice.setName("GERO");
		policeOffice.setBadgenumber("2141223");
		System.out.println("\nYour test Case : ");
		fineReport = parkingTicket.reportFine(parkedCarIn, parkingMeter, policeOffice);
		System.out.println(fineReport);
		
	}

}
