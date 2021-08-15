package main;

/**
 * Details here
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SimulationFileReader {
	
	/**
	 * 
	 */
	public SimulationFileReader() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return
	 */
	public Warehouse readSimFile() {

		//The path for the Simulation file we want to read
		String path = "twoRobotsTwoShelves.sim";
		
		//Intialises variables based on contents in sim file
		
		Integer width = 0;
		Integer height = 0;
		Integer capacity = 0;
		Integer chargeSpeed = 0;
		ArrayList<String> podRobots = new ArrayList<>();
		ArrayList<String> shelves = new ArrayList<>();
		ArrayList<String> stations = new ArrayList<>();
		ArrayList<String> orders = new ArrayList<>(); 
		
		
		try {
			//Creates a BufferedReader object to read the CSV file.
			BufferedReader br = new BufferedReader(new FileReader(path));
			//To store the read line.
			String line = null;
			
			//If the next line isn't null, read the next line. If null, stop reading.
			while((line = br.readLine()) != null) {
				//Ignores first line of file
				if(!line.startsWith("format")){
					
					//Read each line of the sim file, and depending on the first word in the line,
					//assign/add the lines contents to a variable/collection, which can later be passed 
					//to the Warehouse. 
					
					if(line.startsWith("width")) {
						line = line.replaceFirst("width", "");
						line = line.strip();
						width = Integer.parseInt(line);
					}
					
					if(line.startsWith("height")) {
						line = line.replaceFirst("height", "");
						line = line.strip();
						height = Integer.parseInt(line);
					}
					
					if(line.startsWith("capacity")) {
						line = line.replaceFirst("capacity", "");
						line = line.strip();
						capacity = Integer.parseInt(line);
					}
					
					if(line.startsWith("chargeSpeed")) {
						line = line.replaceFirst("chargeSpeed", "");
						line = line.strip();
						chargeSpeed = Integer.parseInt(line);
					}
					
					if(line.startsWith("podRobot")) {
						line = line.replaceFirst("podRobot", "");
						line = line.strip();
						podRobots.add(line);
					}
					
					if(line.startsWith("shelf")) {
						line = line.replaceFirst("shelf", "");
						line = line.strip();
						shelves.add(line);
					}
					
					if(line.startsWith("station")) {
						line = line.replaceFirst("station", "");
						line = line.strip();
						stations.add(line);
					}
					
					if(line.startsWith("order")) {
						line = line.replaceFirst("order", "");
						line = line.strip();
						orders.add(line);
					}	
				}
			}
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Warehouse(width, height, capacity, chargeSpeed, podRobots, shelves, stations, orders);
	}
	
	public static void main(String[] args) {
		SimulationFileReader s = new SimulationFileReader();
		Warehouse wh = s.readSimFile();
		
		//Tests the warehouse tick all objects method.
		wh.tickAllObjects();
		//wh.getPS("ps0").orderRetrievedByRobot();
		wh.tickAllObjects();
		wh.tickAllObjects();
		wh.tickAllObjects();
		wh.tickAllObjects();
		wh.tickAllObjects();
		wh.tickAllObjects();
		wh.tickAllObjects();
		//wh.getPS("ps0").orderRetrievedByRobot();
		wh.tickAllObjects();
		wh.tickAllObjects();
		wh.tickAllObjects();
		wh.tickAllObjects();
	
		//Tests the charging pod and whether it does update the correct robot.
		System.out.println(wh.getChargingPod("c1").getUID());
		System.out.println(wh.getRobot("r0").getBatteryStatus());
		wh.getChargingPod("c0").chargeRobot("r0", wh);
		System.out.println(wh.getRobot("r0").getBatteryStatus());
		
		//Tests PackingStation getNextOrder method
//		wh.getPS("ps0").getNextOrder(wh);
//		System.out.println("UAOR: " + wh.getPS("ps0").getCurrentOrder().getTicksToPack());
//		System.out.println("AOQ: " + wh.getAssignedOrderQueue().peek().getTicksToPack());
//		
//		wh.getPS("ps0").getNextOrder(wh);
//		System.out.println("UAOR: " + wh.getPS("ps0").getCurrentOrder().getTicksToPack());
//		System.out.println("AOQ: " + wh.getAssignedOrderQueue().peek().getTicksToPack());
		
	}
}
