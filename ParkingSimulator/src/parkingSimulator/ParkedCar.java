package parkingSimulator;
/*
@Author--Yi Qiu
@Date--Sep/04/2015
*/

public class ParkedCar {
	//Instance variables
	private String make;           //Car maker
	private String model;          //Car model
	private String color;          //Color of car
	private String licenseNumber;  //Car license number
	private int parkedMinutes;     //Time that this car has parked
	
	
	//Setters and getters
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public int getParkedMinutes() {
		return parkedMinutes;
	}
	public void setParkedMinutes(int parkedMinutes) {
		this.parkedMinutes = parkedMinutes;
	}
}
