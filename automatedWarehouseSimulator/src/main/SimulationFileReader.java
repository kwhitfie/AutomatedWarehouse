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
import java.util.Scanner;

public class SimulationFileReader {

	private static Scanner stdIn;

	/**
	 * 
	 */
	public SimulationFileReader() {

	}

	/**
	 * 
	 * @return
	 */
	public Warehouse readSimFile(String file) {

		// The path for the Simulation file we want to read
		String path = file;

		// Intialises variables based on contents in sim file

		Integer width = 0;
		Integer height = 0;
		Integer capacity = 0;
		Integer chargeSpeed = 0;
		ArrayList<String> podRobots = new ArrayList<>();
		ArrayList<String> shelves = new ArrayList<>();
		ArrayList<String> stations = new ArrayList<>();
		ArrayList<String> orders = new ArrayList<>();

		try {
			// Creates a BufferedReader object to read the CSV file.
			BufferedReader br = new BufferedReader(new FileReader(path));
			// To store the read line.
			String line = null;

			// If the next line isn't null, read the next line. If null, stop reading.
			while ((line = br.readLine()) != null) {
				// Ignores first line of file
				if (!line.startsWith("format")) {

					// Read each line of the sim file, and depending on the first word in the line,
					// assign/add the lines contents to a variable/collection, which can later be
					// passed
					// to the Warehouse.

					if (line.startsWith("width")) {
						line = line.replaceFirst("width", "");
						line = line.strip();
						width = Integer.parseInt(line);
					}

					if (line.startsWith("height")) {
						line = line.replaceFirst("height", "");
						line = line.strip();
						height = Integer.parseInt(line);
					}

					if (line.startsWith("capacity")) {
						line = line.replaceFirst("capacity", "");
						line = line.strip();
						capacity = Integer.parseInt(line);
					}

					if (line.startsWith("chargeSpeed")) {
						line = line.replaceFirst("chargeSpeed", "");
						line = line.strip();
						chargeSpeed = Integer.parseInt(line);
					}

					if (line.startsWith("podRobot")) {
						line = line.replaceFirst("podRobot", "");
						line = line.strip();
						podRobots.add(line);
					}

					if (line.startsWith("shelf")) {
						line = line.replaceFirst("shelf", "");
						line = line.strip();
						shelves.add(line);
					}

					if (line.startsWith("station")) {
						line = line.replaceFirst("station", "");
						line = line.strip();
						stations.add(line);
					}

					if (line.startsWith("order")) {
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

	public static void printMessage() {
		System.out.println("Welcome to the automated warehouse simulator, please choose which file you wish to open: \n"
				+ "a) Bottom Stations \n" + "b) Bottom stations Old Mac \n" + "c) Bottom Stations Win \n"
				+ "d) One of Everything \n" + "e) Three of everything \n" + "f) Two Robots \n"
				+ "g) Two Robots Two Shelves \n" + "h) Two Shelves - bad \n" + "i) Two Shelves v2");
	}

	public static void main(String[] args) {

		stdIn = new Scanner(System.in);

		boolean hasInput = false;
		String fileName = "";

		printMessage();

		while (!hasInput) {

			String command = stdIn.nextLine().trim();
			switch (command) {
			case "a":
				fileName = "bottomStations.sim";
				hasInput = true;
				break;
			case "b":
				fileName = "bottomStationsOldMac.sim";
				hasInput = true;
				break;
			case "c":
				fileName = "bottomStationsWin.sim";
				hasInput = true;
				break;
			case "d":
				fileName = "oneOfEverything.sim";
				hasInput = true;
				break;
			case "e":
				fileName = "threeOfEverything.sim";
				hasInput = true;
				break;
			case "f":
				fileName = "twoRobots.sim";
				hasInput = true;
				break;
			case "g":
				fileName = "twoRobotsTwoShelves.sim";
				hasInput = true;
				break;
			case "h":
				fileName = "twoShelves-bad.sim";
				hasInput = true;
				break;
			case "i":
				fileName = "twoShelves-v2.sim";
				hasInput = true;
				break;
			default:
				System.out.println("Input not recognised, please try again");
			}
		}

		SimulationFileReader s = new SimulationFileReader();
		Warehouse wh = s.readSimFile(fileName);

		// Tests the warehouse tick all objects method.
		// wh.tickAllObjects();
		// wh.getPS("ps0").orderRetrievedByRobot();
		// wh.tickAllObjects();
		// wh.tickAllObjects();
		// wh.tickAllObjects();
		// wh.tickAllObjects();
		// wh.tickAllObjects();

		// Tests the charging pod and whether it does update the correct robot.
		// System.out.println(wh.getChargingPod("c1").getUID());
		// System.out.println(wh.getRobot("r0").getBatteryStatus());
		// wh.getChargingPod("c0").chargeRobot("r0", wh);
		// System.out.println(wh.getRobot("r0").getBatteryStatus());

		// Tests PackingStation getNextOrder method
//		wh.getPS("ps0").getNextOrder(wh);
//		System.out.println("UAOR: " + wh.getPS("ps0").getCurrentOrder().getTicksToPack());
//		System.out.println("AOQ: " + wh.getAssignedOrderQueue().peek().getTicksToPack());
//		
//		wh.getPS("ps0").getNextOrder(wh);
//		System.out.println("UAOR: " + wh.getPS("ps0").getCurrentOrder().getTicksToPack());
//		System.out.println("AOQ: " + wh.getAssignedOrderQueue().peek().getTicksToPack());

	}
}
