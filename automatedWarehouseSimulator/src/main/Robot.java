package main;

/**
 * Details here
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
	

public class Robot extends WarehouseObject implements Tick{
	
	//private ArrayList<Position> path;
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
	public void move(Position destination, Warehouse wh) {
		
		Position up = new Position(position.getX(),(position.getY()-1));
		Position down = new Position(position.getX(),(position.getY()+1));
		Position left = new Position(position.getX()-1,(position.getY()));
		Position right = new Position(position.getX()+1,(position.getY()));
		
		int upDistance = 0;
		int downDistance = 0;
		int leftDistance = 0;
		int rightDistance = 0;
		
		if(!doesSquareHaveRobot(up,wh) || 0 > up.getX() || up.getX() > wh.getX() || 0 > up.getY() || up.getY() > wh.getY())
		{
			upDistance = getManhattanDistance(up,destination);
		}
		if(!doesSquareHaveRobot(down,wh) || 0 > down.getX() || down.getX() > wh.getX() || 0 > down.getY() || down.getY() > wh.getY())
		{
			downDistance = getManhattanDistance(down,destination);
		}
		if(!doesSquareHaveRobot(left,wh) || 0 > left.getX() || left.getX() > wh.getX() || 0 > left.getY() || left.getY() > wh.getY())
		{
			leftDistance = getManhattanDistance(left,destination);
		}
		if(!doesSquareHaveRobot(right,wh) || 0 > right.getX() || right.getX() > wh.getX() || 0 > right.getY() || right.getY() > wh.getY())
		{
			rightDistance = getManhattanDistance(right,destination);
		}
		
		upDistance = getManhattanDistance(up,destination);
		downDistance = getManhattanDistance(down,destination);
		leftDistance = getManhattanDistance(left,destination);
		rightDistance = getManhattanDistance(right,destination);
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		HashMap<Position,Integer> map = new HashMap<Position,Integer>();
		
		if(upDistance!=0)
		{
			list.add(upDistance);
			map.put(up, upDistance);
		}
		
		if(downDistance!=0)
		{
			list.add(downDistance);
			map.put(down, downDistance);
		}
		if(upDistance!=0)
		{
			list.add(leftDistance);
			map.put(left, leftDistance);
		}
		if(upDistance!=0)
		{
			list.add(rightDistance);
			map.put(right, rightDistance);
		}
		
		Collections.sort(list);
		
		for(Entry<Position, Integer> entry: map.entrySet()) {
			if(entry.getValue()==list.get(0)) {
				position = wh.moveObjectToCell(position.getX(), position.getY(), entry.getKey().getX(), entry.getKey().getY(), UID);
				break;
			}
		}
		
		
	}
	
	public boolean doesSquareHaveRobot(Position p, Warehouse wh) {
		ArrayList<String> UIDs = new ArrayList<String>();
		for(Position keyP:wh.getGrid().keySet()) {
			if(keyP.getX()==p.getX()&&keyP.getY()==p.getY()) {
				UIDs = wh.getGrid().get(keyP);
			}
		}
		
		if(UIDs.isEmpty()) {
			return false;
		}
		
		else {
			for(String s: UIDs) {
				if(s.startsWith("r")) {
					return true;
				}
			}
		}
		
		return false;
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
		//System.out.println("Robots warehouse: " + wh.toString());
		position = wh.getPositionFromUID(UID);
		Position p = new Position(0,0);
		move(p,wh);
		
			
	}
}
