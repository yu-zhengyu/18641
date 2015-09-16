package parkingSimulator;

/*
@Author--Yi Qiu
@Date--Sep/04/2015
*/

public class PoliceOfficer {
	//Instance variables
	private String name;     //Police officer name
	private String badge;    //Badge of this police officer

	//Constructor
	public PoliceOfficer(String name, String badge) {
		this.name = name;
		this.badge = badge;
	}

	//Setters and getters
	public String getName() {
		return name;
	}

	public String getBadge() {
		return badge;
	}

	public String toString() {
		return "The policeofficer name is " + name + ", and badge number is " + badge;
	}

	public boolean isIllegal(ParkedCar parkedcar, ParkingMeter parkingmeter) {
		if (parkedcar.getParkedMinutes() > parkingmeter.getPurchasedMinutes())
			return true;
		else
			return false;
	}

	public void issueTicket(ParkedCar parkedcar, ParkingMeter parkingmeter, ParkingTicket parkingticket) {
		boolean isillegal = isIllegal(parkedcar, parkingmeter);   //Determine whether this car is legal or not
		if (isillegal) {                                          //If illegal, print out the car information and the fine
			System.out.println("This car has used up its time !");
			System.out.println("The car has purchased "+parkingmeter.getPurchasedMinutes()+" minutes");
			System.out.println("While this car has parked "+parkedcar.getParkedMinutes()+" minutes");
			System.out.println(parkingticket.toString(parkedcar, parkingmeter));
			System.out.println(toString());

		} else {                                                 //If not, print out accordingly information.
			System.out.println("The car has purchased "+parkingmeter.getPurchasedMinutes()+" minutes");
			System.out.println("And this car has parked "+parkedcar.getParkedMinutes()+" minutes");
			System.out.println("So this car is legal");
		}
	}

}
