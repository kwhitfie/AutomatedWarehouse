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
				System.out.println("Width: " + width);
				System.out.println("H: " + height);
				System.out.println("C: " + capacity);
				System.out.println("cS: " + chargeSpeed);
				System.out.println("pR: " + podRobots.toString());
				System.out.println("Sh: " + shelves.toString());
				System.out.println("St: " + stations.toString());
				System.out.println("Or: " + orders.toString());
			}
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		SimulationFileReader s = new SimulationFileReader();
		s.readSimFile();
	}
}
