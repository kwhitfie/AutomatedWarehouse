package main;

public class ChargingPod extends WarehouseObject implements Tick {
	
	/**
	 * Details here
	 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
	 *
	 */
	
	private static int POWER_UNITS_PER_TICK;
	private String assignedRobotUID;
	
	/**
	 * 
	 */
	public ChargingPod(String UID, int POWER_UNITS_PER_TICK, String assignedRobotUID) {
		super(UID);
		ChargingPod.POWER_UNITS_PER_TICK = POWER_UNITS_PER_TICK;
		this.assignedRobotUID = assignedRobotUID;
	}
	
	/**
	 * 
	 * @return
	 */
	public String toString() {
		return "Charging Pod("+UID+")";
	}
	
	/**
	 * 
	 */
	public void chargeRobot(String robotUID, Warehouse wh) {
		//If the robot that wants to be charged is the assignedRobotUID, charge it.     
		if(assignedRobotUID.contentEquals(robotUID)) {
			//Get the robots charge percentage. 
			int battery = wh.getRobot(robotUID).getBatteryStatus();
			
			//Take this percentage, and add the POWER_UNITS_PER_TICK to it.
			battery += POWER_UNITS_PER_TICK;
			
			if(wh.getRobot(robotUID).getBatteryStatus()>wh.getRobot(robotUID).getMaxBattery()) {
				battery = wh.getRobot(robotUID).getMaxBattery();
			}
			
			//Set the battery charge to this new value.
			wh.getRobot(robotUID).setBatteryChargePercentage(battery);
		}else {
			System.out.println("This robot is not authorised to use this charging pod!");
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void tick(Warehouse wh) {
		//System.out.println("ChargingPod warehouse: " + wh.toString());
		
	}
}
