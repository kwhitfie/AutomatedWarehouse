package main;

/**
 * This abstract class WarehouseObject details
 * the fields and methods of what a object in the 
 * Warehouse should contain. 
 * 
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */
public abstract class WarehouseObject {
	protected String UID;
	
	/**
	 * Constructor for a WarehouseObject
	 * @param UID - the objects UID
	 */
	public WarehouseObject (String UID) {
		this.UID = UID;
	}
	
	/**
	 * Get the UID of this Warehouse object.
	 * @return object UID
	 */
	public String getUID() {
		return UID;
	}
	
	/**
	 * Return a string representation of the object implementing this method.
	 */
	public abstract String toString();

}
