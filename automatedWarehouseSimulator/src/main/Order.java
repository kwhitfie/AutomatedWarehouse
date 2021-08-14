package main;

import java.util.ArrayList;

/**
 * Details here
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class Order implements Tick{
	
	public ArrayList<String> shelfUIDs;
	public int ticksToPack;
	
	/**
	 * 
	 */
	public Order(ArrayList<String> shelves, int ticks) {
		shelfUIDs = shelves;
		ticksToPack = ticks;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getShelfUIDs() {
		return shelfUIDs;
	}

	/**
	 * 
	 */
	@Override
	public void Tick() {
		// TODO Auto-generated method stub
		
	}
	
	public int getTicksToPack() {
		return ticksToPack;
	}

}
