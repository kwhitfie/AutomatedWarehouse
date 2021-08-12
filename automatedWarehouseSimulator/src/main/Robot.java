package main;

/**
 * Details here
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

import java.util.ArrayList;
	

public class Robot implements Tick{
	
	private String UID;
	private ArrayList<Position> path;
	private ChargingPod chargingPod;
	private String[] storageShelfIDs;
	private boolean hasItem;
	private boolean isBusy;
	private int batteryChargePercent;
	private static final int MAX_BATTERY = 0;

	/**
	 * 
	 */
	public Robot(String UID) {
		this.UID = UID;
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
	public String getUID() {
		return UID;
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
