package main;

import java.util.ArrayList;

/**
 * Details here
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class Order {
	
	private ArrayList<String> shelfUIDs;
	private int ticksToPack;
	
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
	
	public int getTicksToPack() {
		return ticksToPack;
	}

}
