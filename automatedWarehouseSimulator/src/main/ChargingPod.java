package main;

public class ChargingPod implements Tick {
	
	/**
	 * Details here
	 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
	 *
	 */
	
	private String UID;
	private static final int POWER_UNITS_PER_TICK = 0;
	
	/**
	 * 
	 */
	public ChargingPod(String UID) {
		this.UID = UID;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUID() {
		return UID;
	}
	
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
