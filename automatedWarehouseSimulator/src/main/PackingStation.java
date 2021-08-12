package main;

public class PackingStation implements Tick{

	/**
	 * Details here
	 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
	 *
	 */
	
	private String UID;
	private Order order;
	
	/**
	 * 
	 */
	public PackingStation(String UID) {
		this.UID=UID;
	}

	/**
	 * 
	 * @return
	 */
	public String getUID() {
		return UID;
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
