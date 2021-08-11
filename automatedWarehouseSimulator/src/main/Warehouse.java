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
	private ArrayList<PackingStation> packingStations;
	private ArrayList<Robot> robots;
	private ArrayList<ChargingPod> chargingPods;
	private ArrayList<StorageShelf> storageShelves;
	
	/**
	 * 
	 */
	public Warehouse(Integer width, Integer height, Integer capacity, Integer chargeSpeed,
			ArrayList<String> podRobots, ArrayList<String> shelves, ArrayList<String> stations, ArrayList<String> orders) {
		
		System.out.println("Width: " + width);
		System.out.println("Height: " + height);
		System.out.println("Capacity: " + capacity);
		System.out.println("cCharge Speed: " + chargeSpeed);
		System.out.println("Pod Robots: " + podRobots.toString());
		System.out.println("Shelves: " + shelves.toString());
		System.out.println("Stations: " + stations.toString());
		System.out.println("Orders: " + orders.toString());
		
		// TODO Auto-generated constructor stub
		oq = new OrderQueue();
		grid = new HashMap<Position, ArrayList<String>>();
		
		//Hello Kayley, instead of the Warehouse getting a single output ArrayList, the SimulationFileReader
		//reads the sim file, and breaks it down by storing each Object type into it's own variable/collection.
		//This saves the hassle of having to loop through the arraylist. 
		
		//Please could you look to set up the grid when you have the time tomorrow using the width and heigh parameters that is passed
		//to the Warehouse. Thanks. We'll look to start creating the objects and adding them to the grid once the grid is properly set up. 
	
		
		
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
