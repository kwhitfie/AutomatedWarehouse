package main;

public class PackingStation extends WarehouseObject implements Tick {

	/**
	 * This class details the fields and method of a Packing Station object.
	 * 
	 * A Packing Station takes an unassigned order from the Warehouse,
	 * and will look to ask a robot to get the items for this order from 
	 * the shelves in a Warehouse.
	 * 
	 * Once a items for an order have been retrieved, a PackingStation 
	 * packs this order. When packing is complete, the order is dispatched.
	 * 
	 * This repeats until there are no more unassigned orders left to pack.
	 * 
	 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
	 *
	 */

	private boolean needsRobot;
	private boolean packing;
	private Order order;
	private int ticksToPackOrder;

	/**
	 * Constructor for a Packing Station object.
	 * @param UID - the PackingStations UID
	 */
	public PackingStation(String UID) {
		super(UID);
		needsRobot = true;
		packing = false;
	}
	
	/**
	 * Return the Packing stations internal details
	 * as a String representation.
	 */
	public String toString() {
		return "Packing Station ("+UID+")";
	}

	/**
	 * Get the PackingStations current order.
	 * 
	 * @return the current Order
	 */
	public Order getCurrentOrder() {
		return order;
	}

	/**
	 * Get the next unassigned order from the warehouse and assign it to the order field.
	 * Access the order and store its ticks to pack the order.
	 * 
	 * @param wh the warehouse
	 * @return the next unassigned order
	 */
	public void getNextOrder(Warehouse wh) {
		order = wh.getNextUnassignedOrder();
		ticksToPackOrder = order.getTicksToPack();
	}

	/**
	 * This method is used to let the Packing Station
	 * know that a Robot has retrieved the items for an
	 * order and that packing can begin of the order
	 * can begin.
	 */
	public void orderRetrievedByRobot() {
		packing = true;
	}

	/**
	 * This tick method determines the logic behind the actions 
	 * of a PackingStation each tick of the simulation, using methods
	 * from the Warehouse to get all the required information/objects.
	 * 
	 * @param wh the Warehouse object.
	 */
	
	//LOGIC BEHIND TICK
	
	// PackingStation will have three modes/statuses
	// 1. Sleeping - no more unassigned orders left.
	// 2. Needs a robot to service an Order.
	// 3. Has a robot servicing an Order, waiting for robot to finish this.
	// 4. Robot has finished retrieving the Order, packing the Order.

	// 2, 3 and 4 are represented via a boolean, whereas 1 is a check to see if the
	// unassigned order queue is empty.
	
	// 1. If unassigned order queue is empty, add this message to the report logs via the
	// warehouse method and sleep.
	
	// For 2, 3 and 4, depending the values of the booleans, a different action
	// will be taken.
	
	// 2. - If the PS requires a Robot to service an order, and the unassigned order 
	// queue isn't empty, the PS will check if it has an Order ready. 
	// If not, get an Order from the OrderQueue via Warehouse.
	// If yes, do nothing.
	
	// 2.1 - Once confirmed the PS has an order, it will check if any Robots are
	// available to service the Order. 
	// 	If yes, get that Robot to service the order.
	//	If not, wait till the next tick and try again.
	
	// 3. - The PS is sleeping while the Robot retrieves the Orders items.
	
	// 4. - Once the Orders items are retrieved, begin packing the Order for the x
	// tickets it specifies. Once finished packing, dispatch the Order and move the
	// Order to the dispatchedOrderQueue in the Warehouse.
	
	@Override
	public void tick(Warehouse wh) {
		
		System.out.println(UID + " HAS THE ORDER: " + order);
		
		if (needsRobot) {
			if (wh.isUnassignedOrderQueueEmpty() && order == null) {
				wh.addToMessage(UID + " is sleeping." + " No more orders left to be assigned.");
			} else {
				if (order == null) {
					getNextOrder(wh);
				}

				String potentialRobotUID = wh.checkRobotAvailability(order);
				if (!(potentialRobotUID == null)) {
					wh.addToMessage(
							this.toString() + " successfully gives order to Robot (" + potentialRobotUID + "). ");
					needsRobot = false;
					wh.getRobot(potentialRobotUID).acceptOrder(order, UID); // call the method which engages the
																				// robot to get the items, once
																				// complete.
				}

			}
		}
		// Check if this packing station needs to pack an order after the robot has got the items
		else if (packing) {

			// Is the packing complete? Check if ticks to pack is 0, and if yes, move the
			// order to the dispatched list.
			if (ticksToPackOrder == 0) {
				wh.moveOrderFromAssignedToDispactedList(order);
				packing = false;
				needsRobot = true;
				order = null;
				// If ticks to pack is not 0, decrease the ticks to pack by 1.
			} else {
				ticksToPackOrder--;
				//To allow for the user to know when a packing station
				//has/hasn't finished packing on this tick, an if statement has 
				//been added.
				if (ticksToPackOrder == 0) {
					wh.addToMessage(this.toString() + " is finished packing.");
				} else {
					wh.addToMessage(this.toString() + " is packing. Ticks left: " + ticksToPackOrder);
				}
			}
		}

	}
	
	public boolean needRobot() {
		return needsRobot;
	}
	
	public boolean isPacking() {
		return packing;
	}
}
