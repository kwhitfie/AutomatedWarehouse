package main;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Details here
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class Warehouse implements Tick{
	
	private OrderQueue oq;
	private HashMap<Position, ArrayList<String>> grid;
	
	/**
	 * 
	 */
	public Warehouse() {
		// TODO Auto-generated constructor stub
		oq = new OrderQueue();
		grid = new HashMap<Position, ArrayList<String>>();
	}
	
	/**
	 * 
	 */
	@Override
	public void Tick() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * 
	 * @return
	 */
	public OrderQueue getOrderQueue() {
		return oq;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean crashMonitor() {
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public Position getGridSquare() {
		return null;
	}
	
	/**
	 * 
	 * @param UID
	 */
	public void setGridSquare(String UID) {
		
	}
	
	/**
	 * 
	 */
	public void tickAll() {
		//tick tick tick tick tick tick tick tick tick tick tick 
	}
}
