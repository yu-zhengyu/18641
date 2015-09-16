package parkingSimulator;

/*
@Author--Yi Qiu
@Date--Sep/04/2015
*/

public class ParkingTicket {
	int fine;                                                                            //Fine payment of this illegal car
	public int getFineNumber(ParkedCar legalcar, ParkingMeter legalminutes){                                                         
		int overflow = legalcar.getParkedMinutes()-legalminutes.getPurchasedMinutes();   //Time that this car has exceeded
		if(overflow<=60) fine = 25;
		else if(overflow%60==0){
			fine = 25+10*((overflow/60)-1);
		}
		else{
			fine = 25+10*(overflow/60);
		}
		return fine;
	}
	public String toString(ParkedCar illegalcar, ParkingMeter illegalminutes){
		String result = "So the illegal car maker is "+illegalcar.getMake()
		                + ", car model is "+illegalcar.getModel()
		                +", car color is "+illegalcar.getColor()
		                +", car license number is "+illegalcar.getLicenseNumber()
		                +", and the fine is "+getFineNumber(illegalcar,illegalminutes)+" $";
		return result;
	}
}
