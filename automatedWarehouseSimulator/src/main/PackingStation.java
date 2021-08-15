package main;

public class PackingStation extends WarehouseObject implements Tick{

	/**
	 * Details here
	 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
	 *
	 */
	
	//PackingStation will have three modes/statuses
	// 	1. Needs a robot to service an Order. 
	// 	2. Has a robot servicing an Order, waiting for robot to finish this.
	//	3. Robot has finished retrieving the Order, packing the Order.
	
	//Each could be represented via a boolean.
	//In the tick method, depending the values of the booleans, a different action will be taken. 
	// 1. - If the PS requires a Robot to service an order, the PS will check if it has an Order ready. If not, get an Order from the OrderQueue via Warehouse. If yes, do nothing. 
	// 1.1 - Once confirmed the PS has an order, it will check if any Robots are available to service the Order. If yes, get that Robot to service the order. If not, wait till the next tick and try again.
	// 2. - The PS is sleeping while the Robot retrieves the Orders items.
	// 3. - Once the Orders items are retrieved, begin packing the Order for the x tickets it specifies. Once finished packing, dispatch the Order and move the Order to the dispatchedOrderQueue in the Warehouse.
	
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
