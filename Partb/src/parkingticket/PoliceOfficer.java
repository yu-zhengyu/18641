package parkingticket;

/**
 * This class is a police officer class, it describe a basic situation of police officer.
 * 
 * @version  1.0
 * @author YuZheng
 * @Date 9/5/2015
 */ 

public class PoliceOfficer {
	private String name;			// police officer name
	private String badgenumber;		// police officer badge number
	
	public PoliceOfficer(String name, String badgenumber) {
		this.name = name;
		this.badgenumber = badgenumber;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	public void setBadgenumber(String badgenumber){
		this.badgenumber = badgenumber;
	}
	public String getBadgenumber(){
		return badgenumber;
	}
	
	// Determine whether the car's time has expire
	public boolean isExpired(ParkedCar parkedCar, ParkingMeter parkingMeter){
		int parkedminute = parkedCar.getParkingMinute();
		int purchaseminute = parkingMeter.getPurchaseminute();
		if(parkedminute - purchaseminute > 0)
			return true;
		else
			return false;
	}
}
