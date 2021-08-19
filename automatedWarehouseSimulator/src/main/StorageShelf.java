package main;

/**
 * This class details the fields and method of a StorageShelf object.
 * 
 * A shelf has the items which can be ordered by customers.
 * Robots take items from these shelves and are mostly 
 * passive markers for the robots to go.
 * 
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class StorageShelf extends WarehouseObject {

	
	/**
	 * Constructor for a StorageShelf object.
	 * @param UID - this shelfs UID
	 */
	public StorageShelf(String UID) {
		super(UID);
	}

	/**
	 * Return a string representation of the StorageShelf object.
	 * @return string representation of the StorageShelf object
	 */	
	public String toString() {
		return "Storage Shelf("+UID+")";
	}

}
