package main;

public class PackingStation extends WarehouseObject implements Tick {

	/**
	 * Details here
	 * 
	 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
	 *
	 */

	// PackingStation will have three modes/statuses
	// 1. Needs a robot to service an Order.
	// 2. Has a robot servicing an Order, waiting for robot to finish this.
	// 3. Robot has finished retrieving the Order, packing the Order.

	// Each could be represented via a boolean.
	// In the tick method, depending the values of the booleans, a different action
	// will be taken.
	// 1. - If the PS requires a Robot to service an order, the PS will check if it
	// has an Order ready. If not, get an Order from the OrderQueue via Warehouse.
	// If yes, do nothing.
	// 1.1 - Once confirmed the PS has an order, it will check if any Robots are
	// available to service the Order. If yes, get that Robot to service the order.
	// If not, wait till the next tick and try again.
	// 2. - The PS is sleeping while the Robot retrieves the Orders items.
	// 3. - Once the Orders items are retrieved, begin packing the Order for the x
	// tickets it specifies. Once finished packing, dispatch the Order and move the
	// Order to the dispatchedOrderQueue in the Warehouse.

	private boolean needsRobot;
	private boolean sleeping;
	private boolean packing;
	private Order order;
	private int ticksToPackOrder;

	/**
	 * 
	 */
	public PackingStation(String UID) {
		super(UID);
		needsRobot = true;
		sleeping = false;
		packing = false;
	}

	public String toString() {
		return "Packing Station(" + UID + ")";
	}

	/**
	 * 
	 * @return
	 */
	public Order getCurrentOrder() {
		return order;
	}

	/**
	 * Gets the next order from the warehouse and assigns it to the order field.
	 * 
	 * @param wh the warehouse
	 * @return the next order
	 */
	public void getNextOrder(Warehouse wh) {
		order = wh.getNextUnassignedOrder();
		ticksToPackOrder = order.getTicksToPack();
	}

	/**
	 * 
	 */
	public void orderRetrievedByRobot() {
		System.out.println("Robot job complete");
		sleeping = false;
		packing = true;
	}

	/**
	 * 
	 */
	@Override
	public void tick(Warehouse wh) {
		// If this packingStations needs a robot to serve an order
		if (needsRobot) {
			if (wh.isUnassignedOrderQueueEmpty()) {
				wh.addToMessage(UID + " is sleeping." + " No more orders left to be assigned.");
				wh.addToMessage(UID + " has order " + order);
			} else {
				if (order == null) {
					getNextOrder(wh);
				}

				String potentialRobotUID = wh.checkRobotAvailability();
				if (!(potentialRobotUID == null)) {
					wh.addToMessage(
							this.toString() + " successfully gives order to Robot (" + potentialRobotUID + "). ");
					needsRobot = false;
					sleeping = true;
					wh.getRobot(potentialRobotUID).acceptOrder(order, UID, wh); // call the method which engages the
																				// robot to get the items, once
																				// complete.
				}

			}
		}
		// If this packing station needs to pack an order after the robot has got the
		// items
		else if (packing) {

			// Is the packing complete? Check if ticks to pack is 0, and if yes, move the
			// order to the dispatched list.
			if (ticksToPackOrder == 0) {
				wh.moveOrderFromAssignedToDispactedList(order);
				System.out.println(wh.getDispatchedOrderList().toString());
				packing = false;
				needsRobot = true;
				order = null;
				// If ticks to pack is not 0, decrease the ticks to pack by 1.
			} else {
				ticksToPackOrder--;
				if (ticksToPackOrder == 0) {
					wh.addToMessage(this.toString() + "is finished packing.");
				} else {
					wh.addToMessage(this.toString() + "is packing. Ticks left: " + ticksToPackOrder);
				}
			}
		}

	}
}
