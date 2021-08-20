package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PackingStationTest {

	@Test
	public void test() {
		
		//create new packing station
		PackingStation pkSt1 = new PackingStation("pkSt1");
		
		//test constructor
		assertEquals("pkSt1",pkSt1.getUID());
		assertEquals(true,pkSt1.needRobot());
		assertEquals(false, pkSt1.isPacking());
		assertEquals(0, pkSt1.getTicksToPackOrder());
		assertNull(pkSt1.getCurrentOrder());
		
		//test toString()
		assertEquals("Packing Station (pkSt1)", pkSt1.toString());
		
		
		//set up warehouse
		ArrayList<String> robots = new ArrayList<String>();
		robots.add("c0 r0 4 1");
		ArrayList<String> shelves= new ArrayList<String>();
		shelves.add("ss1 2 3");
		ArrayList<String> stations = new ArrayList<String>();
		stations.add("ps0 0 2");
		ArrayList<String> orders = new ArrayList<String>();
		orders.add("4 ss1");
		orders.add("6 ss1");
		
		Warehouse whs = new Warehouse(5,5,50,1,robots,shelves,stations,orders);		
			
		
		
		//test tick()
	}

}
