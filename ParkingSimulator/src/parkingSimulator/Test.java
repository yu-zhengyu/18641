package parkingSimulator;

/*
@Author--Yi Qiu
@Date--Sep/04/2015
*/

public class Test {
	public static void main(String[] args){
		ParkingMeter parkingmeter = new ParkingMeter();
		ParkedCar mycar = new ParkedCar();
		PoliceOfficer police = new PoliceOfficer("John", "5901");//If you want to try another name and badge number, modify here.
		ParkingTicket parkingticket = new ParkingTicket();
		parkingmeter.setPurchasedMinutes(100);                   //If you want to try another purchased time, modify here.
		mycar.setMake("Toyota");                                 //If you want to try another car make, modify here.
		mycar.setModel("GTR");                                   //If you want to try another car model, modify here.
		mycar.setColor("red");                                   //If you want to try another car color, modify here.
		mycar.setLicenseNumber("PHD-ING");                       //If you want to try another car license number, modify here.
		mycar.setParkedMinutes(180);                             //If you want to try another parked time, modify here.
		police.issueTicket(mycar, parkingmeter, parkingticket);  //Determine this car will get a fine or not.
		
	}
}
