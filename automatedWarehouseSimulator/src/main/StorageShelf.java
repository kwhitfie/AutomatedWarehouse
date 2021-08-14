package main;

/**
 * Details here
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class StorageShelf extends WarehouseObject implements Tick{

	
	/**
	 * 
	 */
	public StorageShelf(String UID) {
		super(UID);
	}

	/**
	 * 
	 * @return
	 */	
	public String toString() {
		return "Storage Shelf("+UID+")";
	}

	/**
	 * 
	 */
	@Override
	public void Tick() {
		// TODO Auto-generated method stub
		
	}
}
