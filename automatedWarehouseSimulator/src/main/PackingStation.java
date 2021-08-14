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
	
	public Order getNextOrder() {
		return null;
	}
	
	/**
	 * 
	 */
	@Override
	public void tick(Warehouse wh) {
		// TODO Auto-generated method stub
		System.out.println("Packing Stations warehouse: " + wh.toString());

	}

}
