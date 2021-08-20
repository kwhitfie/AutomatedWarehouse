package main;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class tests the methods of the StorageShelf class
 * 
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class StorageShelfTest {

	@Test
	public void test() {
		
		//Create new StorageShelf
		StorageShelf stSh1 = new StorageShelf("stSh1");
		
		//Constructor Test
		assertEquals("stSh1", stSh1.getUID());
		
		//Test toString()
		assertEquals("Storage Shelf(stSh1)", stSh1.toString());
	}

}
