package main;

/**
 * Details here
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

import java.util.ArrayList;
	

public class Robot extends WarehouseObject implements Tick{
	
	private ArrayList<Position> path;
	private ChargingPod chargingPod;
	private String[] storageShelfIDs;
	private boolean hasItem = false;
	private boolean isBusy = false;
	private int batteryChargePercent;
	private static int MAX_BATTERY;
	private Position position;

	/**
	 * 
	 */
	public Robot(String UID, int MAX_BATTERY, Position position) {
		super(UID);
		this.MAX_BATTERY = MAX_BATTERY;
		this.position = position;
		batteryChargePercent = 50;
	}
	
	public int getManhattanDistance(Position a, Position b) {
		return (a.getX()-b.getX())+(a.getY()-b.getX());
	}
	
	/**
	 * 
	 */
	public void move() {
		
	}
	
	/**
	 * 
	 * @param UID
	 * @return
	 */
	public String[] getPathToDestination(String UID) {
		return null;
	}
	
	/**
	 * 
	 */
	public void setBatteryChargePercentage(int battery) {
		batteryChargePercent = battery;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getBatteryStatus() {
		return batteryChargePercent;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean checkIfPossibleToAcceptJob() {
		//The robot will decide if it
		//wants to accept the assignment or not: this will depend on the current battery level and how far
		//the shelf and the packing station are.
		return false;
	}
	
	/**
	 * 
	 * @param StorageShelfID
	 * @return
	 */
	public boolean getItemFromShelf(int StorageShelfID) {
		return false;
	}
	
	public String toString() {
		return "Robot("+UID+")";
	}
	
	/**
	 * 
	 */
	public void tick(Warehouse wh) {
		// TODO Auto-generated method stub
		System.out.println("Robots warehouse: " + wh.toString());
	}
}
