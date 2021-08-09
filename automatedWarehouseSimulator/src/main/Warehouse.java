package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Warehouse implements Tick{
	
	private OrderQueue oq;
	private HashMap<Position, ArrayList<String>> grid;

	public Warehouse() {
		// TODO Auto-generated constructor stub
		oq = new OrderQueue();
		grid = new HashMap<Position, ArrayList<String>>();
	}

	@Override
	public void Tick() {
		// TODO Auto-generated method stub
	}
	
	public OrderQueue getOrderQueue() {
		return oq;
	}
	
	public boolean crashMonitor() {
		return false;
	}
	
	public Position getGridSquare() {
		return null;
	}
	
	public void setGridSquare(String UID) {
		
	}
	
	public void tickAll() {
		//tick tick tick tick tick tick tick tick tick tick tick 
	}
}
