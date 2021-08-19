package main;

import static org.junit.Assert.*;

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
		
		//set up solution robot=rb1
		
		//test charge robot
		
	}

}
