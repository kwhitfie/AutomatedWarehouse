package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ChargingPodTest {

	@Test
	public void constructorTest() {
				
		//new charging pod 
		ChargingPod cPd1 = new ChargingPod("cPd1", 5, "rb1");
		
		//test constructor
		assertEquals("cPd1", cPd1.getUID());
		assertEquals(5, cPd1.getPOWER_UNITS_PER_TICK());
		assertEquals("rb1", cPd1.getAssignedRobotUID());
		
		//test toString()
		assertEquals("Charging Pod. UID: cPd1. Power units per tick: 5. Assigned robot UID: rb1", cPd1.toString());
		
		//set up warehouse
		ArrayList<String> robots = new ArrayList<String>();
		robots.add("c0 r0 4 1");
		ArrayList<String> shelves= new ArrayList<String>();
		shelves.add("ss1 2 3");
		ArrayList<String> stations = new ArrayList<String>();
		stations.add("ps0 0 2");
		ArrayList<String> orders = new ArrayList<String>();
		orders.add("3 ss1 ss1");
		
		Warehouse whs = new Warehouse(5,5,50,1,robots,shelves,stations,orders);
		
		//test charge robot    
		whs.getRobot("r0").setBatteryChargePercentage(5);
		whs.getChargingPod("c0").chargeRobot("r0", whs);
		assertEquals(6, whs.getRobot("r0").getBatteryStatus());
		
		
		
	}

}
