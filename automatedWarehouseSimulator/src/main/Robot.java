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
	private boolean hasItem;
	private boolean isBusy;
	private int batteryChargePercent;
	private static int MAX_BATTERY;

	/**
	 * 
	 */
	public Robot(String UID, int MAX_BATTERY) {
		super(UID);
		this.MAX_BATTERY = MAX_BATTERY;
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
	 * @return
	 */
	public int checkBatteryStatus() {
		return 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean checkIfPossibleToAcceptJob() {
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
	@Override
	public void Tick() {
		// TODO Auto-generated method stub
		
	}
}
