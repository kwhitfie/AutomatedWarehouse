package main;

/**
 * This class details the fields and method of a Charging Pod.
 * A charging pod has a 1 to 1 relationship with a robot object, 
 * and is used to charge a Robot objects battery. 
 * 
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class ChargingPod extends WarehouseObject {
	
	private static int POWER_UNITS_PER_TICK;
	private String assignedRobotUID;
	
	/**
	 * Constructor for a Charging Pod object.
	 * @param UID
	 * @param POWER_UNITS_PER_TICK
	 * @param assignedRobotUID
	 */
	public ChargingPod(String UID, int POWER_UNITS_PER_TICK, String assignedRobotUID) {
		super(UID);
		ChargingPod.POWER_UNITS_PER_TICK = POWER_UNITS_PER_TICK;
		this.assignedRobotUID = assignedRobotUID;
	}
	
	/**
	 * Return a string representation of the Charging Pod.
	 * @return string representation
	 */
	public String toString() {
		return "Charging Pod. UID: "+UID+ ". Power units per tick: " + POWER_UNITS_PER_TICK + ". Assigned robot UID: " + assignedRobotUID ;
	}
	
	/**
	 * This method charges the battery of its assigned robot.
	 * @param robotUID - UID of the robot being charged
	 * @param wh - the warehouse that contains this robot object.
	 */
	public void chargeRobot(String robotUID, Warehouse wh) {
		//If the robot that wants to be charged is the assignedRobotUID, charge it.     
		if(assignedRobotUID.contentEquals(robotUID)) {
			//Get the robots charge percentage. 
			int battery = wh.getRobot(robotUID).getBatteryStatus();
			
			//Take this battery charge, and add the POWER_UNITS_PER_TICK to it.
			battery += POWER_UNITS_PER_TICK;
			
			//If the robots battery exceeds the max, e.g., 101 / 100, change the battery to the max. 
			if(wh.getRobot(robotUID).getBatteryStatus()>wh.getRobot(robotUID).getMaxBattery()) {
				battery = wh.getRobot(robotUID).getMaxBattery();
			}
			
			//Set the battery charge to this new value.
			wh.getRobot(robotUID).setBatteryChargePercentage(battery);
		}
	}
	
	
	public int getPOWER_UNITS_PER_TICK() {
		return POWER_UNITS_PER_TICK;
	}
	
	public String getAssignedRobotUID() {
		return assignedRobotUID;
	}

}