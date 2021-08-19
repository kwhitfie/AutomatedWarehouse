package main;

/**
 * This class details the fields and method of a Position object.
 * 
 * A Position object imitates a cell on a grid/table by containing
 * x and y coordinates.
 * 
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class Position {
	
	private int y;
	private int x;
	
	/**
	 * Constructor for a Position object.
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the X coordinate of this Position object.
	 * @return x 
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get the Y coordinate of this Position object.
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Set the X coordinate of this Position object.
	 * @param x coordinate to be set
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Set the Y coordinate of this Position object.
	 * @param y coordinate to be set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Return a string representation of the Position object.
	 * @return string representation
	 */
	public String toString() {
		return "("+x+","+y+")";
	}

}
