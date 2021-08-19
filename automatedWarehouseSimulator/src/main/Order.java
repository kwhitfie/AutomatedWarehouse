package main;

import java.util.ArrayList;

/**
 * This class  details the fields and method of an Order.
 * 
 * An individual order contains an integer of how many ticks it takes 
 * for a PackingStation object to pack this order, and a list of
 * StorageShelf object UIDs to let a Robot object know which StorageShelf(s)
 * it needs to travel to get the items a PackingStation needs to 
 * pack this order.  
 *
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class Order {
	
	private ArrayList<String> shelfUIDs;
	private int ticksToPack;
	
	/**
	 * Order object constructor.
	 * @param shelves - a list of StorageShelf UIDs
	 * @param ticks - the amount of ticks to pack this order
	 */
	public Order(ArrayList<String> shelves, int ticks) {
		shelfUIDs = shelves;
		ticksToPack = ticks;
	}
	
	/**
	 * Return the list of StorageShelf UIDs.
	 * @return - shelfUID variable.
	 */
	public ArrayList<String> getShelfUIDs() {
		return shelfUIDs;
	}
	
	/**
	 * Return the integer representation of how many
	 * ticks it takes to pack this order.
	 * @return - ticksToPack variable.
	 */
	public int getTicksToPack() {
		return ticksToPack;
	}

}
