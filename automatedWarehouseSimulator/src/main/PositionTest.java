package main;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class tests the methods of the Position class
 * 
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class PositionTest {

	@Test
	public void test() {
		
		//Create new Position
		Position pst1 = new Position(5,7);
		
		//Constructor Test
		assertEquals(5, pst1.getX());
		assertEquals(7, pst1.getY());
		
		//Test toString()
		assertEquals("(5,7)", pst1.toString());
		
		//Test set x
		pst1.setX(15);
		
		//Test set y
		pst1.setY(12);
		
		//Test all again
		assertEquals(15, pst1.getX());
		assertEquals(12, pst1.getY());
		assertEquals("(15,12)", pst1.toString());
		
	}

}
