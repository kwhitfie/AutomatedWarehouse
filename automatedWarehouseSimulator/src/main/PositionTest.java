package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class PositionTest {

	@Test
	public void test() {
		
		//create new Position
		Position pst1 = new Position(5,7);
		
		//test constructor
		assertEquals(5, pst1.getX());
		assertEquals(7, pst1.getY());
		
		//test toString()
		assertEquals("(5,7)", pst1.toString());
		
		//test set x
		pst1.setX(15);
		
		//test set y
		pst1.setY(12);
		
		//test all again
		assertEquals(15, pst1.getX());
		assertEquals(12, pst1.getY());
		assertEquals("(15,12)", pst1.toString());
		
	}

}
