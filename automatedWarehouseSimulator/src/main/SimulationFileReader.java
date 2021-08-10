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
		
		try {
			//Creates a BufferedReader object to read the CSV file.
			BufferedReader br = new BufferedReader(new FileReader(path));
			//To store the read line.
			String line = null;
			
			//If the next line isn't null, read the next line. If null, stop reading.
			while((line = br.readLine()) != null){
				String[] values = line.split(" ");
				String test = "";
				for(int i = 0; i<values.length; i++) {
					test += values[i] + " ";
				}
				System.out.println(test);
				
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
