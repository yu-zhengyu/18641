package parkingticket;

/**
 * This class is a parking meeter class. It describe how much time the driver bought 
 * 
 * @version  1.0
 * @author YuZheng
 * @Date 9/5/2015
 */ 


public class ParkingMeter {
	private int purchaseMinute;		// The time driver has bought
	
	public ParkingMeter(int purchaseMinute) {
		this.purchaseMinute = purchaseMinute;
	}
	public void setPurchaseminute(int purchaseMinute){
		this.purchaseMinute = purchaseMinute;
	}
	public int getPurchaseminute(){
		return purchaseMinute;
	}
}
