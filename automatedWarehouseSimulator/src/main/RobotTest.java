package main;

import static org.junit.Assert.*;

import java.util.Queue;

import org.junit.Test;

public class RobotTest {

	@Test
	public void test() {
		
		//create new positions
		Position pst1 = new Position(5,7);
		Position pst2 = new Position(12,4);
		Position pst3 = new Position(7,9);
		Position pst4 = new Position(3,2);
		
		//create new robot 
		Robot rb1 = new Robot("rb1", 50, pst1, "cPd1");
		
		//test constructor
		assertEquals("cPd1", rb1.getChargingPodUID());
		assertEquals(false, rb1.hasItem());
		assertEquals(false, rb1.isBusy());
		assertEquals(50, rb1.getBatteryStatus());
		assertEquals(50, rb1.getMaxBattery());
		assertEquals(pst1, rb1.getPosition());
		assertNull(rb1.getDestination());
		assertEquals(true, rb1.getShelves().isEmpty());
		assertEquals(false, rb1.needsToCharge());
		assertEquals(false, rb1.chargeToFull());
		assertNull(rb1.getRequestingPackingStationUID());
		assertNull(rb1.getTargetShelfPosition());
		
	
		//test getManhattanDistance()
		assertEquals(10, rb1.getManhattanDistance(pst1,pst2));
		assertEquals(4, rb1.getManhattanDistance(pst1,pst3));
		assertEquals(10, rb1.getManhattanDistance(pst3,pst2));
		
		//test setBatteryChargePercentage()
		rb1.setBatteryChargePercentage(20);
		assertEquals(20, rb1.getBatteryStatus());
		
		
		//test toString()
		assertEquals("Robot(rb1)", rb1.toString());
		
		
		//Require Warehouse
		
		
		//test move()
		//test doesSquareHaveRobot()	
		//test checkIfPossibleToAcceptJob()
		//test getDestinationPosition()
		//test acceptOrder()
		//test doesRobotNeedToCharge()
		//test tick()
		//test batteryCostPerTick()
		
	}

}
