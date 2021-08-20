package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * This class tests the methods of the Order class
 * 
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class OrderTest {

	@Test
	public void test() {
		
		//Create new Order object
		ArrayList<String> testAL = new ArrayList<>();
		testAL.add("UID1");
		testAL.add("UID2");
		testAL.add("UID3");
		Order testOrder = new Order(testAL, 20);
		
		//Constructor Test
		assertEquals("UID1",testOrder.getShelfUIDs().get(0));
		assertEquals("UID3",testOrder.getShelfUIDs().get(2));
		assertEquals(20, testOrder.getTicksToPack());
		
		
		//Create new Order object
		ArrayList<String> testAL2 = new ArrayList<>();
		testAL2.add("UID4");
		testAL2.add("UID5");
		testAL2.add("UID6");
		Order testOrder2 = new Order(testAL2, 77);
		
		//Constructor Test
		assertEquals("UID4",testOrder2.getShelfUIDs().get(0));
		assertEquals("UID6",testOrder2.getShelfUIDs().get(2));
		assertEquals(77, testOrder2.getTicksToPack());

	}

}
