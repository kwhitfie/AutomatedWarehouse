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

	public Robot() {
		// TODO Auto-generated constructor stub
	}
	
	public void move() {
		
	}
	
	public String[] getPathToDestination(String UID) {
		return null;
	}

	public String getUID() {
		return UID;
	}
	
	public int checkBatteryStatus() {
		return 0;
	}
	
	public boolean checkIfPossibleToAcceptJob() {
		return false;
	}
	
	public boolean getItemFromShelf(int StorageShelfID) {
		return false;
	}
	

	@Override
	public void Tick() {
		// TODO Auto-generated method stub
		
	}
}
