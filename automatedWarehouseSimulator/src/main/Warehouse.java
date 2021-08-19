package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * The Warehouse stores the objects of a simulation
 * in a grid and provides methods for these objects to 
 * be accessed and mutated.
 * 
 * It is a controller to the simulation, ensuring that
 * the simulation ends when it is either complete or has
 * crashed.
 * 
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 */

public class Warehouse {
	//hello
	private Queue<Order> unassignedOQ;
	private ArrayList<Order> assignedOL;
	private ArrayList<Order> dispatchedOL;
	private HashMap<Position, ArrayList<String>> grid;
	private ArrayList<PackingStation> packingStations;
	private ArrayList<Robot> robots;
	private ArrayList<ChargingPod> chargingPods;
	private ArrayList<StorageShelf> storageShelves;
	private int x;
	private int y;
	private String message;
	private ArrayList<String> log;
	private int tick;
	private boolean isRunning = true;

	/**
	 * Constructor for a Warehouse object.
	 *   
	 * @param width - grid width
	 * @param height - grid height
	 * @param capacity - Robot battery capacity
	 * @param chargeSpeed - ChargingPod charge speed
	 * @param podRobots - Robot and ChargingPod UIDs
	 * @param shelves - StorageShelf UIDs
	 * @param stations - PackingStation UIDs
	 * @param orders - Order details
	 */
	public Warehouse(int width, int height, Integer capacity, Integer chargeSpeed, ArrayList<String> podRobots,
			ArrayList<String> shelves, ArrayList<String> stations, ArrayList<String> orders) {
		
		/*
		 *  The constructor takes multiple parameters, and uses these
		 *  parameters to set up the warehouse.
		 *  
		 *  It creates ArrayLists to store the ...
		 *  
		 *   - StorageShelf(s) 
		 *   - PackingStations 
		 *   - Robots
		 *   - ChargingPods
		 *   
		 *  that a Warehouse will contain.
		 *  
		 *  It next takes the parameters to 
		 *   - create the Warehouses unassigned order queue using orders (and the assigned and dispatched queues).
		 *   - create the Warehouses grid using width and height.
		 *   - create the Robots and Charging pods using capacity, chargeSpeed, podRobots.
		 *   - create the StorageShelf(s) using shelves.
		 *   - create the Orders using orders, and storing them into the unassigned order queue.
		 *   - initialise the User Interface.
		 */

		storageShelves = new ArrayList<StorageShelf>();
		packingStations = new ArrayList<PackingStation>();
		robots = new ArrayList<Robot>();
		chargingPods = new ArrayList<ChargingPod>();
		log = new ArrayList<String>();

		x = width;
		y = height;

		message = "Welcome to the automated warehouse simulator";
		log.add(message);
		
		System.out.println("Warehouse information:");

		System.out.println("Width: " + width);
		System.out.println("Height: " + height);
		System.out.println("Capacity: " + capacity);
		System.out.println("Charge Speed: " + chargeSpeed);
		System.out.println("Pod Robots: " + podRobots.toString());
		System.out.println("Shelves: " + shelves.toString());
		System.out.println("Stations: " + stations.toString());
		System.out.println("Orders: " + orders.toString());

		unassignedOQ = new LinkedList<Order>();
		assignedOL = new ArrayList<Order>();
		dispatchedOL = new ArrayList<Order>();
		grid = new HashMap<Position, ArrayList<String>>();

		for (String r : podRobots) {
			String[] parse = r.split("\\s+");
			ArrayList<String> s = new ArrayList<String>();
			s.add(parse[0]);
			s.add(parse[1]);
			Position pos = new Position(Integer.parseInt(parse[2]), Integer.parseInt(parse[3]));
			grid.put(pos, s);

			ChargingPod cPod = new ChargingPod(parse[0], chargeSpeed, parse[1]);
			Robot robot = new Robot(parse[1], capacity, pos, parse[0]);
			chargingPods.add(cPod);
			robots.add(robot);

		}

		for (String sh : shelves) {
			String[] parse = sh.split("\\s+");
			ArrayList<String> s = new ArrayList<String>();
			s.add(parse[0]);
			Position pos = new Position(Integer.parseInt(parse[1]), Integer.parseInt(parse[2]));
			grid.put(pos, s);

			StorageShelf shelf = new StorageShelf(parse[0]);
			storageShelves.add(shelf);
		}

		for (String st : stations) {
			String[] parse = st.split("\\s+");
			ArrayList<String> s = new ArrayList<String>();
			s.add(parse[0]);
			Position pos = new Position(Integer.parseInt(parse[1]), Integer.parseInt(parse[2]));
			grid.put(pos, s);

			PackingStation station = new PackingStation(parse[0]);
			packingStations.add(station);
		}

		for (String o : orders) {
			String[] parse = o.split("\\s+");
			int ticks = Integer.parseInt(parse[0]);
			ArrayList<String> s = new ArrayList<String>();
			for (int i = 1; i < parse.length; i++) {
				s.add(parse[i]);
			}
			Order order = new Order(s, ticks);
			unassignedOQ.add(order);

		}

		for (int x = 0; x <= width; x++) {
			for (int y = 0; y <= height; y++) {
				if (!grid.containsKey(getPositionFromCoordinates(x, y))) {
					ArrayList<String> al = new ArrayList<String>();
					Position p = new Position(x, y);
					grid.put(p, al);
				}
			}
		}
		UserInterface ui = new UserInterface(width, height, this);
		String[] arguments = new String[] { "123" };
		ui.main(arguments);
	}

	/**
	 * This method is called each tick of the simulation.
	 * 
	 * The method checks if the simulation has finished/crashed through the crashOrCompletedMonitor method. 
	 * 
	 * The method also calls the tick method of each object that in the Warehouse
	 * by looping through the ArrayLists containing these objects to ensure all
	 * objects perform their actions this tick.
	 * 
	 * Each object type is called sequentially, in this order:
	 * 
	 * PackingStations -> Robots
	 *
	 */
	public void tickAllObjects() {

		message = "";
		tick += 1;
		
		message += "Orders left: "+ (unassignedOQ.size() + assignedOL.size())+" Orders Dispatched: "+dispatchedOL.size()+"\n";

		
		// Go through each object arraylist. Call its tick method and passes itself as a param.
		for (int i = 0; i < packingStations.size(); i++) {
			packingStations.get(i).tick(this);
		}

		for (int i = 0; i < robots.size(); i++) {
			robots.get(i).tick(this);
		}

		if (!message.equals("")) {
			log.add("Tick " + tick + ": " + message);
		}

		if(crashOrCompletedMonitor()) {
			isRunning = false;
		}
	}

	/**
	 * Return a specific Robot from the Warehouse
	 * via it's UID as a parameter.
	 * 
	 * @param target Robot UID
	 * @return a Robot object
	 */
	public Robot getRobot(String UID) {
		for (Robot r : robots) {
			if (r.getUID().contentEquals(UID)) {
				return r;
			}
		}
		return null;
	}

	/**
	 * Return a specified charging pod
	 * via it's UID as a parameter.
	 * 
	 * @param target ChargingPod UID
	 * @return a ChargingPod object.
	 */
	public ChargingPod getChargingPod(String UID) {
		for (ChargingPod cp : chargingPods) {
			if (cp.getUID().contentEquals(UID)) {
				return cp;
			}
		}
		return null;
	}

	public void addToMessage(String s) {
		message += s + "\n";
	}

	/**
	 * Return a objects Position in the grid
	 * via it's UID.
	 * 
	 * @param target Objects UID
	 * @return a Position object
	 */
	public Position getPositionFromUID(String UID) {
		for (Position p : getGrid().keySet()) {
			for (String s : getGrid().get(p)) {
				if (s.equals(UID)) {
					return p;
				}
			}
		}
		return null;
	}

	/**
	 * Return a objects Position in the grid
	 * via it's coordinates (x and y).
	 * 
	 * @param target Objects coordinates (x and y)
	 * @return a Position object
	 */
	public Position getPositionFromCoordinates(int x, int y) {
		for (Position p : getGrid().keySet()) {
			if (p.getX() == x && p.getY() == y) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Return a specified PackingStation
	 * via it's UID as a parameter.
	 * 
	 * @param target PackingStation UID
	 * @return a PackingStation object.
	 */
	public PackingStation getPackingStation(String UID) {
		for (PackingStation ps : packingStations) {
			if (ps.getUID().contentEquals(UID)) {
				return ps;
			}
		}
		return null;
	}

	/**
	 * Return the next order object from the
	 * unassigned order queue.
	 * 
	 * This method removes the returned order from the queue,
	 * and adds it to the assigned order list.
	 * 
	 * @return order object
	 */
	public Order getNextUnassignedOrder() {
		Order o = unassignedOQ.poll();
		assignedOL.add(o);
		return o;
	}

	/**
	 * This method checks if the order queue is empty.
	 * 
	 * True if empty, false if not empty.
	 * 
	 * @return boolean
	 */
	public boolean isUnassignedOrderQueueEmpty() {
		if (unassignedOQ.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method returns the assigned order list.
	 * 
	 * @return order object
	 */
	public ArrayList<Order> getAssignedOrderList() {
		return assignedOL;
	}

	/**
	 * This method returns the dispatched order list.
	 * 
	 * @return order object
	 */
	public ArrayList<Order> getDispatchedOrderList() {
		return dispatchedOL;
	}

	/**
	 * Moves a specified order from the assigned order list 
	 * to the dispatched order list.
	 * @param order - specified order
	 */
	public void moveOrderFromAssignedToDispactedList(Order order) {
		assignedOL.remove(order);
		dispatchedOL.add(order);
	}

	/**
	 * Moves a specified object via the UID to a new cell in the grid, and
	 * removes the object from the old cell.
	 * 
	 * @param oldX - current x position
	 * @param oldY - current y position
	 * @param newX - desired x position
	 * @param newY - desired y position
	 * @param UID - object UID to be moved
	 * @return the new Position of the object.
	 */
	public Position moveObjectToCell(int oldX, int oldY, int newX, int newY, String UID) {
		grid.get(getPositionFromCoordinates(oldX, oldY)).remove(UID);
		grid.get(getPositionFromCoordinates(newX, newY)).add(UID);
		return getPositionFromCoordinates(newX, newY);
	}

	/**
	 * Checks if a Robot in the Warehouse is 
	 * available to perform a job.
	 * 
	 * @return a RobotUID is one is available, or null.
	 */
	public String checkRobotAvailability(Order order) {
		String robotUID = null;
		for (Robot r : robots) {
			if (r.checkIfPossibleToAcceptJob(this, order)) {
				robotUID = r.getUID();
				break;
			}
		}
		return robotUID;
	}

	/**
	 * Checks if the simulation has finished by checking if the 
	 * unassigned order queue and assigned order list is empty, or
	 * checks if the simulation has crashed by checking if two robots
	 * are in the same grid cell or if a robot has run out of battery.
	 * 
	 * @return boolean - true if crashed/finished, false if not. 
	 */
	private boolean crashOrCompletedMonitor() {
		if (unassignedOQ.isEmpty() && assignedOL.isEmpty()) {
			addToMessage("No more orders. Stopping simulation.");
			log.add(message);
			return true;
		}

		ArrayList<Position> positionArray = new ArrayList<Position>();
		Set<Position> positionSet = new HashSet<Position>();
		for (Robot r : robots) {
			if (r.getBatteryStatus() <= 0) {
				addToMessage(r+" has run out of battery. Stopping simulation.");
				log.add(message);
				return true;
			}
			
			positionArray.add(getPositionFromUID(r.getUID()));
			positionSet.add(getPositionFromUID(r.getUID()));
		}

		if (positionArray.size() != positionSet.size()) {
			addToMessage("Robots have crashed. Stopping simulation.");
			log.add(message);
			return true;
		}

		return false;
	}

	/**
	 * Returns the grid.
	 * @return the grid
	 */
	public HashMap<Position, ArrayList<String>> getGrid() {
		return grid;
	}

	/**
	 * Returns the width/x value of the grid
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the height/y value of the grid
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the message field that contains the 
	 * details of what has occured in the Warehouse
	 * for the current tick.
	 * @return String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Returns the log field that contains the 
	 * details of what has occured in the Warehouse
	 * for the each tick.
	 * @return String
	 */
	public ArrayList<String> getLog() {
		return log;
	}
	
	/**
	 * Returns whether the Warehouse is currently running or not
	 * through the isRunning field.
	 * @return boolean
	 */
	public boolean isRunning() {
		return isRunning;
	}
	
	/**
	 * Sets the isRunning field to determine whether the 
	 * Warehouse is running or not.
	 * 
	 * Setting this to false stops the simulation.
	 * 
	 * @param isRunning
	 */
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	/**
	 * Returns the StorageShelves in the Warehouse
	 * @return  ArrayList<StorageShelf>
	 */
	public ArrayList<StorageShelf> getStorageShelves(){
		return storageShelves;
	}
	
	/**
	 * Returns the PackingStations in the Warehouse
	 * @return  ArrayList<PackingStation>
	 */
	public ArrayList<PackingStation> getPackingStations(){
		return packingStations;
	}
	
	/**
	 * Returns the Robots in the Warehouse
	 * @return ArrayList<Robot>
	 */
	public ArrayList<Robot> getRobots(){
		return robots;
	}
	
	/**
	 * Returns the ChargingPods in the Warehouse
	 * @return ArrayList<ChargingPod>
	 */
	public ArrayList<ChargingPod> getChargingPods(){
		return chargingPods;
	}
	
	/**
	 * Return the unassigned order queue 
	 * @return Queue<Order>
	 */
	public Queue<Order> getUnassignedOQ(){
		return unassignedOQ;
	}
	
	/**
	 * Return the dispatched order on the specified index.
	 * @param x - index
	 * @return Order 
	 */
	public Order getDispatchedOrder(int x){
		return dispatchedOL.get(x);
	}
	
	
}
