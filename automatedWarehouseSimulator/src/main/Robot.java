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

public class Robot extends WarehouseObject implements Tick {

	// private ArrayList<Position> path;
	private String chargingPodUID;
	private boolean hasItem = false;
	private boolean isBusy = false;
	private int batteryChargePercent;
	private static int MAX_BATTERY;
	private Position position;
	private Position destination;
	private Queue<String> shelves;
	private String requestingPackingStationUID;
	private Position targetShelfPosition;
	private boolean needsToCharge;
	private boolean chargeToFull;

	/**
	 * 
	 */
	public Robot(String robotUID, int MAX_BATTERY, Position position, String chargingPodUID) {
		super(robotUID);
		Robot.MAX_BATTERY = MAX_BATTERY;
		this.position = position;
		batteryChargePercent = MAX_BATTERY;
		requestingPackingStationUID = null;
		targetShelfPosition = null;
		shelves = new LinkedList<String>();
		needsToCharge = false;
		this.chargingPodUID = chargingPodUID;
		chargeToFull = false;
	}
	
	/**
	* Gets the number of squares from one position to another using the Manhattan Distance formula
	* Gets the difference in the x and adds it to the difference in the y. Both of these numbers are
	* made to be absolute so they will not be negative if moving from right to left or upwards.
	*
	* @param a, the starting position
	* @param b, the destination
	* 
	* @return int, distance from point a to point b.
	*/
	public int getManhattanDistance(Position a, Position b) {
		return (java.lang.Math.abs(a.getX() - b.getX())) + (java.lang.Math.abs((a.getY() - b.getY())));
	}

	/**
	* Moves the robot 1 square towards the current destination.
	* 1) Position objects are created for the positions up, down, left and right of the current position.
	* 2) Gets the distance from each position to the destination
	* 3) If any position already contains a robot or is out of bounds, it is not considered.
	* 4) Robot moves to in the direction that is closest to the destination and is not blocked. 
	*
	* @param wh, the current warehouse
	* @param destination, the desired destination
	*/
	public void move(Position destination, Warehouse wh) {

		this.destination = destination;
		
		//Gets positions objects for the surrounding squares from current position
		Position up = new Position(position.getX(), (position.getY() - 1));
		Position down = new Position(position.getX(), (position.getY() + 1));
		Position left = new Position(position.getX() - 1, (position.getY()));
		Position right = new Position(position.getX() + 1, (position.getY()));

		//Sets the distance to a high number for later checks
		int upDistance = 10000;
		int downDistance = 10000;
		int leftDistance = 10000;
		int rightDistance = 10000;

		
		//For each position, set the distance if there is no robot on that square and if it is within bounds
		if (!doesSquareHaveRobot(up, wh) && up.getX() >= 0 && up.getX() < wh.getX() && up.getY() >= 0
				&& up.getY() < wh.getY()) {
			upDistance = getManhattanDistance(up, destination);
		}
		if (!doesSquareHaveRobot(down, wh) && down.getX() >= 0 && down.getX() < wh.getX() && down.getY() >= 0
				&& down.getY() < wh.getY()) {
			downDistance = getManhattanDistance(down, destination);
		}
		if (!doesSquareHaveRobot(left, wh) && left.getX() >= 0 && left.getX() < wh.getX() && left.getY() >= 0
				&& left.getY() < wh.getY()) {
			leftDistance = getManhattanDistance(left, destination);
		}
		if (!doesSquareHaveRobot(right, wh) && right.getX() >= 0 && right.getX() < wh.getX() && right.getY() >= 0
				&& right.getY() < wh.getY()) {
			rightDistance = getManhattanDistance(right, destination);
		}

		//Creates an ArrayList of each distance and a HashMap to store which distance correlates which position
		ArrayList<Integer> list = new ArrayList<Integer>();
		HashMap<Position, Integer> map = new HashMap<Position, Integer>();

		
		//Adds the distance to the list if it passed the earlier checks
		if (upDistance != 10000) {
			list.add(upDistance);
			map.put(up, upDistance);
		}

		if (downDistance != 10000) {
			list.add(downDistance);
			map.put(down, downDistance);
		}
		if (leftDistance != 10000) {
			list.add(leftDistance);
			map.put(left, leftDistance);
		}
		if (rightDistance != 10000) {
			list.add(rightDistance);
			map.put(right, rightDistance);
		}

		//Sorts the list in ascending order so the first (and lowest) value can be used
		Collections.sort(list);

		//Gets the desired position and moves the robot 1 square in that direction.
		for (Entry<Position, Integer> entry : map.entrySet()) {
			if (entry.getValue() == list.get(0)) {
				position = wh.moveObjectToCell(position.getX(), position.getY(), entry.getKey().getX(),
						entry.getKey().getY(), UID);
				batteryChargePercent -= batteryCostPerTick();
				break;
			}
		}

	}

	public boolean doesSquareHaveRobot(Position p, Warehouse wh) {
		ArrayList<String> UIDs = new ArrayList<String>();
		for (Position keyP : wh.getGrid().keySet()) {
			if (keyP.getX() == p.getX() && keyP.getY() == p.getY()) {
				UIDs = wh.getGrid().get(keyP);
			}
		}

		if (UIDs.isEmpty()) {
			return false;
		}

		else {
			for (String s : UIDs) {
				if (s.startsWith("r")) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Set the current battery of the Robot
	 * @param new battery charge
	 */
	public void setBatteryChargePercentage(int battery) {
		batteryChargePercent = battery;
	}

	/**
	 * Return the current battery of the Robot
	 * @return int - current Battery
	 */
	public int getBatteryStatus() {
		return batteryChargePercent;
	}

	/**
	 * 
	 * @return
	 */
	public boolean checkIfPossibleToAcceptJob(Warehouse wh, Order order) {
		
		Queue<String> possibleShelves = new LinkedList<String>();
		
		ArrayList<String> temp = order.getShelfUIDs();
		for (String s : temp) {
			possibleShelves.add(s);
		}

		// If the queue is NOT null, then it is currently completing a job. Return
		// false.

		if (!shelves.isEmpty()) {
			return false;
		}

		// Can it reach the locations with existing battery?

		// Get the current position.
		// Get the possible shelves position of the order that needs completing
		// Plug into manhatten distance.
		// Take this manhatten distance and * it by 1 or 2 depending on whether the
		// robot is holding an item.
		// Take the this calculated value and take it away from the robots current battery
		// If battery goes below 0% - return false to reject the job.

		Position shelfP = getDestinationPosition(wh, possibleShelves.peek());

		int manhattanDistance = getManhattanDistance(position, shelfP);

		int futureBattery = batteryChargePercent - (manhattanDistance * batteryCostPerTick());

		if (futureBattery <= 0) {
			return false;
		}

		// return true if none of the if statements above are triggered.
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
		if (hasItem) {
			return 2;
		} else {
			return 1;
		}
	}
	
	/**
	 * 
	 */
	public int getMaxBattery() {
		return MAX_BATTERY;
	}

	/**
	 * 
	 */
	public String toString() {
		return "Robot(" + UID + ")";
	}

	/**
	 * 
	 * @param order
	 * @param packingStationUID
	 */
	public void acceptOrder(Order order, String packingStationUID, Warehouse wh) {
		ArrayList<String> temp = order.getShelfUIDs();
		for (String s : temp) {
			shelves.add(s);
		}
		isBusy = true;
		requestingPackingStationUID = wh.getPackingStation(packingStationUID).getUID();
	}

	/**
	 * 
	 * @param wh
	 * @param destination
	 * @return
	 */
	private boolean doesRobotNeedToCharge(Warehouse wh, Position destination) {
		
		int futureCostPerTick = 0; 

		if(hasItem == true){
			futureCostPerTick = 1;
		}

		if(hasItem == false) {
			futureCostPerTick = 2;
		}
		
		int manhattanValuePD = getManhattanDistance(position, destination); //1 
		int manhattanValueDCP = getManhattanDistance(destination, wh.getPositionFromUID(chargingPodUID)); //2 
		//int batteryLossSum = (manhattanValuePD + manhattanValueDCP) * batteryCostPerTick(); //doesn't account for the second line, only first
	
		//3 is added as a contingency to account for another robot possibly blocking the robot from getting to the charging pod in time.
		//Contingency ideally should be dynamic in conjunction with the max battery of the robot.
		int batteryLossSum = (manhattanValuePD * batteryCostPerTick()) + (manhattanValueDCP * futureCostPerTick) + 3;
		

		if ((batteryChargePercent - batteryLossSum) <= 0) {
			if(batteryLossSum > MAX_BATTERY/2) {
				chargeToFull = true;
			}
			return true;
		} else {
			return false;
		}
		
		//if total loss sum is not greater than half the battery, charge to half. 
		//if total battery loss sum is greater than half the battery, charge to full. 
	}


	/**
	 * 
	 */
	public void tick(Warehouse wh) {

		position = wh.getPositionFromUID(UID);

//		wh.addToMessage(UID + " PACKING STATION IS: " + requestingPackingStationUID);
//		wh.addToMessage(UID + " BUSY?: " + isBusy);
//		wh.addToMessage(UID + " NEED TO CHARGE?: " + needsToCharge);
		
		// Battery

		// needsToCharge = false/true

		// Check if needsToCharge needs to be true

		// If the robot needsToCharge is true
		// change destination to charging pod
		// check if robot is at charging pod
		// if true,
		// if battery is >50%,
		// is true, stop charging and set needsToCharge to false
		// is false, charge
		// if false,
		// move to charging pod

		// add extra check to see if needsToCharge is false.

		// check if it needsToCharge
		if (needsToCharge) {
			if (position.equals(destination)) {
				//if(chargeToFull is true, do this, false, do whats already written)
				if(chargeToFull) {
					if (batteryChargePercent == MAX_BATTERY) {
						wh.addToMessage("Robot " + UID + " is done charging. Battery: " + batteryChargePercent);
						needsToCharge = false;
						chargeToFull = false;
					} else {
						wh.addToMessage("Robot " + UID + " has started charging.");
						//wh.addToMessage("Robot " + UID + "old battery: " + batteryChargePercent);
						wh.getChargingPod(chargingPodUID).chargeRobot(UID, wh);
						//wh.addToMessage("Robot " + UID + "new battery: " + batteryChargePercent);
	
					}
				}else {
					if (batteryChargePercent >= MAX_BATTERY / 2) {
						wh.addToMessage("Robot " + UID + " is done charging. Battery: " + batteryChargePercent);
						needsToCharge = false;
					} else {
						wh.addToMessage("Robot " + UID + " is charging.");
						//wh.addToMessage("Robot " + UID + "old battery: " + batteryChargePercent);
						wh.getChargingPod(chargingPodUID).chargeRobot(UID, wh);
						//wh.addToMessage("Robot " + UID + "new battery: " + batteryChargePercent);
	
					}
				}
			} else {
				move(destination, wh);
			}
		} else {

			if (isBusy) {
				if (!hasItem) {
					// Set the target shelf position.
					targetShelfPosition = getDestinationPosition(wh, shelves.peek());
					// Destination is the shelf in the queue
					destination = targetShelfPosition;
					// call method which checks if it can reach the destination, and if it can't,
					// make needsToCharge to be true and change the destination to the chargingPod,
					// and move.
					if (doesRobotNeedToCharge(wh, destination)) {
						needsToCharge = true;
						wh.addToMessage("Robot " + UID + " needs to charge. Has no item.");
						destination = wh.getPositionFromUID(chargingPodUID);
						move(destination, wh);
					} else {
						// If the robot is at the target shelf
						if (position.equals(destination)) {
							// Pick up all items and set hasItem to true
							hasItem = true;
						} else {
							// If not, move towards the target shelf.
							move(destination, wh);
						}
					}
				} else if (hasItem) {
					// Set the destination to the packing stations position
					destination = wh.getPositionFromUID(requestingPackingStationUID);
					// call method which checks if it can reach the destination, and if it can't,
					// make needsToCharge to be true and change the destination to the chargingPod,
					// and move.
					if (doesRobotNeedToCharge(wh, destination)) {
						needsToCharge = true;
						wh.addToMessage("Robot " + UID + " needs to charge. Has item.");
						destination = wh.getPositionFromUID(chargingPodUID);
						move(destination, wh);
					} else {
						// If the robot is at the packing station
						if (position.equals(destination)) {
							// Remove shelf from queue
							shelves.poll();
							// Check if the shelf is empty
							if (shelves.isEmpty()) {
								// If yes, tell the packing station that the job has been completed.
								wh.getPackingStation(requestingPackingStationUID).orderRetrievedByRobot();
								// Set the robot as not busy.
								isBusy = false;
								// Set the requesting packing station to null
								requestingPackingStationUID = null;
								// Set the destination to null;
								destination = null;
								// Set hasItem to false;
								hasItem = false;
							} else {
								hasItem = false;
							}
						} else {
							// If the robot is not at the packing station, move a step towards it.
							move(destination, wh);
						}
					}
				}
			} else {
				destination = wh.getPositionFromUID(chargingPodUID);
				// If not busy, travel back to the ChargingPod.
				if (!position.equals(destination)) {
					move(destination, wh);
				}else if((position.equals(destination))) {
					wh.getChargingPod(chargingPodUID).chargeRobot(UID, wh);
				}
				
				
			}
		}
	}
}
