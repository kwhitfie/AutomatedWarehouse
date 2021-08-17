package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Details here
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class Warehouse {
	
	private Queue<Order> unassignedOQ;
	private ArrayList<Order> assignedOQ;
	private ArrayList<Order> dispatchedOQ;
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
	
	/**
	 * 
	 */
	public Warehouse(int width, int height, Integer capacity, Integer chargeSpeed,
			ArrayList<String> podRobots, ArrayList<String> shelves, ArrayList<String> stations, ArrayList<String> orders) {
		
		storageShelves = new ArrayList<StorageShelf>();
		packingStations = new ArrayList<PackingStation>();
		robots = new ArrayList<Robot>();
		chargingPods = new ArrayList<ChargingPod>();
		log = new ArrayList<String>();
		
		x = width;
		y = height;
		
		message = "Welcome to the automated warehouse simulator";
		log.add(message);
		
		System.out.println("Width: " + width);
		System.out.println("Height: " + height);
		System.out.println("Capacity: " + capacity);
		System.out.println("cCharge Speed: " + chargeSpeed);
		System.out.println("Pod Robots: " + podRobots.toString());
		System.out.println("Shelves: " + shelves.toString());
		System.out.println("Stations: " + stations.toString());
		System.out.println("Orders: " + orders.toString());
		
		//comment
		
		// TODO Auto-generated constructor stub
		unassignedOQ = new LinkedList<Order>();
		assignedOQ = new ArrayList<Order>();
		dispatchedOQ = new ArrayList<Order>();
		grid = new HashMap<Position, ArrayList<String>>();
		
		//Hello Kayley, instead of the Warehouse getting a single output ArrayList, the SimulationFileReader
		//Hello Alfie o/
		//reads the sim file, and breaks it down by storing each Object type into it's own variable/collection.
		//This saves the hassle of having to loop through the arraylist. 
		
		//Please could you look to set up the grid when you have the time tomorrow using the width and height parameters that is passed
		//to the Warehouse. Thanks. We'll look to start creating the objects and adding them to the grid once the grid is properly set up. 
		

		
		for(String r: podRobots) {
			String[] parse = r.split("\\s+");
			ArrayList<String> s = new ArrayList<String>();
			s.add(parse[0]);
			s.add(parse[1]);
			Position pos = new Position(Integer.parseInt(parse[2]),Integer.parseInt(parse[3]));
			grid.put(pos, s);
			
			ChargingPod cPod = new ChargingPod(parse[0],chargeSpeed, parse[1]);
			Robot robot = new Robot(parse[1],capacity,pos);
			chargingPods.add(cPod);
			robots.add(robot);
			
		}
		
		for(String sh: shelves) {
			String[] parse = sh.split("\\s+");
			ArrayList<String> s = new ArrayList<String>();
			s.add(parse[0]);
			Position pos = new Position(Integer.parseInt(parse[1]),Integer.parseInt(parse[2]));
			grid.put(pos, s);
			
			StorageShelf shelf = new StorageShelf(parse[0]);
			storageShelves.add(shelf);
		}
		
		for(String st: stations) {
			String[] parse = st.split("\\s+");
			ArrayList<String> s = new ArrayList<String>();
			s.add(parse[0]);
			Position pos = new Position(Integer.parseInt(parse[1]),Integer.parseInt(parse[2]));
			grid.put(pos, s);
			
			PackingStation station = new PackingStation(parse[0]);
			packingStations.add(station);
		}
		
		
		for(String o: orders) {
			String[] parse = o.split("\\s+");
			int ticks = Integer.parseInt(parse[0]);
			ArrayList<String> s = new ArrayList<String>();
			for(int i=1;i<parse.length;i++) {
				s.add(parse[i]);
			}
			Order order = new Order(s,ticks);
			unassignedOQ.add(order);
			
		}
		
		for(int x=0; x<=width; x++) {
		for(int y=0; y<=height; y++) {
			if(!grid.containsKey(getPositionFromCoordinates(x,y))) {
			ArrayList<String> al = new ArrayList<String>();
			Position p = new Position(x,y);
			grid.put(p, al);
			}
		}
	}
		
		System.out.println();
		System.out.println("Grid information: "+grid);
		System.out.println();
		System.out.println("Packing Stations:"+packingStations);
		System.out.println("Robots:"+robots);
		System.out.println("Charging Pods:"+chargingPods);
		System.out.println("Storage Shelves:"+storageShelves);
		
		UserInterface ui = new UserInterface(width, height,this);
		String[] arguments = new String[] {"123"};
		ui.main(arguments);
	}
	
	/**
	 * 
	 */
	public void tickAllObjects() {
		
		message = "";
		tick += 1;
		
		//Go through each object arraylist and call its ticket method and passes itself.
		for(int i = 0; i<packingStations.size(); i++) {
			packingStations.get(i).tick(this);
		}
		
		for(int i = 0; i<chargingPods.size(); i++) {
			chargingPods.get(i).tick(this);
		}
		
		for(int i = 0; i<robots.size(); i++) {
			robots.get(i).tick(this);
		}
		
		log.add("Tick "+tick+": "+message);
		System.out.println(log);
	}
	
	/**
	 * Return a specified robot. 
	 */
	public Robot getRobot (String UID) {
		for(Robot r : robots) {
			if(r.getUID().contentEquals(UID)) {
				return r;
			}
		}
		return null;
	}
	
	/**
	 * Return a specified charging pod. 
	 */
	public ChargingPod getChargingPod(String UID) {
		for(ChargingPod cp : chargingPods) {
			if(cp.getUID().contentEquals(UID)) {
				return cp;
			}
		}
		return null;
	}
	
	public void addToMessage(String s) {
		message += "\n"+ s;
	}
	
	/**
	 * 
	 * @param UID
	 * @return
	 */
	public Position getPositionFromUID(String UID) {
		for(Position p: getGrid().keySet()) {
			for(String s: getGrid().get(p)) {
				if(s.equals(UID)) {
					return p;
				}
			}
		}
		return null;
	}
	
	public Position getPositionFromCoordinates(int x, int y) {
		for(Position p: getGrid().keySet()) {
			if(p.getX()==x && p.getY()==y) {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * Return a specified packingStation 
	 * Might not be needed outside of testing.
	 */
	public PackingStation getPS(String UID) {
		for(PackingStation ps : packingStations) {
			if(ps.getUID().contentEquals(UID)) {
				return ps;
			}
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public Order getNextUnassignedOrder() {
		Order o = unassignedOQ.poll();
		assignedOQ.add(o);
		return o;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Order> getAssignedOrderList() {
		return assignedOQ;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Order> getDispatchedOrderList() {
		return dispatchedOQ;
	}
	
	/**
	 * 
	 * @return
	 */
	public void moveOrderFromAssignedToDispactedList(Order order) {
		assignedOQ.remove(order);
		dispatchedOQ.add(order);
	}
	
	/**
	 * 
	 */
	public Position moveObjectToCell(int oldX, int oldY, int newX, int newY, String UID) {
		grid.get(getPositionFromCoordinates(oldX, oldY)).remove(UID);
		grid.get(getPositionFromCoordinates(newX, newY)).add(UID);
		return getPositionFromCoordinates(newX, newY);
	}
	
	/**
	 * Check if a robot is available to perform a job for the PackingStation
	 */
	public String checkRobotAvailability() {
		String robotUID = null;
		for(Robot r : robots) {
			if(r.checkIfPossibleToAcceptJob(this)) {
				robotUID = r.getUID();
				break;
			}
		}
		return robotUID;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean crashMonitor() {
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public Position getGridSquare() {
		return null;
	}
	
	public HashMap<Position, ArrayList<String>> getGrid(){
		return grid;
	}
	
	/**
	 * 
	 * @param UID
	 */
	public void setGridSquare(String UID) {
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getMessage() {
		return message;
	}

	public ArrayList<String> getLog() {
		return log;
	}
}
