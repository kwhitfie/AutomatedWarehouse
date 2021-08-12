package main;

import java.util.ArrayList;

/**
 * Details here
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class Order implements Tick{
	
	public ArrayList<String> shelfUID;
	
	/**
	 * 
	 */
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getShelfUIDs() {
		return shelfUID;
	}

	/**
	 * 
	 */
	@Override
	public void Tick() {
		// TODO Auto-generated method stub
		
	}

}
