package Coin;

/**
 * This class is a simulation class, it would simulate the tossing coin 20 times and record
 * the situation
 * 
 * @version  1.0
 * @author YuZheng
 * @Date 9/5/2015
 */ 

public class Simulation {
	private int heads_num;
	private int tails_num;
	private String track;
	
	public int getHeads_num() {
		return heads_num;
	}

	public void setHeads_num(int heads_num) {
		this.heads_num = heads_num;
	}

	public int getTails_num() {
		return tails_num;
	}

	public void setTails_num(int tails_num) {
		this.tails_num = tails_num;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	
	// simulate the case, toss the coin 20 times
	public void run(Coin test_coin) {
		track = "";		// record the face up situation each time
		heads_num = 0;	// the number of heads
		tails_num = 0;	// the number of tail
		track = "The initial facing up is: " + test_coin.getSideUp() + "\n";
		for (int i = 0; i < 20; i++) {
			test_coin.toss();
			if (test_coin.getSideUp() == face.heads)
				heads_num++;
			else
				tails_num++;
			track += "The " + (i + 1) + " times facing up is: " + test_coin.getSideUp() + "\n";
		}
	}
	
	// get the face up situation each time
	public String gettrack(){
		return track;
	}
}
