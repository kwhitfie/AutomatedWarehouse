package main;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Queue;

import org.junit.Test;

/**
 * This class tests the methods of the Robot class
 * 
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class RobotTest {

	@Test
	public void test() {
		
		//Create new positions
		Position pst1 = new Position(5,7);
		Position pst2 = new Position(12,4);
		Position pst3 = new Position(7,9);
		
		//Create new robot 
		Robot rb1 = new Robot("rb1", 50, pst1, "cPd1");
		
		//Constructor Test
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
		
	
		//Test getManhattanDistance()
		assertEquals(10, rb1.getManhattanDistance(pst1,pst2));
		assertEquals(4, rb1.getManhattanDistance(pst1,pst3));
		assertEquals(10, rb1.getManhattanDistance(pst3,pst2));
		
		//Test setBatteryChargePercentage()
		rb1.setBatteryChargePercentage(20);
		assertEquals(20, rb1.getBatteryStatus());
		
		
		//Test toString()
		assertEquals("Robot(rb1)", rb1.toString());
		
		
		//Create Warehouse
		ArrayList<String> robots = new ArrayList<String>();
		robots.add("c0 r0 4 1");
		ArrayList<String> shelves= new ArrayList<String>();
		shelves.add("ss1 2 3");
		ArrayList<String> stations = new ArrayList<String>();
		stations.add("ps0 0 2");
		ArrayList<String> orders = new ArrayList<String>();
		orders.add("3 ss1 ss1");
		
		Warehouse whs = new Warehouse(5,5,50,1,robots,shelves,stations,orders);
		
		//Test move()
		whs.getRobot("r0").move(whs.getPositionFromCoordinates(3,2), whs);
		assertEquals(3, whs.getPositionFromUID("r0").getX());
		assertEquals(2, whs.getPositionFromUID("r0").getY());
		
		//Test doesSquareHaveRobot()	
		assertEquals(true, whs.getRobot("r0").doesSquareHaveRobot(whs.getPositionFromCoordinates(3,2), whs));
		assertEquals(false, whs.getRobot("r0").doesSquareHaveRobot(whs.getPositionFromCoordinates(1,2), whs));
		
		//Test checkIfPossibleToAcceptJob() and acceptOrder()
		ArrayList<String> al = new ArrayList<>();
		al.add("ss1");
		Order tstOrder = new Order(al,5);
		assertEquals(true, whs.getRobot("r0").checkIfPossibleToAcceptJob(whs, tstOrder));
		
		whs.getRobot("r0").acceptOrder(tstOrder, "ps0");
		assertEquals("ss1", whs.getRobot("r0").getShelves().peek());
		assertEquals(false, whs.getRobot("r0").checkIfPossibleToAcceptJob(whs, tstOrder));
		
		//Test batteryCostPerTick()
		assertEquals(1, whs.getRobot("r0").batteryCostPerTick());
		
		whs.moveObjectToCell(3,2,2,3,"r0");
		whs.getRobot("r0").tick(whs);
		assertEquals(2, whs.getRobot("r0").batteryCostPerTick());
		
		//Test doesRobotNeedToCharge()
		assertEquals(false, whs.getRobot("r0").doesRobotNeedToCharge(whs, whs.getPositionFromCoordinates(3,2)));
		whs.getRobot("r0").setBatteryChargePercentage(1);
		assertEquals(true, whs.getRobot("r0").doesRobotNeedToCharge(whs, whs.getPositionFromCoordinates(5,5)));
		
	}

}
