package main;

/**
 * Details here
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class StorageShelf implements Tick{

	private String UID;
	
	/**
	 * 
	 */
	public StorageShelf(String UID) {
		this.UID = UID;
	}

	/**
	 * 
	 * @return
	 */
	public String getUID() {
		return UID;
	}
	
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
