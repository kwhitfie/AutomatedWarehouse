package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class StorageShelfTest {

	@Test
	public void test() {
		
		//create new StorageShelf
		StorageShelf stSh1 = new StorageShelf("stSh1");
		
		//test constructor
		assertEquals("stSh1", stSh1.getUID());
		
		//test toString()
		assertEquals("Storage Shelf(stSh1)", stSh1.toString());
	}

}
