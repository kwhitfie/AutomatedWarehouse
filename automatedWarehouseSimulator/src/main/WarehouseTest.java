package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * This class tests the methods of the Warehouse class
 * 
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class WarehouseTest {

	@Test
	public void test() {
		
		//Create robots, shelves, packing stations and orders
		ArrayList<String> robots = new ArrayList<String>();
		robots.add("c0 r0 4 1");
		robots.add("c1 r1 4 3");
		ArrayList<String> shelves= new ArrayList<String>();
		shelves.add("ss1 2 3");
		shelves.add("ss0 2 1");
		ArrayList<String> stations = new ArrayList<String>();
		stations.add("ps0 0 2");
		ArrayList<String> orders = new ArrayList<String>();
		orders.add("3 ss1 ss0");
		orders.add("2 ss0");
		orders.add("15 ss1 ss0");
		
		//Create new warehouse
		Warehouse whs = new Warehouse(5,5,50,1,robots,shelves,stations,orders);
		
		//Constructor Tests
		
		//Robots
		assertEquals("r0", whs.getRobots().get(0).getUID());
		assertEquals("r1", whs.getRobots().get(1).getUID());
		
		//Charging pods
		assertEquals("c0", whs.getChargingPods().get(0).getUID());
		assertEquals("c1", whs.getChargingPods().get(1).getUID());
		
		//Storage shelves
		assertEquals("ss1", whs.getStorageShelves().get(0).getUID());
		assertEquals("ss0", whs.getStorageShelves().get(1).getUID());
		
		//Packing stations
		assertEquals("ps0", whs.getPackingStations().get(0).getUID());
		
		
		//Orders  
		assertEquals(0, whs.getUnassignedOQ().size());
		assertEquals(0, whs.getAssignedOrderList().size());
		assertEquals(3, whs.getDispatchedOrderList().size());
		
		//Test getRobot()
		assertEquals("r1", whs.getRobot("r1").getUID());
		assertEquals("r0", whs.getRobot("r0").getUID());
		
		//Test getChargingPod()
		assertEquals("c0", whs.getChargingPod("c0").getUID());
		assertEquals("c1", whs.getChargingPod("c1").getUID());
		
		//Test getPackingStation()
		assertEquals("ps0", whs.getPackingStation("ps0").getUID());
		
		//Test getNextUnassignedOrder()
		assertNull(whs.getNextUnassignedOrder());
		
		//Test isUnassignedOrderQueueEmpty()
		assertEquals(true, whs.isUnassignedOrderQueueEmpty());		
		
		//Test getAssignedOrderList()
		ArrayList<String> al = new ArrayList<>();
		al.add("ss1");
		Order tstOrder = new Order(al, 5);
		whs.getAssignedOrderList().add(tstOrder);
		
		//Test moveOrderFromAssignedToDispactedList()
		whs.moveOrderFromAssignedToDispactedList(tstOrder);
		assertEquals(tstOrder, whs.getDispatchedOrder(3));
		
		//Test getDispatchedOrderList()
		assertEquals(4, whs.getDispatchedOrderList().size());
		
		//Test checkRobotAvailability()
		assertEquals("r0", whs.checkRobotAvailability(tstOrder));
		
		
		//Test getPositionFromUID()
		assertEquals(0, whs.getPositionFromUID("ps0").getX());
		assertEquals(2, whs.getPositionFromUID("ps0").getY());
		
		//Test getPositionFromCoordinates()
		assertEquals(4, whs.getPositionFromCoordinates(4,5).getX());
		assertEquals(5, whs.getPositionFromCoordinates(4,5).getY());
		
		//Test moveObjectToCell()	
		whs.moveObjectToCell(0,2,1,3,"ps0");
		assertEquals(3, whs.getPositionFromUID("ps0").getY());
		assertEquals(1, whs.getPositionFromUID("ps0").getX());
		
		
		//Test tickAllObjects()
		whs.tickAllObjects();
		assertEquals(88, whs.getTick());		
		
	}

}
