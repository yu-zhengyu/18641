package parkingticket;

/**
 * This class is a parked car class. It describe a basic car situation
 * 
 * @version  1.0
 * @author YuZheng
 * @Date 9/5/2015
 */ 

public class ParkedCar {
	private String make;			// The make of car
	private String model;			// The car model
	private String color;			// The car Color
	private String lisenceNumber;	// The driver's lisence number	
	private int parkingMinute;		// The time the car has parked 
	
	public ParkedCar(String make, String model, String color,
			String lisencenumber, int parkingminute) {
		this.make = make;
		this.model = model;
		this.color = color;
		this.lisenceNumber = lisencenumber;
		this.parkingMinute = parkingminute;
	}

	// Getters and Setters
	public void setMake(String make) {
		this.make = make;
	}
	public String getMake() {
		return make;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	public String getModel() {
		return model;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	public String getColor() {
		return color;
	}
	
	public void setLisenceNumber(String lisenceNumber) {
		this.lisenceNumber = lisenceNumber;
	}
	public String getLisenceNumber() {
		return lisenceNumber;
	}
	
	public void setParkingMinute(int parkingMinute) {
		this.parkingMinute = parkingMinute;
	}
	public int getParkingMinute() {
		return parkingMinute;
	}
	
}
