package main;

public class ChargingPod extends WarehouseObject implements Tick {
	
	/**
	 * Details here
	 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
	 *
	 */
	
	private static int POWER_UNITS_PER_TICK;
	
	/**
	 * 
	 */
	public ChargingPod(String UID, int POWER_UNITS_PER_TICK) {
		super(UID);
		this.POWER_UNITS_PER_TICK = POWER_UNITS_PER_TICK;
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
	public void chargeRobot() {
		
	}
	
	/**
	 * 
	 */
	@Override
	public void tick(Warehouse wh) {
		System.out.println("ChargingPod warehouse: " + wh.toString());
		
	}

}
