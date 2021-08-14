package main;

public class ChargingPod extends WarehouseObject implements Tick {
	
	/**
	 * Details here
	 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
	 *
	 */
	
	private static final int POWER_UNITS_PER_TICK = 0;
	
	/**
	 * 
	 */
	public ChargingPod(String UID) {
		super(UID);
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
	public void Tick() {
		// TODO Auto-generated method stub
		
	}

}
