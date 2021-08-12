package main;

/**
 * Details here
 * @author Alfie Smith, Kayley Whitfield, Dan Philpot
 *
 */

public class Position implements Tick{
	
	private int y;
	private int x;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}

	/**
	 * 
	 */
	@Override
	public void Tick() {
		// TODO Auto-generated method stub
		
	}
}
