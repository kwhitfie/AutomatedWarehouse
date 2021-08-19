package main;

import static org.junit.Assert.*;

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
		
		//test toString()
		assertEquals("Packing Station (pkSt1)", pkSt1.toString());
		
		
		//set up solution
		
		//test getNextOrder()
		//test getCurrentOrder()
		//test orderRetrivedByRobot()
		//test tick()
	}

}
