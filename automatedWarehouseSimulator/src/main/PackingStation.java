package main;

public class PackingStation extends WarehouseObject implements Tick{

	/**
	 * Details here
	 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
	 *
	 */

	private Order order;
	
	/**
	 * 
	 */
	public PackingStation(String UID) {
		super(UID);
	}
	
	public String toString() {
		return "Packing Station("+UID+")";
	}

	/**
	 * 
	 * @return
	 */
	public Order getCurrentOrder() {
		return order;
	}
	
	/**
	 * 
	 */
	@Override
	public void Tick() {
		// TODO Auto-generated method stub

	}

}
