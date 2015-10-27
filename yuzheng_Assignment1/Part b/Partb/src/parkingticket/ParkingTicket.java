package parkingticket;

/**
 * This class is a parking ticket class. It can simulate whether the car has expired. 
 * 
 * @version  1.0
 * @author YuZheng
 * @Date 9/5/2015
 */ 

public class ParkingTicket {
	private double diff; 	// Compute how many minutes the car has expire
	private double plus; 	// how much the Car driver have to pay 
	private int fine; 				// The final fine
	private String result; 	// report the final fine situation
	
	public String reportFine(ParkedCar parkedcar, ParkingMeter parkingmeter,
			PoliceOfficer policeofficer)
	{	
		result = ""; // clear the former message
		if(!policeofficer.isExpired(parkedcar, parkingmeter)){
			return "The Car: " + parkedcar.getLisenceNumber() + " have not expired";
		}
		else{
			int fineminute = parkedcar.getParkingMinute() 
					- parkingmeter.getPurchaseminute();
			if(fineminute <= 60)
				fine = 25;
			else{
				diff = fineminute - 60;
				plus = Math.ceil(diff / 60);
				fine = 25 + (int)(10 * plus);
			}
			
			// The final report information
			result += "The car's time has expire, here is the message: \n" 
					+ "Make: " + parkedcar.getMake() + "\n"
					+ "Model: " + parkedcar.getModel() + "\n"
					+ "Color: " + parkedcar.getColor() + "\n"
					+ "License Number: " + parkedcar.getLisenceNumber() + "\n"
					+ "Total Fine: " + fine + "\n"
					+ "Policeofficer name: " + policeofficer.getName() + "\n"
					+ "policeofficer badge number: " + policeofficer.getBadgenumber();
		}
		
		return result;
	}
}
