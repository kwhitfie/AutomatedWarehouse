package main;

/**
 * This tick interface is used by objects that will
 * perform actions each tick of the simulation.
 * 
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot.
 *
 */

public interface Tick {
	
	/**
	 * The tick method will be implemented by objects that will
	 * perform actions each tick of the simulation. 
	 * 
	 * It will require a Warehouse object to perform these actions.
	 * 
	 * @param wh - Warehouse object
	 */
	public void tick(Warehouse wh);
}
