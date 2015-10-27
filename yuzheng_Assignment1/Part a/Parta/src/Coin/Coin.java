package Coin;

/**
 * This class is a coin class, it describe the coin situation
 * @version  1.0
 * @author YuZheng
 * @Date 9/5/2015
 */

enum face {heads, tails};			// 2 face of coin

public class Coin {
	
	public face sideup;				// the current face of coin
	// Construction. initial the situation of coin.
	public Coin()
	{
		if(Math.random() < 0.5)
			sideup = face.tails;
		else
			sideup = face.tails;
	}
	
	// do the toss. 50% the face would become head, another 50% would be tail
	public void toss()
	{
		if(Math.random() < 0.5)
			sideup = face.tails;
		else
			sideup = face.heads;
		
	}
	
	// get the coin current face up
	public face getSideUp()
	{
		return sideup;
	}
}

