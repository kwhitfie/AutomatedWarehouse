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
	private Position destination;
	private Queue<String> shelves;
	private String requestingPackingStationUID;

	/**
	 * 
	 */
	public Robot(String UID, int MAX_BATTERY, Position position) {
		super(UID);
		this.MAX_BATTERY = MAX_BATTERY;
		this.position = position;
		batteryChargePercent = 50;
		requestingPackingStationUID = null;
		shelves = new LinkedList<String>();
	}
	
	public int getManhattanDistance(Position a, Position b) {
		return (a.getX()-b.getX())+(a.getY()-b.getY());
	}
	
	
	/**
	 * 
	 */
	public void move(Position destination, Warehouse wh) {
		
		this.destination = destination;
		
		Position up = new Position(position.getX(),(position.getY()-1));
		Position down = new Position(position.getX(),(position.getY()+1));
		Position left = new Position(position.getX()-1,(position.getY()));
		Position right = new Position(position.getX()+1,(position.getY()));
		
		int upDistance = 10000;
		int downDistance = 10000;
		int leftDistance = 10000;
		int rightDistance = 10000;
		
		System.out.println("MAX X: " + wh.getX() + " MAX Y = " + wh.getY());
		
		if(!doesSquareHaveRobot(up,wh) && up.getX() >= 0 && up.getX() < wh.getX() && up.getY() >= 0 && up.getY() < wh.getY())
		{
			System.out.println("\nUp position: X = " + up.getX() + " Y = " + up.getY());
			upDistance = getManhattanDistance(up,destination);
		}
		if(!doesSquareHaveRobot(down,wh) && down.getX() >= 0 && down.getX() < wh.getX() && down.getY() >= 0 && down.getY() < wh.getY())
		{
			System.out.println("\nDown position: X = " + down.getX() + " Y = " + down.getY());
			downDistance = getManhattanDistance(down,destination);
		}
		if(!doesSquareHaveRobot(left,wh) && left.getX() >= 0 && left.getX() < wh.getX() && left.getY() >= 0 && left.getY() < wh.getY())
		{
			System.out.println("\nLeft position: X = " + left.getX() + " Y = " + left.getY());
			leftDistance = getManhattanDistance(left,destination);
		}
		if(!doesSquareHaveRobot(right,wh) && right.getX() >= 0  && right.getX() < wh.getX() && right.getY() >= 0 && right.getY() < wh.getY())
		{
			System.out.println("\nRight position: X = " + right.getX() + " Y = " + right.getY());
			rightDistance = getManhattanDistance(right,destination);
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		HashMap<Position,Integer> map = new HashMap<Position,Integer>();
		
		if(upDistance!=10000)
		{
			list.add(upDistance);
			map.put(up, upDistance);
		}
		
		if(downDistance!=10000)
		{
			list.add(downDistance);
			map.put(down, downDistance);
		}
		if(leftDistance!=10000)
		{
			list.add(leftDistance);
			map.put(left, leftDistance);
		}
		if(rightDistance!=10000)
		{
			list.add(rightDistance);
			map.put(right, rightDistance);
		}
		
		Collections.sort(list);
		
		System.out.println("HASHMAP OF POSSIBLE POSITIONS");
		for(Entry<Position, Integer> entry: map.entrySet()) {
			System.out.println("X: " + entry.getKey().getX() + " Y: " + entry.getKey().getY());
		}
		
		for(Entry<Position, Integer> entry: map.entrySet()) {
			if(entry.getValue()==list.get(0)) {
				System.out.println("CHOSEN POSITION");
				System.out.println("X: " + entry.getKey().getX() + " Y: " + entry.getKey().getY());
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
	public boolean checkIfPossibleToAcceptJob(Warehouse wh) {
		//The robot will decide if it
		//wants to accept the assignment or not: this will depend on the current battery level and how far
		//the shelf and the packing station are.
		
		//Is the robot already completing a job?
		
		if(!isBusy) {
			return true;
		}
		
		//If the queue is NOT null, then it is currently completing a job. Return false.
		
		//If it is null, then the robot is not completing a job. Next if statement.
		
		if(!shelves.isEmpty()) {
			return false;
		}
		
		
		//Does the robot have enough battery? 
		
		//If battery is >50%, return false.
		
		if(batteryChargePercent <= MAX_BATTERY/2) {
			return false;
		}
		
		
		
		//Can it reach the locations with existing battery?
		
		//Get the current position. 
		//Get the shelves position (peek at the shelves queue)
		//Plug into manhatten distance.
		//Take this manhatten distance and * it by 1 or 2 depending on whether the robot is holding an item.
		//If battery goes below 50% - return false. 
		
		Position shelfP = getDestinationPosition(wh, shelves.peek());

		int manhattanDistance = getManhattanDistance(position, shelfP);
		
		int futureBattery = batteryChargePercent - (manhattanDistance * batteryCostPerTick());
		
		if(futureBattery <= MAX_BATTERY/2) {
			return false;
		}
		
		//return true if none of the if statements above are triggered. 
		return true;
	}
	
	/**
	 * 
	 */
	public Position getDestinationPosition(Warehouse wh, String UID) {
		return wh.getPositionFromUID(UID);
		
	}
	
	/**
	 * 
	 */
	public int batteryCostPerTick() {
		if(hasItem) {
			return 2;
		}else {
			return 1;
		}
	}
	
	/**
	 * 
	 * @param StorageShelfID
	 * @return
	 */
	public boolean getItemFromShelf(int StorageShelfID) {
		return false;
	}
	
	/**
	 * 
	 */
	public String toString() {
		return "Robot("+UID+")";
	}
	
	/**
	 * 
	 * @param order
	 * @param packingStationUID
	 */
	public void acceptOrder(Order order, String packingStationUID, Warehouse wh) {
		ArrayList<String> temp = order.getShelfUIDs();
		for(String s : temp) {
			shelves.add(s);
		}
		isBusy = true;
		destination = getDestinationPosition(wh, shelves.peek());
		requestingPackingStationUID = packingStationUID;
	}
	
	/**
	 * 
	 */
	public void tick(Warehouse wh) {
		// TODO Auto-generated method stub
		//System.out.println("Robots warehouse: " + wh.toString());
		position = wh.getPositionFromUID(UID);
		Position p = new Position(0,0);
		destination = p;
		if(position.getX()==destination.getX() && position.getY()==destination.getY())
		{
			
		}
		else {
			move(p,wh);
		}
		
		
			
	}
}
