package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class WarehouseTest {

	@Test
	public void test() {
		
		//create robots, shelves, packing stations and orders
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
		
		//create new warehouse
		Warehouse whs = new Warehouse(5,5,50,1,robots,shelves,stations,orders);
		
		//test constructor 
		
		//robots
		assertEquals("r0", whs.getRobots().get(0).getUID());
		assertEquals("r1", whs.getRobots().get(1).getUID());
		
		//charging pods
		assertEquals("c0", whs.getChargingPods().get(0).getUID());
		assertEquals("c1", whs.getChargingPods().get(1).getUID());
		
		//storage shelves
		assertEquals("ss1", whs.getStorageShelves().get(0).getUID());
		assertEquals("ss0", whs.getStorageShelves().get(1).getUID());
		
		//packing stations
		assertEquals("ps0", whs.getPackingStations().get(0).getUID());
		
		
		//orders  
		assertEquals(0, whs.getUnassignedOQ().size());
		assertEquals(0, whs.getAssignedOrderList().size());
		assertEquals(3, whs.getDispatchedOrderList().size());
		
		//test getRobot()
		assertEquals("r1", whs.getRobot("r1").getUID());
		assertEquals("r0", whs.getRobot("r0").getUID());
		
		//test getChargingPod()
		assertEquals("c0", whs.getChargingPod("c0").getUID());
		assertEquals("c1", whs.getChargingPod("c1").getUID());
		
		//test getPackingStation()
		assertEquals("ps0", whs.getPackingStation("ps0").getUID());
		
		//test getNextUnassignedOrder()
		assertNull(whs.getNextUnassignedOrder());
		
		//test isUnassignedOrderQueueEmpty()
		assertEquals(true, whs.isUnassignedOrderQueueEmpty());
		
		//test getAssignedOrderList()
		ArrayList<String> al = new ArrayList<>();
		al.add("ss1");
		Order tstOrder = new Order(al, 5);
		whs.getAssignedOrderList().add(tstOrder);
		
		//test moveOrderFromAssignedToDispactedList()
		whs.moveOrderFromAssignedToDispactedList(tstOrder);
		assertEquals(tstOrder, whs.getDispatchedOrder(3));
		
		//test getDispatchedOrderList()
		assertEquals(4, whs.getDispatchedOrderList().size());
		
		//test checkRobotAvailability()
		assertEquals("r0", whs.checkRobotAvailability(tstOrder));
		
		
		//test getPositionFromUID()
		//test getPositionFromCoordinates()
		//test moveObjectToCell()
	
		//test tickAllObjects()
		
		
		
		
		
		
		




	}

}
