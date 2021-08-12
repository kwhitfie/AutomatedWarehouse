package main;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;

/**
 * Details here
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class Warehouse implements Tick{
	
	private OrderQueue oq;
	private HashMap<Position, ArrayList<String>> grid;
	private ArrayList<PackingStation> packingStations;
	private ArrayList<Robot> robots;
	private ArrayList<ChargingPod> chargingPods;
	private ArrayList<StorageShelf> storageShelves;
	
	/**
	 * 
	 */
	public Warehouse(int width, int height, Integer capacity, Integer chargeSpeed,
			ArrayList<String> podRobots, ArrayList<String> shelves, ArrayList<String> stations, ArrayList<String> orders) {
		
		storageShelves = new ArrayList<StorageShelf>();
		packingStations = new ArrayList<PackingStation>();
		robots = new ArrayList<Robot>();
		chargingPods = new ArrayList<ChargingPod>();
		
		System.out.println("Width: " + width);
		System.out.println("Height: " + height);
		System.out.println("Capacity: " + capacity);
		System.out.println("cCharge Speed: " + chargeSpeed);
		System.out.println("Pod Robots: " + podRobots.toString());
		System.out.println("Shelves: " + shelves.toString());
		System.out.println("Stations: " + stations.toString());
		System.out.println("Orders: " + orders.toString());
		
		// TODO Auto-generated constructor stub
		oq = new OrderQueue();
		grid = new HashMap<Position, ArrayList<String>>();
		
		//Hello Kayley, instead of the Warehouse getting a single output ArrayList, the SimulationFileReader
		//Hello Alfie o/
		//reads the sim file, and breaks it down by storing each Object type into it's own variable/collection.
		//This saves the hassle of having to loop through the arraylist. 
		
		//Please could you look to set up the grid when you have the time tomorrow using the width and height parameters that is passed
		//to the Warehouse. Thanks. We'll look to start creating the objects and adding them to the grid once the grid is properly set up. 
		
		
		
		
		// This currently runs the main after the constructor so the size isnt correct, need to figure out how to make it not do that
		
		for(String r: podRobots) {
			String[] parse = r.split("\\s+");
			ArrayList<String> s = new ArrayList<String>();
			s.add(parse[0]);
			s.add(parse[1]);
			Position pos = new Position(Integer.parseInt(parse[2]),Integer.parseInt(parse[3]));
			grid.put(pos, s);
			
			ChargingPod cPod = new ChargingPod(parse[0]);
			Robot robot = new Robot(parse[1]);
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
			
		}
		
		System.out.println();
		System.out.println("Grid information: "+grid);
		System.out.println();
		System.out.println("Packing Stations:"+packingStations);
		System.out.println("Robots:"+robots);
		System.out.println("Charging Pods:"+chargingPods);
		System.out.println("Storage Shelves:"+storageShelves);
		
		UserInterface ui = new UserInterface(width, height);
		String[] arguments = new String[] {"123"};
		ui.main(arguments);
		//bruh bruh bruh bruh bruh bruh bruh bruh bruh bruh bruh bruh
	}
	
	/**
	 * 
	 */
	@Override
	public void Tick() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * 
	 * @return
	 */
	public OrderQueue getOrderQueue() {
		return oq;
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
	
	/**
	 * 
	 * @param UID
	 */
	public void setGridSquare(String UID) {
		
	}
	
	/**
	 * 
	 */
	public void tickAll() {
		//tick tick tick tick tick tick tick tick tick tick tick 
	}
}
