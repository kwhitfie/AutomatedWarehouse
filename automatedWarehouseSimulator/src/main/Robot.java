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
	 * Constructor for a Robot object.
	 * @param robotUID - the robots UID
	 * @param MAX_BATTERY - the robots max battery
	 * @param position - the robots position in the Warehouse grid
	 * @param chargingPodUID - the assigned ChargingPod UID
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
	private void move(Position destination, Warehouse wh) {

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
	
	/**
	 * This method checks whether a square in the Warehouse
	 * already contains a Robot.
	 * 
	 * If yes - return true.
	 * If no - return false.
	 * 
	 * @param p - Position to check
	 * @param wh - Warehouse to access position in grid. 
	 * @return boolean 
	 */
	private boolean doesSquareHaveRobot(Position p, Warehouse wh) {
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
	 * This method checks if the Robot is able to accept a job.
	 * 
	 * @param wh - the Warehouse
	 * @param order - the order that the Robot will need to complete.
	 * @return boolean - true if it can accept, false if it can't accept.
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
	 * Get the Position of the destination that the Robot needs to travel to.
	 * @return destinations Position object
	 */
	private Position getDestinationPosition(Warehouse wh, String UID) {
		return wh.getPositionFromUID(UID);
	}

	/**
	 * Return how much the battery will decrease by to move this tick.
	 * @return int - battery decrease
	 */
	private int batteryCostPerTick() {
		if (hasItem) {
			return 2;
		} else {
			return 1;
		}
	}
	
	/**
	 * Return the max battery of the robot.
	 * @return int - max battery
	 */
	public int getMaxBattery() {
		return MAX_BATTERY;
	}

	/**
	 * Return a String representation of the Robot.
	 * @return String representation
	 */
	public String toString() {
		return "Robot(" + UID + ")";
	}

	/**
	 * This method is used to accept an order from a PackingStation.
	 * @param order - the order that's been accepted
	 * @param packingStationUID - the packingStation that has provided the order
	 */
	public void acceptOrder(Order order, String packingStationUID) {
		ArrayList<String> temp = order.getShelfUIDs();
		for (String s : temp) {
			shelves.add(s);
		}
		isBusy = true;
		requestingPackingStationUID = packingStationUID;
	}

	/**
	 * This method is used to determine whether the Robot
	 * needs to charge before heading to a destination.
	 * 
	 * @param wh - the Warehouse
	 * @param destination - the desired destination
	 * @return boolean - true if it does need to charge, false if not.
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
	
		//3 is added as a contingency to account for another robot possibly blocking the robot from getting to the charging pod in time.
		//Contingency ideally should be dynamic in conjunction with the max battery of the robot.
		int batteryLossSum = (manhattanValuePD * batteryCostPerTick()) + (manhattanValueDCP * futureCostPerTick) + 3;
		
		//Check if the current battery is less or equal to 0 after minusing the batteryLossSum against it.
		if ((batteryChargePercent - batteryLossSum) <= 0) {
			//if total battery loss sum is not greater than half the battery, charge to half.
			//if total battery loss sum is greater than half the battery, charge to full. 
			if(batteryLossSum > MAX_BATTERY/2) {
				chargeToFull = true;
			}
			//If it is less than or equal to 0, it needs to charge before taking the journey. Return true.
			return true;
		} else {
			//If it isn't less than or equal to 0, it needs to charge before taking the journey. Return false.
			return false;
		}
	}


	/**
	 * This tick method determines the logic behind the actions 
	 * of a Robot each tick of the simulation, using methods
	 * from the Warehouse to get all the required information/objects.
	 * 
	 * @param wh the Warehouse object.
	 */
	public void tick(Warehouse wh) {

		position = wh.getPositionFromUID(UID);
		
		
		// check if it needsToCharge
		if (needsToCharge) {
			// if yes, check if the current position is at the designated charging pod
			if (position.equals(destination)) {
				//if at the charging pod,
				//check if we need to charge to full battery or not
				if(chargeToFull) {
					//if do we need to charge to full battery, check if we are at full battery or not
					if (batteryChargePercent == MAX_BATTERY) {
						//if full battery, add a message to the simulation report 
						wh.addToMessage("Robot " + UID + " is done charging. Battery: " + batteryChargePercent);
						//set the charge boolean to false as its finished charging.
						needsToCharge = false;
						//set the chargeToFull boolean to false as its finished charging.
						chargeToFull = false;
					} else {
						//if not charged, add a message to the report and charge the battery.
						wh.addToMessage("Robot " + UID + " has started charging.");
						wh.getChargingPod(chargingPodUID).chargeRobot(UID, wh);
	
					}
				}else {
					//if we don't need to charge to full, charge to half
					if (batteryChargePercent >= MAX_BATTERY / 2) {
						//if the battery is charged to half, 
						//add a message to the simulation report
						wh.addToMessage("Robot " + UID + " is done charging. Battery: " + batteryChargePercent);
						//set the charge boolean to false as its finished charging.
						needsToCharge = false;
					} else {
						//if not charged, add a message to the report and charge the battery.
						wh.addToMessage("Robot " + UID + " is charging.");
						wh.getChargingPod(chargingPodUID).chargeRobot(UID, wh);
					}
				}
			} else {
				//if not at the charging pod, move towards it this tick.
				move(destination, wh);
			}
		} else {
			//check if the robot has an order to complete
			if (isBusy) {
				//check if the robot has an item or not
				if (!hasItem) {
					//if not, 
					// Set the target shelf position to the next shelf in the shelves queue.
					targetShelfPosition = getDestinationPosition(wh, shelves.peek());
					// Set the destination to the targetShelfPosition.
					destination = targetShelfPosition;
					// call method which checks if it needs to charge before travelling to the destination
					if (doesRobotNeedToCharge(wh, destination)) {
						//if robot needs to charge before travelling to destination, 
						//make needsToCharge to be true and change the destination to the chargingPod,
						//and move.
						needsToCharge = true;
						wh.addToMessage("Robot " + UID + " needs to charge. Has no item.");
						destination = wh.getPositionFromUID(chargingPodUID);
						move(destination, wh);
					} else {
						//if the robot doesn't need to charge,
						// Check if the robot is at the target shelf
						if (position.equals(destination)) {
							//If it at the target shelf,
							//pick up all items and set hasItem to true
							hasItem = true;
						} else {
							//If not, move towards the target shelf.
							move(destination, wh);
						}
					}
				} else if (hasItem) {
					// Set the destination to the packing stations position
					destination = wh.getPositionFromUID(requestingPackingStationUID);
					// call method which checks if it can reach the destination
					if (doesRobotNeedToCharge(wh, destination)) {
						//if robot needs to charge before travelling to destination, 
						//make needsToCharge to be true and change the destination to the chargingPod,
						//and move.
						needsToCharge = true;
						wh.addToMessage("Robot " + UID + " needs to charge. Has item.");
						destination = wh.getPositionFromUID(chargingPodUID);
						move(destination, wh);
					} else {
						// Check if the robot is at the packing station
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
				// If Robot is not busy, travel back to the ChargingPod.
				destination = wh.getPositionFromUID(chargingPodUID);
				if (!position.equals(destination)) {
					//If not at the ChargingPod, travel to it's destination.
					move(destination, wh);
				}else if((position.equals(destination))) {
					//If it is at the ChargingPod, charge while waiting for a job.
					wh.getChargingPod(chargingPodUID).chargeRobot(UID, wh);
				}
			}
		}
	}
	
	public String getChargingPodUID() {
		return chargingPodUID;
	}
	
	public Boolean hasItem() {
		return hasItem;
	}
	
	public Boolean isBusy() {
		return isBusy;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public Position getDestination() {
		return destination;
	}
	
	public Queue<String> getShelves(){
		return shelves;
	}
	
	public Boolean needsToCharge() {
		return needsToCharge;
	}
	
	public Boolean chargeToFull() {
		return chargeToFull;
	}
	
	public String getRequestingPackingStationUID() {
		return requestingPackingStationUID;
	}
	
	public Position getTargetShelfPosition() {
		return targetShelfPosition;
	}
}
